package com.werpindia.internnigeria.repositories;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.werpindia.internnigeria.models.Employer;

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
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task ->
                {
                    if (task.isSuccessful()) emitter.onSuccess(true);
                    else emitter.onError(task.getException());
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Boolean> signuUp(Employer newEmployer)
    {
        return Single.create((SingleOnSubscribe<Boolean>) emitter ->
        {
            if (!emitter.isDisposed())
            {
               auth.createUserWithEmailAndPassword(newEmployer.getEmail(),newEmployer.getPassword()).addOnCompleteListener(task ->
               {
                   if (task.isSuccessful())
                   {
                       db.collection(EMPLOYER_COLLECTION_NAME).add(newEmployer).addOnCompleteListener(addTask ->
                       {
                           if (addTask.isSuccessful()) emitter.onSuccess(true);
                           else emitter.onError(addTask.getException());
                       });
                   }
                   else emitter.onError(task.getException());
               });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
