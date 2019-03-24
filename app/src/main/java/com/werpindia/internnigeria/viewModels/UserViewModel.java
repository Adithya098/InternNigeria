package com.werpindia.internnigeria.viewModels;

import android.app.Application;
import android.widget.Toast;

import com.werpindia.internnigeria.models.Employer;
import com.werpindia.internnigeria.repositories.EmployerRepository;
import com.werpindia.internnigeria.utils.InputValidator;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.observers.DisposableSingleObserver;

public class UserViewModel extends AndroidViewModel
{
    private ObservableField<String> loginEmail = new ObservableField<>();
    private ObservableField<String> loginPassword = new ObservableField<>();

    private ObservableField<String> signUpPassword = new ObservableField<>();
    private ObservableField<String> signUpConfirmPassword = new ObservableField<>();
    private ObservableField<String> signUpCompanyName = new ObservableField<>();
    private ObservableField<String> signUpEmail = new ObservableField<>();

    private EmployerRepository employerRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        employerRepository = new EmployerRepository();
    }

    public LiveData<Boolean> loginEmployer()
    {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        if (InputValidator.validateLoginDetails(loginEmail, loginPassword) == null)
        {
            employerRepository.loginEmployer(loginEmail.get(), loginPassword.get()).subscribe(new DisposableSingleObserver<Boolean>()
            {
                @Override
                public void onSuccess(Boolean isSuccessful) {
                    result.setValue(isSuccessful);
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        return result;
    }

    public LiveData<Boolean> signUpEmployer(String phoneNumber)
    {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        if (InputValidator.validateSignUpDetails(signUpEmail, signUpPassword, signUpConfirmPassword, signUpCompanyName) == null)
        {
            Employer newEmployer = new Employer(signUpCompanyName.get(), signUpEmail.get(), signUpPassword.get(),phoneNumber);
            employerRepository.signUp(newEmployer).subscribe(new DisposableSingleObserver<Boolean>() {
                @Override
                public void onSuccess(Boolean aBoolean) {
                    result.setValue(aBoolean);
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        return result;
    }

    public ObservableField<String> getLoginEmail() {
        return loginEmail;
    }

    public ObservableField<String> getLoginPassword() {
        return loginPassword;
    }

    public ObservableField<String> getSignUpPassword() { return signUpPassword; }

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
