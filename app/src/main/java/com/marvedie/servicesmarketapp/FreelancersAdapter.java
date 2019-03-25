package com.marvedie.servicesmarketapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



public class FreelancersAdapter extends RecyclerView.Adapter <FreelancersAdapter.FreelancersViewHolder> {

    private Context mCtx;
    private List<Freelancer> freelancerList;

    public FreelancersAdapter(Context mCtx, List<Freelancer> freelancerList) {
        this.mCtx = mCtx;
        this.freelancerList = freelancerList;
    }

    @NonNull
    @Override
    public FreelancersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycleview_freelancers, parent, false);
        return new FreelancersViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull FreelancersViewHolder holder, int position) {
        Freelancer freelancer = freelancerList.get(position);
        holder.textViewName.setText(freelancer.freelancerName);
        holder.textViewAge.setText("ChatEmail: " + freelancer.freelancerChat);
        holder.textViewGenre.setText("Category: " + freelancer.freelancerGenre);
        holder.textViewCountry.setText("Service: " + freelancer.freelancerService);

    }

    @Override
    public int getItemCount() {
        return freelancerList.size();
    }
    class FreelancersViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewGenre, textViewAge, textViewCountry;

        public FreelancersViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewGenre = itemView.findViewById(R.id.text_view_genre);
            textViewAge = itemView.findViewById(R.id.text_view_age);
            textViewCountry = itemView.findViewById(R.id.text_view_country);
        }
    }
}



