package com.werpindia.internnigeria.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import com.werpindia.internnigeria.R;
import com.werpindia.internnigeria.databinding.ActivityUserSignupBinding;
import com.werpindia.internnigeria.dialogs.PhoneVerificationDialog;
import com.werpindia.internnigeria.interfaces.PhoneVerificationListener;
import com.werpindia.internnigeria.viewModels.UserViewModel;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModelProviders;

public class UserSignUpActivity extends AppCompatActivity implements PhoneVerificationListener
{
    private ActivityUserSignupBinding signUpBinding;
    private  UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        signUpBinding = DataBindingUtil.setContentView(this,R.layout.activity_user_signup);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        signUpBinding.setEmployerModel(userViewModel);
        signUpBinding.setSignUpPhoneNumber(new ObservableField<>());

        signUpBinding.setSignUpListener(v ->
        {
            //Retrieve PhoneNumber From The EditText Observable
            String phoneNumber = signUpBinding.getSignUpPhoneNumber().get();
            if (!phoneNumber.trim().isEmpty())
            {
                //Verify The Phone Number Provided
                PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) { signUp(phoneNumber); }

                    @Override
                    public void onVerificationFailed(FirebaseException e) { Toast.makeText(UserSignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show(); }

                    @Override
                    public void onCodeSent(String verificationCode, PhoneAuthProvider.ForceResendingToken forceResendingToken)
                    {
                        Toast.makeText(getApplicationContext(),verificationCode,Toast.LENGTH_LONG).show();
                        /* Display A Dialog For The User To Input Otp Sent*/
                        PhoneVerificationDialog verificationDialog = new PhoneVerificationDialog();
                        Bundle verificationBundle = new Bundle();
                        verificationBundle.putString("VERIFICATION_CODE",verificationCode);
                        verificationDialog.setArguments(verificationBundle);
                        verificationDialog.show(getSupportFragmentManager(),"PhoneVVerification Dialog");
                    }
                });
            }
            else Toast.makeText(getApplicationContext(),"Phone Number Is Empty",Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void verificationSuccess() { signUp(signUpBinding.getSignUpPhoneNumber().get()); }

    @Override
    public void verificationFailed() { Toast.makeText(getApplicationContext(),"Verification Failed",Toast.LENGTH_LONG).show(); }

    private void signUp(String phoneNumber)
    {
        signUpBinding.setSignUpListener(v -> userViewModel.signUpEmployer(phoneNumber).observe(UserSignUpActivity.this, result ->
        {
            if (result != null ) if (result)
            {
                // Display Alert Dialog To Inform The User About The Confirmation Email Sent
                AlertDialog.Builder builder = new AlertDialog.Builder(UserSignUpActivity.this);
                builder.setTitle("Email Verification");
                builder.setMessage("A Confirmation Message Has Been Sent To the Email Provided");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", (dialog, which) -> {
                    startActivity(new Intent(getApplicationContext(),UserLoginActivity.class));
                   finish();
                });
                builder.create().show();
            }
        }));
    }
}
