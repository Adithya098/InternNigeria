package com.werpindia.internnigeria.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.werpindia.internnigeria.R;
import com.werpindia.internnigeria.adapters.InternshipAdapter;
import com.werpindia.internnigeria.databinding.ActivityMainBinding;
import com.werpindia.internnigeria.models.Internship;
import com.werpindia.internnigeria.viewModels.InternshipViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        InternshipViewModel internshipViewModel =
                ViewModelProviders.of(this)
                        .get(InternshipViewModel.class);
        internshipViewModel.getCompanyInternships(
                FirebaseAuth.getInstance().getCurrentUser().getEmail()).observe(this, result -> {
            if (result != null) {
                if (result.hasError()) {
                    Toast.makeText(getApplicationContext(), result.getError().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    List<Internship> internships = (List<Internship>) result.getData();
                    mainBinding.internshipList.setLayoutManager(
                            new LinearLayoutManager(getApplicationContext()));
                    mainBinding.internshipList.setHasFixedSize(true);
                    mainBinding.setInternshipAdapter(new InternshipAdapter(internships, getApplicationContext()));
                }
            }
        });
        mainBinding.setAddListener(v ->
                startActivity(new Intent(getApplicationContext(), CreateInternshipActivity.class)));
    }
}
