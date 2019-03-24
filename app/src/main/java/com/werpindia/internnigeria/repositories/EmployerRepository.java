package com.werpindia.internnigeria.repositories;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import com.werpindia.internnigeria.models.Employer;

import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EmployerRepository
{
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private final String EMPLOYER_COLLECTION_NAME = "Employers";

    public EmployerRepository()
    {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public Single<Boolean> loginEmployer(String email, String password)
    {
        return Single.create((SingleOnSubscribe<Boolean>) emitter ->
        {
            if (!emitter.isDisposed())
            {
                //Sign In User With Email And Password
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task ->
                {
                    if (task.isSuccessful())
                    {
                        //Check If Email Provided Is Verified
                        if (auth.getCurrentUser().isEmailVerified()) emitter.onSuccess(true);
                        else emitter.onError(new Exception("Email Is Not Verified"));
                    }
                    else emitter.onError(task.getException());
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Boolean> signUp(Employer newEmployer)
    {
        return Single.create((SingleOnSubscribe<Boolean>) emitter ->
        {
            if (!emitter.isDisposed())
            {
                //Create A New User With Details Provided
               auth.createUserWithEmailAndPassword(newEmployer.getEmail(),newEmployer.getPassword()).addOnCompleteListener(task ->
               {
                   //Save The Employer Details If The Account Creation Was Successful
                   if (task.isSuccessful()) db.collection(EMPLOYER_COLLECTION_NAME).add(newEmployer).addOnCompleteListener(addTask ->
                   {
                       if (addTask.isSuccessful()) {
                          /* If Saving Was Successful Send A Verification Email To The User And Then
                              Sign Them Out*/
                           Objects.requireNonNull(auth.getCurrentUser()).sendEmailVerification();
                           auth.signOut();
                       } else emitter.onError(addTask.getException());
                   });
                   else emitter.onError(task.getException());
               });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
