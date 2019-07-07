package com.werpindia.internnigeria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.werpindia.internnigeria.databinding.InternshipListItemBinding;
import com.werpindia.internnigeria.models.Internship;

import java.util.List;

public class InternshipAdapter extends RecyclerView.Adapter<InternshipAdapter.DashboardRecyclerViewHolder> {

    private List<Internship> internships;
    private Context context;

    public InternshipAdapter(List<Internship> internships, Context context) {
        this.internships = internships;
        this.context = context;
    }

    @NonNull
    @Override
    public DashboardRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DashboardRecyclerViewHolder(
                InternshipListItemBinding.inflate(
                        LayoutInflater.from(context),
                        parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardRecyclerViewHolder holder, int position) {
        holder.listItemBinding.setCurrentInternship(internships.get(position));
    }

    @Override
    public int getItemCount() {
        return internships.size();
    }

    class DashboardRecyclerViewHolder extends RecyclerView.ViewHolder {
       private InternshipListItemBinding listItemBinding;

        DashboardRecyclerViewHolder(InternshipListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }
    }
}
