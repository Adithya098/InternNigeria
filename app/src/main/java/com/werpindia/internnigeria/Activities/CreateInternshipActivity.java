package com.werpindia.internnigeria.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.werpindia.internnigeria.R;
import com.werpindia.internnigeria.databinding.ActivityCreateInternshipBinding;

public class CreateInternshipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCreateInternshipBinding createInternshipBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_internship);
    }
}
