package com.werpindia.internnigeria.activities;

import android.content.Intent;
import android.os.Bundle;

import com.werpindia.internnigeria.R;
import com.werpindia.internnigeria.databinding.ActivityUserSignupBinding;
import com.werpindia.internnigeria.viewModels.UserViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class UserSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityUserSignupBinding signUpBinding = DataBindingUtil.setContentView(this,R.layout.activity_user_signup);

        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        signUpBinding.setEmployerModel(userViewModel);

        signUpBinding.setSignUpListener(v -> userViewModel.signUpEmployer().observe(this, result ->
        {
            if (result != null ) if (result)
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }));

    }
}
