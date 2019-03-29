package com.werpindia.internnigeria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.werpindia.internnigeria.adapters.DashboardViewPagerAdapter;
import com.werpindia.internnigeria.R;
import com.werpindia.internnigeria.databinding.ActivityDashboardBinding;

import java.util.Objects;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityDashboardBinding dashboardBinding = DataBindingUtil.setContentView(this,R.layout.activity_dashboard);

        setSupportActionBar((Toolbar) dashboardBinding.dashboardToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        dashboardBinding.setPagerAdapter(new DashboardViewPagerAdapter(getSupportFragmentManager()));
        dashboardBinding.dashboardTabs.setupWithViewPager(dashboardBinding.dashboardViewPager);
    }
}
