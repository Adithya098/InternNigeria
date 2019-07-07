package com.werpindia.internnigeria.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.werpindia.internnigeria.R;
import com.werpindia.internnigeria.fragments.LoginFragment;
import com.werpindia.internnigeria.fragments.SignUpFragment;
import com.werpindia.internnigeria.listeners.AuthSwitchListener;

public class AuthenticateActivity extends AppCompatActivity implements AuthSwitchListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        if (getSupportFragmentManager().findFragmentById(R.id.authFragmentContainer) == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.authFragmentContainer, new LoginFragment())
                    .commit();

    }

    @Override
    public void onSwitch() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.authFragmentContainer);
        if (currentFragment instanceof LoginFragment)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.authFragmentContainer, new SignUpFragment())
                    .commit();
        else
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.authFragmentContainer, new LoginFragment())
                    .commit();

    }
}

