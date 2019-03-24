package com.werpindia.internnigeria.activities;

import android.content.Intent;
import android.os.Bundle;

import com.werpindia.internnigeria.R;
import com.werpindia.internnigeria.databinding.ActivityUserLoginBinding;
import com.werpindia.internnigeria.viewModels.UserViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class UserLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityUserLoginBinding loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_user_login);

        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        loginBinding.setUserModel(userViewModel);

        loginBinding.setLoginListener(v -> userViewModel.loginEmployer().observe(this,result ->
        {
            if (result != null) if (result) startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }));

        loginBinding.setCreateListener(v -> startActivity(new Intent(getApplicationContext(),UserSignUpActivity.class)));
    }
}

