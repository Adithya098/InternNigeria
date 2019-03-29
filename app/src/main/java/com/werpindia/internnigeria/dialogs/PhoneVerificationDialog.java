package com.werpindia.internnigeria.dialogs;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.werpindia.internnigeria.databinding.DialogPhoneVerificationBinding;
import com.werpindia.internnigeria.listeners.PhoneVerificationListener;

public class PhoneVerificationDialog extends DialogFragment
{
    private PhoneVerificationListener phoneVerificationListener;

    public PhoneVerificationDialog() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        phoneVerificationListener = (PhoneVerificationListener) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        DialogPhoneVerificationBinding phoneVerificationBinding = DialogPhoneVerificationBinding.inflate(inflater,container,false);

        if (getArguments() != null)
        {
            String verificationId = getArguments().getString("VERIFICATION_CODE");

            phoneVerificationBinding.setVerificationCode(new ObservableField<>());
            phoneVerificationBinding.setCancelListener(v -> dismiss());
            phoneVerificationBinding.setVerifyListener(v ->
            {
                String verificationCode = phoneVerificationBinding.getVerificationCode().get();
                if (verificationCode != null && !verificationCode.isEmpty())
                {
                    if (verificationId != null)
                    {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,verificationCode);
                        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(task ->
                        {
                            if (task.isSuccessful()) phoneVerificationListener.verificationSuccess();
                            else phoneVerificationListener.verificationFailed();
                        });
                    }
                    else dismiss();
                }
            });
        }
        else dismiss();
        return phoneVerificationBinding.getRoot();
    }

}
