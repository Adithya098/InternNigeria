package com.werpindia.internnigeria.utils;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

public class CustomDataBindingUtils
{

    @BindingAdapter("currentValue")
    public static void spinnerAdapter(Spinner spinner, ObservableField<String> currentValue)
    {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                currentValue.set(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
