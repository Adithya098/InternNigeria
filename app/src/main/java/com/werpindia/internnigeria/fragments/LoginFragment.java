package com.werpindia.internnigeria.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.werpindia.internnigeria.activities.MainActivity;
import com.werpindia.internnigeria.databinding.FragmentLoginBinding;
import com.werpindia.internnigeria.listeners.AuthSwitchListener;
import com.werpindia.internnigeria.viewModels.CompanyViewModel;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding loginBinding;
    private AuthSwitchListener authSwitchListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        return loginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ObservableBoolean layoutEnabled = new ObservableBoolean(true);
        loginBinding.setLayoutEnabled(layoutEnabled);

        CompanyViewModel companyViewModel =
                ViewModelProviders.of(this).get(CompanyViewModel.class);

        loginBinding.setCreateListener(v ->
                authSwitchListener.onSwitch());

        loginBinding.setUserModel(companyViewModel);
        loginBinding.setLoginListener(v -> {
            layoutEnabled.set(false);

            String email = companyViewModel.getLoginEmail().get().trim();
            String password = companyViewModel.getLoginPassword().get().trim();

            if (email.isEmpty()) {
                loginBinding.loginEmail.setError("Email Is Empty");
                layoutEnabled.set(true);
            } else if (password.isEmpty()) {
                loginBinding.loginPassword.setError("Password Is Empty");
                layoutEnabled.set(true);
            } else {
                companyViewModel.loginEmployer().observe(this, result -> {
                    if (result != null) if (result.hasError()) {
                        showToast(result.getError().getMessage());
                        layoutEnabled.set(true);
                    } else {
                        startActivity(new Intent(getContext(), MainActivity.class));
                        getActivity().finish();
                    }
                });
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        authSwitchListener = (AuthSwitchListener) context;
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
