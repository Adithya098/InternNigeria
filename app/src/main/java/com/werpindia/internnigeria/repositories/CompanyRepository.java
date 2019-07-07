package com.werpindia.internnigeria.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.werpindia.internnigeria.models.Company;
import com.werpindia.internnigeria.models.FirebaseResponse;

import java.util.Objects;

public class CompanyRepository {
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private final String COMPANY_COLLECTION_NAME = "Companies";

    public CompanyRepository() {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public LiveData<FirebaseResponse> login(String email, String password) {
        MutableLiveData<FirebaseResponse> result = new MutableLiveData<>();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task ->
        {
            if (task.isSuccessful()) {
                if (auth.getCurrentUser().isEmailVerified())
                    result.setValue(new FirebaseResponse("Sucesss", null));
                else
                    result.setValue(new FirebaseResponse(null, new Exception("Email Is Not Verified")));
            } else result.setValue(new FirebaseResponse(null, task.getException()));
        });
        return result;
    }

    public LiveData<FirebaseResponse> signUp(Company newCompany) {

        MutableLiveData<FirebaseResponse> result = new MutableLiveData<>();
        auth.createUserWithEmailAndPassword(newCompany.getProfile().getCompanyName(), newCompany.getPassword()).addOnCompleteListener(task ->
        {
            //Save The Company Details If The Account Creation Was Successful
            if (task.isSuccessful())
                db.collection(COMPANY_COLLECTION_NAME).add(newCompany).addOnCompleteListener(addTask ->
                {
                    if (addTask.isSuccessful()) {
                        Objects.requireNonNull(auth.getCurrentUser()).sendEmailVerification();
                        auth.signOut();
                        result.setValue(new FirebaseResponse(
                                "A Confirmation Message Has Been Sent To Your Email", null));
                    } else result.setValue(new FirebaseResponse(null, addTask.getException()));
                });
            else result.setValue(new FirebaseResponse(null, new Exception(task.getException())));
        });
        return result;
    }

    public LiveData<FirebaseResponse> getCompany(String email) {
        MutableLiveData<FirebaseResponse> result = new MutableLiveData<>();

        db.collection(COMPANY_COLLECTION_NAME).whereEqualTo("email", email).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot snapshot = task.getResult();
                if (snapshot != null && !snapshot.getDocuments().isEmpty())
                    result.setValue(new FirebaseResponse(
                            snapshot.getDocuments().get(0).toObject(Company.class), null));
                else result.setValue(new FirebaseResponse(null, null));
            } else result.setValue(new FirebaseResponse(null, task.getException()));
        });
        return result;
    }

}
