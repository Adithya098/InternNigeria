package com.werpindia.internnigeria.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.werpindia.internnigeria.Adapters.DashboardRecyclerAdapter;
import com.werpindia.internnigeria.R;


public class InternshipListFragment extends Fragment {

    private RecyclerView internshipRecyclerView;
    private Context context;

    public InternshipListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_internship_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        internshipRecyclerView = (RecyclerView) view.findViewById(R.id.internshipRecyclerView);
        DashboardRecyclerAdapter recyclerAdapter = new DashboardRecyclerAdapter();

        internshipRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        internshipRecyclerView.setAdapter(recyclerAdapter);

    }
}