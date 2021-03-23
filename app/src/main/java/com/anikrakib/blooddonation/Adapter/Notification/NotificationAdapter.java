package com.anikrakib.blooddonation.Adapter.Notification;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.anikrakib.blooddonation.Model.BadgeModel;
import com.anikrakib.blooddonation.Model.NotificationModel;
import com.anikrakib.blooddonation.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    List<NotificationModel> notificationModels;
    Context context;

    public NotificationAdapter(Context context, List<NotificationModel> notificationModels) {
        this.notificationModels = notificationModels;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(
                                R.layout.item_notofication,
                                parent,
                                false)
        );
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.setBadgeData(notificationModels.get(position));
    }


    @Override
    public int getItemCount() {
        return notificationModels.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView bloodGroup,time,notificationBody;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            bloodGroup = itemView.findViewById(R.id.bloodGroup);
            time = itemView.findViewById(R.id.time);
            notificationBody = itemView.findViewById(R.id.notificationBody);
        }

        @SuppressLint("SetTextI18n")
        void setBadgeData(NotificationModel notificationModel){

            bloodGroup.setText(notificationModel.getBloodGroup());
            notificationBody.setText(Html.fromHtml(notificationModel.getNotificationText()), TextView.BufferType.SPANNABLE);
            time.setText(notificationModel.getTime());
        }
    }

}
