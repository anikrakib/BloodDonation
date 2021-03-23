package com.anikrakib.blooddonation.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.anikrakib.blooddonation.Model.BadgeModel;
import com.anikrakib.blooddonation.Model.BloodRequestModel;
import com.anikrakib.blooddonation.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class UrgentRequestAdapter extends RecyclerView.Adapter<UrgentRequestAdapter.UrgentRequestViewHolder> {
    List<BloodRequestModel> bloodRequestModels;
    Context context;

    public UrgentRequestAdapter(Context context, List<BloodRequestModel> bloodRequestModels) {
        this.bloodRequestModels = bloodRequestModels;
        this.context = context;
    }

    @NonNull
    @Override
    public UrgentRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UrgentRequestViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(
                                R.layout.item_request_blood,
                                parent,
                                false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull UrgentRequestViewHolder holder, int position) {
        //holder.setBadgeData(badgeModels.get(position),context);
        BloodRequestModel bloodRequestModel = bloodRequestModels.get(position);
        holder.bloodGroup.setText(bloodRequestModel.getBloodGroup());
        holder.hospital.setText(bloodRequestModel.getHospitalName());
        holder.postTime.setText(bloodRequestModel.getPostDateAndTime());
        holder.location.setText(bloodRequestModel.getLocation());
        holder.relation.setText(bloodRequestModel.getRelation());

        if(bloodRequestModel.isUrgentOrScheduled()){
            holder.emergencyOrNot.setText("Emergency");
        }else {
            holder.emergencyOrNot.setText("Scheduled");
        }

    }


    @Override
    public int getItemCount() {
        return bloodRequestModels.size();
    }

    public static class UrgentRequestViewHolder extends RecyclerView.ViewHolder {
        TextView emergencyOrNot,bloodGroup,hospital,relation,location,timeAndDay,postTime;

        public UrgentRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            emergencyOrNot = itemView.findViewById(R.id.EmergencyOrScheduled);
            timeAndDay = itemView.findViewById(R.id.timeAndDay);
            hospital = itemView.findViewById(R.id.hospitalName);
            postTime = itemView.findViewById(R.id.postTime);
            location = itemView.findViewById(R.id.location);
            relation = itemView.findViewById(R.id.relation);
            bloodGroup = itemView.findViewById(R.id.bloodGroup);

        }

        @SuppressLint("SetTextI18n")
        void setBadgeData(BadgeModel badgeModel,Context context){

        }
    }

}
