package com.anikrakib.blooddonation.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.databinding.FragmentUploadImageBinding;

public class UploadImageFragment extends Fragment {
    FragmentUploadImageBinding fragmentUploadImageBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentUploadImageBinding = FragmentUploadImageBinding.inflate(getLayoutInflater());



        return fragmentUploadImageBinding.getRoot();
    }
}