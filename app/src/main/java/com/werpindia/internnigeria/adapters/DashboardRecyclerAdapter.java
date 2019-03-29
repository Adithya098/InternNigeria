package com.werpindia.internnigeria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.werpindia.internnigeria.R;
import com.werpindia.internnigeria.models.Internship;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardRecyclerAdapter extends RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardRecyclerViewHolder> {

    private List<Internship> internships;
    private Context context;

    public DashboardRecyclerAdapter(List<Internship> internships , Context context)
    {
        this.internships = internships;
        this.context = context;
    }

    public class DashboardRecyclerViewHolder extends RecyclerView.ViewHolder {
        public DashboardRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public DashboardRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.internship_card_layout, parent, false);
        return new DashboardRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return internships.size();
    }
}
