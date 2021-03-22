package com.anikrakib.blooddonation.Fragment.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.databinding.FragmentContactBinding;

public class ContactFragment extends Fragment {
    FragmentContactBinding fragmentContactBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentContactBinding = FragmentContactBinding.inflate(getLayoutInflater());



        return fragmentContactBinding.getRoot();
    }
}