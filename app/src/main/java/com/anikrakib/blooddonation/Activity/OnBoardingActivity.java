package com.anikrakib.blooddonation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.anikrakib.blooddonation.Adapter.OnBoardingAdapter;
import com.anikrakib.blooddonation.Model.OnBoardingItem;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.SharedPreferencesHelper;
import com.anikrakib.blooddonation.databinding.ActivityOnBoardingBinding;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {
    private OnBoardingAdapter onBoardingAdapter;
    ActivityOnBoardingBinding activityOnBoardingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOnBoardingBinding = ActivityOnBoardingBinding.inflate(getLayoutInflater());
        setContentView(activityOnBoardingBinding.getRoot());

        if(SharedPreferencesHelper.getOnBoardCompleteOrNot(getApplicationContext())){
            startActivity(new Intent(getApplicationContext(),WelComeActivity.class));
            finish();
        }else {
            setViewPager();
        }
    }

    private void setViewPager(){

        List<OnBoardingItem> onBoardingItems = new ArrayList<>();

        onBoardingItems.add(new OnBoardingItem(R.drawable.blood_donate,"Donate Blood", "#d93d65"));
        onBoardingItems.add(new OnBoardingItem(R.drawable.save_life,"Save Life", "#2e29dd"));

        onBoardingAdapter = new OnBoardingAdapter(getApplicationContext(),onBoardingItems,activityOnBoardingBinding.onBoardingViewPager);

        activityOnBoardingBinding.onBoardingViewPager.setAdapter(onBoardingAdapter);
        setUpOnBoardingIndicator();
        setCurrentOnBoardingIndicator(0);
        activityOnBoardingBinding.onBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicator(position);
            }
        });
    }

    private void setUpOnBoardingIndicator(){
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(
                    ContextCompat.getDrawable(getApplicationContext(),R.drawable.on_board_indicator_inactive)
            );
            indicators[i].setLayoutParams(layoutParams);
            activityOnBoardingBinding.layOutOnBoardingIndicator.addView(indicators[i]);
        }
    }

    private void setCurrentOnBoardingIndicator(int index){
        int childCount = activityOnBoardingBinding.layOutOnBoardingIndicator.getChildCount();
        for(int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView) activityOnBoardingBinding.layOutOnBoardingIndicator.getChildAt(i);
            if(i ==index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.on_board_indicator_active)
                );
            }else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.on_board_indicator_inactive)
                );
            }
        }
    }
}