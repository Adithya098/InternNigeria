package com.werpindia.internnigeria.activities;

import android.content.Intent;
import android.os.Bundle;

import com.werpindia.internnigeria.R;
import com.werpindia.internnigeria.databinding.ActivityUserLoginBinding;
import com.werpindia.internnigeria.viewModels.EmployerViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityUserLoginBinding loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_user_login);

        EmployerViewModel employerViewModel = ViewModelProviders.of(this).get(EmployerViewModel.class);
        loginBinding.setUserModel(employerViewModel);

        loginBinding.setLoginListener(v -> employerViewModel.loginEmployer().observe(this, result ->
        {
            if (result != null) if (result) startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }));

        loginBinding.setCreateListener(v -> startActivity(new Intent(getApplicationContext(), SignUpActivity.class)));
    }
}

