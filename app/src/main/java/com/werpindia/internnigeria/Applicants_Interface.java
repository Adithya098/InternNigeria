package com.werpindia.internnigeria;


import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;



import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


public class Applicants_Interface extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicantsinterface);
        ViewPagerAdapter mViewPageAdapter=new ViewPagerAdapter(getSupportFragmentManager());
       ViewPager mViewPager=(ViewPager) findViewById(R.id.container);




         ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

         //Adding Fragments
         adapter.addFragment(new applicant_list(), "Applicants Lists");
         adapter.addFragment(new applicant_message(), "Applicant Message");

        /*CircleImageView circleImageView=(CircleImageView)findViewById(R.id.image);

         */
         //Adapter Setup
         mViewPager.setAdapter(adapter);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

     }
    }




