package com.werpindia.internnigeria.viewModels;

import android.app.Application;
import android.widget.Toast;

import com.werpindia.internnigeria.models.Company;
import com.werpindia.internnigeria.models.CompanyProfile;
import com.werpindia.internnigeria.repositories.CompanyRepository;
import com.werpindia.internnigeria.repositories.InternshipRepository;
import com.werpindia.internnigeria.utils.AuthenticationInputValidator;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class CompanyViewModel extends AndroidViewModel
{
    private ObservableField<String> loginEmail = new ObservableField<>();
    private ObservableField<String> loginPassword = new ObservableField<>();

    private ObservableField<String> signUpPassword = new ObservableField<>();
    private ObservableField<String> signUpConfirmPassword = new ObservableField<>();
    private ObservableField<String> signUpCompanyName = new ObservableField<>();
    private ObservableField<String> signUpEmail = new ObservableField<>();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private CompanyRepository companyRepository;
    private InternshipRepository internshipRepository;

    public CompanyViewModel(@NonNull Application application)
    {
        super(application);
        companyRepository = new CompanyRepository();
        internshipRepository = new InternshipRepository();
    }

    public LiveData<Boolean> loginEmployer()
    {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        if (AuthenticationInputValidator.validateLoginDetails(loginEmail, loginPassword) == null)
        {
            companyRepository.login(loginEmail.get(), loginPassword.get()).subscribe(new SingleObserver<Boolean>()
            {
                @Override
                public void onSubscribe(Disposable d) { compositeDisposable.add(d); }

                @Override
                public void onSuccess(Boolean isSuccessful) { result.setValue(isSuccessful); }

                @Override
                public void onError(Throwable e) { Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show(); }
            });
        }
        return result;
    }

    public LiveData<Boolean> signUpEmployer(String phoneNumber)
    {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        if (AuthenticationInputValidator.validateSignUpDetails(signUpEmail, signUpPassword, signUpConfirmPassword, signUpCompanyName) == null)
        {
            Company newCompany = new Company(new CompanyProfile(signUpCompanyName.get(),null), signUpPassword.get(),phoneNumber,signUpEmail.get());
            companyRepository.signUp(newCompany).subscribe(new SingleObserver<Boolean>()
            {
                @Override
                public void onSubscribe(Disposable d) { compositeDisposable.add(d); }

                @Override
                public void onSuccess(Boolean aBoolean) { result.setValue(aBoolean); }

                @Override
                public void onError(Throwable e) { Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show(); }
            });
        }
        return result;
    }

    public LiveData<Company> getCompany(String email)
    {
        MutableLiveData<Company> result = new MutableLiveData<>();
        companyRepository.getCompany(email).subscribe(new SingleObserver<Company>() {
            @Override
            public void onSubscribe(Disposable d) { compositeDisposable.add(d); }

            @Override
            public void onSuccess(Company company) { result.setValue(company); }

            @Override
            public void onError(Throwable e) { Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show(); }
        });
        return result;
    }

    @Override
    protected void onCleared()
    {
        compositeDisposable.clear();
        super.onCleared();
    }

    public ObservableField<String> getLoginEmail() { return loginEmail; }

    public ObservableField<String> getLoginPassword() { return loginPassword; }

    public ObservableField<String> getSignUpPassword() { return signUpPassword; }

    public ObservableField<String> getSignUpConfirmPassword() { return signUpConfirmPassword; }

    public ObservableField<String> getSignUpEmail() { return signUpEmail; }

    public ObservableField<String> getSignUpCompanyName() { return signUpCompanyName; }
}
