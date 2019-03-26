package com.werpindia.internnigeria.repositories;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import com.werpindia.internnigeria.models.Internship;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InternshipRepository
{
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    private final String INTERNSHIP_COLLECTION_NAME = "Internships";

    public InternshipRepository()
    {
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public Single<Boolean> postInternship(Internship newInternship)
    {
        return Single.create((SingleOnSubscribe<Boolean>) emitter ->
        {
            if (!emitter.isDisposed())
            {
                db.collection(INTERNSHIP_COLLECTION_NAME).add(newInternship).addOnCompleteListener(task ->
                {
                    if (task.isSuccessful()) emitter.onSuccess(true);
                    else emitter.onError(task.getException());
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
