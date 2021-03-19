package com.anikrakib.blooddonation.Fragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.anikrakib.blooddonation.Activity.SignUpActivity;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.databinding.FragmentBloodGroupBinding;
import com.anikrakib.blooddonation.databinding.FragmentWeightBinding;

public class WeightFragment extends Fragment {
    FragmentWeightBinding fragmentWeightBinding;
    private static String weight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentWeightBinding = FragmentWeightBinding.inflate(getLayoutInflater());

        fragmentWeightBinding.greaterThanFiftyKg.setOnClickListener(v->{
            weight = fragmentWeightBinding.greaterThanFifty.getText().toString();
        });

        fragmentWeightBinding.lessThanFiftyKg.setOnClickListener(v->{
            weight = fragmentWeightBinding.lessThanFifty.getText().toString();
        });

        fragmentWeightBinding.nextButton.setOnClickListener(v->{
            Bundle receiveBundle = this.getArguments();
            Bundle sendBundle = new Bundle();

            assert receiveBundle != null;
            sendBundle.putString("email",receiveBundle.getString("email"));
            sendBundle.putString("useName",receiveBundle.getString("userName"));
            sendBundle.putString("password",receiveBundle.getString("userName"));
            sendBundle.putString("phoneNo",receiveBundle.getString("phoneNo"));
            sendBundle.putString("altPhoneNo",receiveBundle.getString("altPhoneNo"));
            sendBundle.putString("social",receiveBundle.getString("social"));
            sendBundle.putString("bloodGroup",receiveBundle.getString("bloodGroup"));
            sendBundle.putString("weight",weight);

            GenderFragment genderFragment = new GenderFragment();
            genderFragment.setArguments(sendBundle);
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction()
                    .replace(SignUpActivity.activitySignUpBinding.container.getId(),genderFragment)
                    .commit();
        });

        return fragmentWeightBinding.getRoot();
    }
}