package com.werpindia.internnigeria.dialogs;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.werpindia.internnigeria.databinding.DialogPhoneVerificationBinding;
import com.werpindia.internnigeria.interfaces.PhoneVerificationListener;

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
        String actualVerificationCode = getArguments().getString("VERIFICATION_CODE");

        DialogPhoneVerificationBinding phoneVerificationBinding = DialogPhoneVerificationBinding.inflate(inflater,container,false);
        phoneVerificationBinding.setVerificationCode(new ObservableField<>());

        phoneVerificationBinding.setCancelListener(v -> dismiss());
        phoneVerificationBinding.setVerifyListener(v ->
        {
            String verificationCode = phoneVerificationBinding.getVerificationCode().get();
            if (actualVerificationCode.equals(verificationCode)) phoneVerificationListener.verificationSuccess();
            else phoneVerificationListener.verificationFailed();
        });

        return phoneVerificationBinding.getRoot();
    }

}
