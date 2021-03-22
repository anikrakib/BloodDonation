package com.anikrakib.blooddonation.Adapter.Profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.anikrakib.blooddonation.Model.BadgeModel;
import com.anikrakib.blooddonation.Model.BloodGroupModel;
import com.anikrakib.blooddonation.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class BadgesAdapter extends RecyclerView.Adapter<BadgesAdapter.BadgesViewHolder> {
    List<BadgeModel> badgeModels;
    Context context;

    public BadgesAdapter(Context context, List<BadgeModel> badgeModels) {
        this.badgeModels = badgeModels;
        this.context = context;
    }

    @NonNull
    @Override
    public BadgesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BadgesViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(
                                R.layout.item_badge,
                                parent,
                                false)
        );
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull BadgesViewHolder holder, int position) {
        holder.setBadgeData(badgeModels.get(position),context);
    }


    @Override
    public int getItemCount() {
        return badgeModels.size();
    }

    public static class BadgesViewHolder extends RecyclerView.ViewHolder {
        ImageView badgeIcon;
        TextView badgeName;
        public BadgesViewHolder(@NonNull View itemView) {
            super(itemView);
            badgeIcon = itemView.findViewById(R.id.badgeImage);
            badgeName = itemView.findViewById(R.id.badgeName);
        }

        @SuppressLint("SetTextI18n")
        void setBadgeData(BadgeModel badgeModel,Context context){
            Glide.with(context)
                    .load(badgeModel.getBadgeIcon())
                    .into(badgeIcon);
            badgeName.setText(badgeModel.getBadgeName());
        }
    }

}
