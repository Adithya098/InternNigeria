package com.werpindia.internnigeria.userSignupLogin;

import android.os.Bundle;
import android.widget.Button;


import com.werpindia.internnigeria.R;

import androidx.appcompat.app.AppCompatActivity;

public class UserSignupActivity extends AppCompatActivity {

    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        signup = findViewById(R.id.signup);

    }
}
