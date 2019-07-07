package com.werpindia.internnigeria.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.werpindia.internnigeria.models.FirebaseResponse;
import com.werpindia.internnigeria.models.Internship;
import com.werpindia.internnigeria.repositories.InternshipRepository;

public class InternshipViewModel extends AndroidViewModel {
    private InternshipRepository internshipRepository;

    public InternshipViewModel(@NonNull Application application) {
        super(application);
        internshipRepository = InternshipRepository.getInstance();
    }

    public LiveData<FirebaseResponse> postInternship(Internship newInternship) {
        return internshipRepository.postInternship(newInternship);
    }

    public LiveData<FirebaseResponse> getCompanyInternships(String companyEmail) {
        return internshipRepository.getCompanyInternships(companyEmail);
    }

    public LiveData<FirebaseResponse> getInternships() {
        return internshipRepository.getAllInternships();
    }

}
