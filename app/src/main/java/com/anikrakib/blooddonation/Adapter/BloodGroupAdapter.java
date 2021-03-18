package com.anikrakib.blooddonation.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.anikrakib.blooddonation.Model.BloodGroupModel;
import com.anikrakib.blooddonation.R;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BloodGroupAdapter extends RecyclerView.Adapter<BloodGroupAdapter.BloodGroupViewHolder> {
    List<BloodGroupModel> bloodGroupModels;
    Context context;
    int rowIndex = -1;
    OnBloodGroupCLickListener onBloodGroupCLickListener;

    public BloodGroupAdapter(Context context, List<BloodGroupModel> bloodGroupModels, OnBloodGroupCLickListener onBloodGroupCLickListener) {
        this.bloodGroupModels = bloodGroupModels;
        this.context = context;
        this.onBloodGroupCLickListener = onBloodGroupCLickListener;
    }

    @NonNull
    @Override
    public BloodGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BloodGroupViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(
                                R.layout.item_blood_group,
                                parent,
                                false),onBloodGroupCLickListener
        );
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull BloodGroupViewHolder holder, int position) {

        holder.setBloodGroupData(bloodGroupModels.get(position));

        holder.bloodGroup.setOnClickListener(v->{
            onBloodGroupCLickListener.onBloodGroupCLickListener(bloodGroupModels.get(position).getBloodGroup());
            rowIndex = position;
            notifyDataSetChanged();
        });

        if(rowIndex == position){
            holder.bloodGroup.setBackgroundResource(R.drawable.blood_group_selected_background);
            holder.bloodGroup.setTextColor(Color.WHITE);
        }else {
            holder.bloodGroup.setBackgroundResource(R.drawable.blood_group_un_selected_background);
            holder.bloodGroup.setTextColor(ContextCompat.getColor(context,R.color.primaryColor));
        }

    }


    @Override
    public int getItemCount() {
        return bloodGroupModels.size();
    }

    public static class BloodGroupViewHolder extends RecyclerView.ViewHolder {
        private final TextView bloodGroup;
        OnBloodGroupCLickListener onBloodGroupCLickListener;
        public BloodGroupViewHolder(@NonNull View itemView, OnBloodGroupCLickListener onBloodGroupCLickListener) {
            super(itemView);
            bloodGroup = itemView.findViewById(R.id.bloodGroup);
            this.onBloodGroupCLickListener = onBloodGroupCLickListener;
        }

        @SuppressLint("SetTextI18n")
        void setBloodGroupData(BloodGroupModel bloodGroupModel){
           bloodGroup.setText(bloodGroupModel.getBloodGroup());
        }
    }

    public interface OnBloodGroupCLickListener{
        void onBloodGroupCLickListener(String bloodGroup);
    }

}
