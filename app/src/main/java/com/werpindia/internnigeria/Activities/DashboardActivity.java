package com.werpindia.internnigeria.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;
import com.werpindia.internnigeria.Adapters.DashboardViewPagerAdapter;
import com.werpindia.internnigeria.R;

public class DashboardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = (Toolbar) findViewById(R.id.dashboardToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.dashboardTabs);
        viewPager = (ViewPager) findViewById(R.id.dashboardViewPager);

        DashboardViewPagerAdapter dashboardViewPagerAdapter = new DashboardViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(dashboardViewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }
}
