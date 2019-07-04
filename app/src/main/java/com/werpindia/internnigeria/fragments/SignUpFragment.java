package com.werpindia.internnigeria.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.werpindia.internnigeria.R;
import com.werpindia.internnigeria.activities.AuthenticateActivity;
import com.werpindia.internnigeria.databinding.FragmentSignUpBinding;
import com.werpindia.internnigeria.listeners.AuthSwitchListener;
import com.werpindia.internnigeria.viewModels.CompanyViewModel;

import java.util.concurrent.TimeUnit;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding signUpBinding;
    private CompanyViewModel companyViewModel;
    private AuthSwitchListener authSwitchListener;
    private ObservableBoolean layoutEnabled = new ObservableBoolean(true);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        authSwitchListener = (AuthSwitchListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false);
        signUpBinding.setLayoutEnabled(layoutEnabled);
        return signUpBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        companyViewModel = ViewModelProviders.of(this).get(CompanyViewModel.class);
        signUpBinding.setEmployerModel(companyViewModel);
        signUpBinding.setSignUpPhoneNumber(new ObservableField<>());

        signUpBinding.setLoginListener(v -> authSwitchListener.onSwitch());

        signUpBinding.setSignUpListener(v ->
        {
            layoutEnabled.set(false);
            //Retrieve PhoneNumber From The EditText Observable
            String phoneNumber = signUpBinding.getSignUpPhoneNumber().get().trim();
            String email = companyViewModel.getSignUpEmail().get().trim();
            String companyName = companyViewModel.getSignUpCompanyName().get().trim();
            String password = companyViewModel.getSignUpPassword().get().trim();
            String confirmPassword = companyViewModel.getSignUpConfirmPassword().get().trim();

            if (companyName.isEmpty()) {
                signUpBinding.signUpCompanyName.setError("Company Name Is Empty");
                layoutEnabled.set(true);
            } else if (email.isEmpty()) {
                signUpBinding.signUpEmail.setError("Email Is Empty");
                layoutEnabled.set(true);
            } else if (password.isEmpty()) {
                signUpBinding.signUpPassword.setError("Password Is Empty");
                layoutEnabled.set(true);
            } else if (confirmPassword.isEmpty()) {
                signUpBinding.signUpConfirmPassword.setError("Confirm Password Is Empty");
                layoutEnabled.set(true);
            } else if (!confirmPassword.equals(password)) {
                signUpBinding.signUpConfirmPassword.setError("Passwords Do Not Match");
                layoutEnabled.set(true);
            } else if (phoneNumber.isEmpty()) {
                signUpBinding.signUpPhoneNumber.setError("Phone Number Is Empty");
                layoutEnabled.set(true);
            } else
                PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, getActivity(),
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeAutoRetrievalTimeOut(String s) {
                                View view = getLayoutInflater().inflate(R.layout.dialog_phone_verification, null);
                                AlertDialog.Builder otpDialogBuilder = new AlertDialog.Builder(getContext());
                                otpDialogBuilder.setTitle("Enter Otp");
                                otpDialogBuilder.setView(view);
                                otpDialogBuilder.setPositiveButton("Verify", (dialog, which) -> {
                                    EditText otp = view.findViewById(R.id.otpText);
                                    if (otp.getText().toString().equals(s))
                                        signUp();
                                });
                                otpDialogBuilder.setNegativeButton("Cancel", null);
                                otpDialogBuilder.create().show();
                            }

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                signUp();
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(String verificationCode, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            }
                        });
        });
    }

    private void signUp() {
        companyViewModel.signUpEmployer(signUpBinding.signUpPhoneNumber.getText().toString()).observe(this, result ->
        {
            if (result != null) if (!result.hasError()) {
                // Display Alert Dialog To Inform The User About The Confirmation Email Sent
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Email Verification");
                builder.setMessage("A Confirmation Message Has Been Sent To the Email Provided");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", (dialog, which) -> {
                    startActivity(new Intent(getContext(), AuthenticateActivity.class));
                    getActivity().finish();
                });
                builder.create().show();
            } else
                Toast.makeText(getContext(), "An Error Occurred", Toast.LENGTH_SHORT).show();
        });
    }
}
