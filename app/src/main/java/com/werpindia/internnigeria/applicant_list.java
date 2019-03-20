package com.werpindia.internnigeria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class applicant_list extends Fragment {
    private static final String tag ="Applicant_list";
    List<applications> applicants;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.applicant_list,container,false);


        applicants=new ArrayList<>();
        applicants.add(new applications("Kunal Hirani","abc@123.com",R.drawable.abc));
        applicants.add(new applications("abc123","abc@123.com",R.drawable.abc));
        applicants.add(new applications("abc xyz","abc@123.com",R.drawable.abc));
        RecyclerView myrv=(RecyclerView) getActivity().findViewById(R.id.rv1);
        RecylerViewAdapter recylerViewAdapter=new RecylerViewAdapter(getActivity(),applicants);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myrv.setLayoutManager(layoutManager);
        myrv.setAdapter(recylerViewAdapter);
        return view;
    }
}
