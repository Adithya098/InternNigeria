package com.werpindia.internnigeria.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.werpindia.internnigeria.models.FirebaseResponse;
import com.werpindia.internnigeria.models.Internship;

import java.util.ArrayList;
import java.util.List;

public class InternshipRepository {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static InternshipRepository instance;

    private final String INTERNSHIP_COLLECTION = "Internships";
    private final String INTERNSHIP_APPLICANTS_COLLECTION = "Internships";

    private InternshipRepository() {
    }

    public static InternshipRepository getInstance() {
        if (instance == null) instance = new InternshipRepository();
        return instance;
    }

    public LiveData<FirebaseResponse> postInternship(Internship newInternship) {
        MutableLiveData<FirebaseResponse> result = new MutableLiveData<>();
        db.collection(INTERNSHIP_COLLECTION).add(newInternship)
                .addOnCompleteListener(task ->
                {
                    if (task.isSuccessful()) result.setValue(new FirebaseResponse(true, null));
                    else result.setValue(new FirebaseResponse(null, task.getException()));
                });
        return result;
    }

    public LiveData<FirebaseResponse> getCompanyInternships(String companyEmail) {
        MutableLiveData<FirebaseResponse> result = new MutableLiveData<>();
        db.collection(INTERNSHIP_COLLECTION).whereEqualTo("companyEmail", companyEmail)
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null && !querySnapshot.getDocuments().isEmpty()) {
                    List<Internship> internships = new ArrayList<>();
                    for (DocumentSnapshot snapshot : querySnapshot)
                        internships.add(snapshot.toObject(Internship.class));

                    result.setValue(new FirebaseResponse(internships, null));
                } else result.setValue(new FirebaseResponse(null, null));
            } else result.setValue(new FirebaseResponse(null, task.getException()));
        });
        return result;
    }

    public LiveData<FirebaseResponse> getApplicatnts(String id) {
        MutableLiveData<FirebaseResponse> result = new MutableLiveData<>();
        db.collection(INTERNSHIP_COLLECTION).whereEqualTo("id", id)
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null && !querySnapshot.getDocuments().isEmpty()) {
                    List<Internship> internships = new ArrayList<>();
                    for (DocumentSnapshot snapshot : querySnapshot)
                        internships.add(snapshot.toObject(Internship.class));

                    result.setValue(new FirebaseResponse(internships, null));
                } else result.setValue(new FirebaseResponse(null, null));
            } else result.setValue(new FirebaseResponse(null, task.getException()));
        });
        return result;
    }

    public LiveData<FirebaseResponse> getAllInternships() {
        MutableLiveData<FirebaseResponse> result = new MutableLiveData<>();
        db.collection(INTERNSHIP_COLLECTION).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null && !querySnapshot.getDocuments().isEmpty()) {
                    List<Internship> internships = new ArrayList<>();
                    for (DocumentSnapshot snapshot : querySnapshot)
                        internships.add(snapshot.toObject(Internship.class));
                    result.setValue(new FirebaseResponse(internships, null));
                } else result.setValue(new FirebaseResponse(null, null));
            } else result.setValue(new FirebaseResponse(null, task.getException()));
        });
        return result;
    }
}
