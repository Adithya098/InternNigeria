package com.werpindia.internnigeria.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.werpindia.internnigeria.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardRecyclerAdapter extends RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardRecyclerViewHolder> {

    public DashboardRecyclerAdapter() {
    }

    public class DashboardRecyclerViewHolder extends RecyclerView.ViewHolder {
        public DashboardRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public DashboardRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.internship_card_layout, parent, false);
        return new DashboardRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
