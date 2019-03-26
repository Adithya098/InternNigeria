package com.werpindia.internnigeria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.werpindia.internnigeria.R;
import com.werpindia.internnigeria.databinding.ActivityCreateInternshipBinding;

public class CreateInternshipActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityCreateInternshipBinding createInternshipBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_internship);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Internship_Category,android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> durationLengthAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Internship_Duration_Length,android.R.layout.simple_spinner_item);
        durationLengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> durationTypeAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Internship_Duration_Type,android.R.layout.simple_spinner_item);
        durationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        createInternshipBinding.setCategoryAdapter(categoryAdapter);
        createInternshipBinding.setDurationLengthAdapter(durationLengthAdapter);
        createInternshipBinding.setDurationTypeAdapter(durationTypeAdapter);

    }
}
