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
            SignUpActivity.userDataModel.setWeight(weight);

            Log.d("data","Weight:--"+SignUpActivity.userDataModel.toString());

            GenderFragment genderFragment = new GenderFragment();
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction()
                    .replace(SignUpActivity.activitySignUpBinding.container.getId(),genderFragment)
                    .commit();
        });

        return fragmentWeightBinding.getRoot();
    }
}