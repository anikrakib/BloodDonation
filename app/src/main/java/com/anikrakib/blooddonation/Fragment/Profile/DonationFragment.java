package com.anikrakib.blooddonation.Fragment.Profile;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.databinding.FragmentMainProfileDataBinding;

public class DonationFragment extends Fragment {
    FragmentMainProfileDataBinding fragmentMainProfileDataBinding;

    public DonationFragment(){

    }

    public DonationFragment(FragmentMainProfileDataBinding fragmentMainProfileDataBinding){
        this.fragmentMainProfileDataBinding = fragmentMainProfileDataBinding;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentMainProfileDataBinding.badgeCardView.setCardBackgroundColor(Color.WHITE);
        fragmentMainProfileDataBinding.infoAndContactCardview.setCardBackgroundColor(Color.WHITE);

        return inflater.inflate(R.layout.fragment_donation, container, false);
    }
}