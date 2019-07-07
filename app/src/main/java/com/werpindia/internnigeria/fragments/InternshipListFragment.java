package com.werpindia.internnigeria.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import com.werpindia.internnigeria.adapters.InternshipAdapter;
import com.werpindia.internnigeria.databinding.FragmentInternshipListBinding;
import com.werpindia.internnigeria.viewModels.InternshipViewModel;

import java.util.Objects;

public class InternshipListFragment extends Fragment {

    public InternshipListFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        FragmentInternshipListBinding internshipListBinding = FragmentInternshipListBinding.inflate(inflater,container,false);
        InternshipViewModel internshipViewModel = ViewModelProviders.of(this).get(InternshipViewModel.class);

        String currentUserEmail = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();
        internshipViewModel.getCompanyInternships(currentUserEmail).observe(this,internships ->
        {
            if (internships != null)
            {
                internshipListBinding.internshipRecyclerView.setHasFixedSize(true);
                internshipListBinding.internshipRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                internshipListBinding.setAdapter(new InternshipAdapter(internships,getContext()));
            }
        });
        return internshipListBinding.getRoot();
    }
}