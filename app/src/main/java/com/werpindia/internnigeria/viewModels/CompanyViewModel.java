package com.werpindia.internnigeria.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.werpindia.internnigeria.models.Company;
import com.werpindia.internnigeria.models.CompanyProfile;
import com.werpindia.internnigeria.models.FirebaseResponse;
import com.werpindia.internnigeria.repositories.CompanyRepository;

public class CompanyViewModel extends AndroidViewModel {
    private ObservableField<String> loginEmail = new ObservableField<>();
    private ObservableField<String> loginPassword = new ObservableField<>();

    private ObservableField<String> signUpPassword = new ObservableField<>();
    private ObservableField<String> signUpConfirmPassword = new ObservableField<>();
    private ObservableField<String> signUpCompanyName = new ObservableField<>();
    private ObservableField<String> signUpEmail = new ObservableField<>();

    private CompanyRepository companyRepository;

    public CompanyViewModel(@NonNull Application application) {
        super(application);
        companyRepository = new CompanyRepository();
    }

    public LiveData<FirebaseResponse> loginEmployer() {
        return companyRepository.login(loginEmail.get(), loginPassword.get());
    }

    public LiveData<FirebaseResponse> signUpEmployer(String phoneNumber) {
        return companyRepository.signUp(
                new Company(new CompanyProfile(signUpCompanyName.get(), null),
                        signUpPassword.get(), phoneNumber, signUpEmail.get()));
    }

    public LiveData<FirebaseResponse> getCompany(String email) {
        return companyRepository.getCompany(email);
    }


    public ObservableField<String> getLoginEmail() {
        return loginEmail;
    }

    public ObservableField<String> getLoginPassword() {
        return loginPassword;
    }

    public ObservableField<String> getSignUpPassword() {
        return signUpPassword;
    }

    public ObservableField<String> getSignUpConfirmPassword() {
        return signUpConfirmPassword;
    }

    public ObservableField<String> getSignUpEmail() {
        return signUpEmail;
    }

    public ObservableField<String> getSignUpCompanyName() {
        return signUpCompanyName;
    }
}
