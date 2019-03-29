package com.werpindia.internnigeria.repositories;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QuerySnapshot;
import com.werpindia.internnigeria.models.Internship;

import java.util.ArrayList;
import java.util.List;

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

    public Single<Boolean> postInternship(Internship newInternship) {
        return Single.create((SingleOnSubscribe<Boolean>) emitter ->
        {
            if (!emitter.isDisposed())
            {
                db.collection(INTERNSHIP_COLLECTION_NAME).add(newInternship)
                        .addOnCompleteListener(task ->
                {
                    if (task.isSuccessful()) emitter.onSuccess(true);
                    else emitter.onError(task.getException());
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Internship>> getCompanyInternships(String companyEmail) {
        return Single.create((SingleOnSubscribe<List<Internship>>) emitter ->
        {
            if (!emitter.isDisposed())
            {
                db.collection(INTERNSHIP_COLLECTION_NAME).whereEqualTo("companyEmail",companyEmail)
                        .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                    {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null && !querySnapshot.getDocuments().isEmpty())
                        {
                            List<Internship> internships = new ArrayList<>();
                            for (DocumentSnapshot snapshot : querySnapshot)
                                internships.add(snapshot.toObject(Internship.class));

                            emitter.onSuccess(internships);
                        }
                        else emitter.onSuccess(null);
                    }
                    else emitter.onError(task.getException());
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Internship>> getInternships()
    {
        return Single.create((SingleOnSubscribe<List<Internship>>) emitter ->
        {
            if (!emitter.isDisposed())
            {
                db.collection(INTERNSHIP_COLLECTION_NAME).get().addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                    {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null && !querySnapshot.getDocuments().isEmpty())
                        {
                            List<Internship> internships = new ArrayList<>();
                            for (DocumentSnapshot snapshot : querySnapshot)
                                internships.add(snapshot.toObject(Internship.class));

                            emitter.onSuccess(internships);
                        }
                        else emitter.onSuccess(null);
                    }
                    else emitter.onError(task.getException());
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
