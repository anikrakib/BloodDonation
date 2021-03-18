package com.anikrakib.blooddonation.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.databinding.ActivityWelcomeBinding;

public class WelComeActivity extends AppCompatActivity {
    ActivityWelcomeBinding activityWelcomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWelcomeBinding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(activityWelcomeBinding.getRoot());

        activityWelcomeBinding.startJourneyButton.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            finish();
        });
    }
}