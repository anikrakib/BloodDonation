package com.anikrakib.blooddonation.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.anikrakib.blooddonation.Activity.WelComeActivity;
import com.anikrakib.blooddonation.Model.OnBoardingItem;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.SharedPreferencesHelper;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OmBoardingViewHolder> {
    List<OnBoardingItem> onBoardingItems;
    ViewPager2 onBoardingViewPager;
    Context context;

    public OnBoardingAdapter(Context context, List<OnBoardingItem> onBoardingItems, ViewPager2 onBoardingViewPager) {
        this.onBoardingItems = onBoardingItems;
        this.onBoardingViewPager = onBoardingViewPager;
        this.context = context;
    }

    @NonNull
    @Override
    public OmBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OmBoardingViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(
                                R.layout.item_container_onboard,
                                parent,
                                false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OmBoardingViewHolder holder, int position) {
        holder.setOnBoardingData(onBoardingItems.get(position),position,onBoardingItems.size());
        holder.button.setOnClickListener(v->{
            if(position<onBoardingItems.size()){
                onBoardingViewPager.setCurrentItem(position+1);
            }
            if(holder.button.getText().toString().equals("Start")){
                SharedPreferencesHelper.setOnBoardCompleteOrNot(context,true);
                Intent intent = new Intent(context, WelComeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    public static class OmBoardingViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardView;
        TextView textView;
        AppCompatButton button;
        public OmBoardingViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.onBoardCardView);
            textView = itemView.findViewById(R.id.onBoardName);
            button = itemView.findViewById(R.id.onBoardItemButton);
        }

        @SuppressLint("SetTextI18n")
        void setOnBoardingData(OnBoardingItem onBoardingItem, int position, int size){
            imageView.setImageResource(onBoardingItem.getImage());
            cardView.setCardBackgroundColor(Color.parseColor(onBoardingItem.getColor()));
            textView.setText(onBoardingItem.getName());
            if(position == (size-1)) button.setText("Start");
            else button.setText("Next");
        }
    }
}
