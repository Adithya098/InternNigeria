package com.werpindia.internnigeria.repositories;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QuerySnapshot;

import com.werpindia.internnigeria.models.Company;

import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CompanyRepository {
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private final String COMPANY_COLLECTION_NAME = "Companies";

    public CompanyRepository() {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public Single<Boolean> login(String email, String password) {
        return Single.create((SingleOnSubscribe<Boolean>) emitter ->
        {
            if (!emitter.isDisposed()) {
                //Sign In User With Email And Password
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task ->
                {
                    if (task.isSuccessful()) {
                        //Check If Email Provided Is Verified
                        if (Objects.requireNonNull(auth.getCurrentUser()).isEmailVerified())
                            emitter.onSuccess(true);
                        else emitter.onError(new Exception("Email Is Not Verified"));
                    } else emitter.onError(task.getException());
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Boolean> signUp(Company newCompany) {
        return Single.create((SingleOnSubscribe<Boolean>) emitter -> {
            //Create A New User With Details Provided
            if (!emitter.isDisposed())
                auth.createUserWithEmailAndPassword(newCompany.getProfile().getCompanyName(), newCompany.getPassword()).addOnCompleteListener(task ->
                {
                    //Save The Company Details If The Account Creation Was Successful
                    if (task.isSuccessful())
                        db.collection(COMPANY_COLLECTION_NAME).add(newCompany).addOnCompleteListener(addTask ->
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
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Company> getCompany(String email) {
        return Single.create((SingleOnSubscribe<Company>) emitter ->
        {
            if (!emitter.isDisposed())
                db.collection(COMPANY_COLLECTION_NAME).whereEqualTo("email", email).get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null && !snapshot.getDocuments().isEmpty())
                            emitter.onSuccess(snapshot.getDocuments().get(0).toObject(Company.class));
                        else emitter.onSuccess(null);
                    } else emitter.onError(task.getException());
                });
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
