package com.werpindia.internnigeria.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.werpindia.internnigeria.R;
import com.werpindia.internnigeria.databinding.ActivityCreateInternshipBinding;
import com.werpindia.internnigeria.models.Internship;
import com.werpindia.internnigeria.viewModels.CompanyViewModel;
import com.werpindia.internnigeria.viewModels.InternshipViewModel;

import java.util.Objects;

public class CreateInternshipActivity extends AppCompatActivity {
    private ActivityCreateInternshipBinding createInternshipBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createInternshipBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_internship);

        ViewModelProvider providers = ViewModelProviders.of(this);

        InternshipViewModel internshipViewModel = providers.get(InternshipViewModel.class);
        CompanyViewModel companyViewModel = providers.get(CompanyViewModel.class);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Internship_Category, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> durationLengthAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Internship_Duration_Length, android.R.layout.simple_spinner_item);
        durationLengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> durationTypeAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Internship_Duration_Type, android.R.layout.simple_spinner_item);
        durationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        createInternshipBinding.setCategoryAdapter(categoryAdapter);
        createInternshipBinding.setDurationLengthAdapter(durationLengthAdapter);
        createInternshipBinding.setDurationTypeAdapter(durationTypeAdapter);

        createInternshipBinding.setInternshipDurationLength(new ObservableField<>());
        createInternshipBinding.setInternshipDurationType(new ObservableField<>());
        createInternshipBinding.setLocation(new ObservableField<>());
        createInternshipBinding.setCategory(new ObservableField<>());
        createInternshipBinding.setNumOfOpenings(new ObservableField<>());

        createInternshipBinding.setPostListener(v ->
        {
            String category = createInternshipBinding.getCategory().get();
            String durationLength = createInternshipBinding.getInternshipDurationLength().get();
            String location = createInternshipBinding.getLocation().get();
            String durationType = createInternshipBinding.getInternshipDurationType().get();
            String numOfOpenings = createInternshipBinding.getNumOfOpenings().get();

            String currentCompanyEmail = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();

            companyViewModel.getCompany(currentCompanyEmail).observe(this, currentCompany ->
            {
                if (currentCompany != null) {
                    Internship newInternShip = new Internship();
                    newInternShip.setCategory(category);
                    newInternShip.setDuration(durationLength + " " + durationType);
                    newInternShip.setNumOfOpenings(Integer.parseInt(numOfOpenings));
                    newInternShip.setLocation(location);

                    internshipViewModel.postInternship(newInternShip).observe(this, isSuccessful ->
                    {
                        if (isSuccessful != null) if (isSuccessful) {
                            Toast.makeText(this, "Upload Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, MainActivity.class));
                            finish();
                        }
                    });
                }
            });
        });
    }

    public Internship createInternShip() {
        Internship newInternShip = new Internship();

        String category = createInternshipBinding.getCategory().get();
        String durationLength = createInternshipBinding.getInternshipDurationLength().get();
        String location = createInternshipBinding.getLocation().get();
        String durationType = createInternshipBinding.getInternshipDurationType().get();
        String numOfOpenings = createInternshipBinding.getNumOfOpenings().get();

        newInternShip.setCategory(category);
        newInternShip.setDuration(durationLength + " " + durationType);
        newInternShip.setNumOfOpenings(Integer.parseInt(numOfOpenings));
        newInternShip.setLocation(location);

        return newInternShip;
    }
}
