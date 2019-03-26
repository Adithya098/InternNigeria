package com.werpindia.internnigeria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.werpindia.internnigeria.R;
import com.werpindia.internnigeria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainBinding.setAddListener(v -> startActivity(new Intent(getApplicationContext(),CreateInternshipActivity.class)));
    }
}
