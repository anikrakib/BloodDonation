package com.anikrakib.blooddonation.Fragment.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.Activity.SignUpActivity;
import com.anikrakib.blooddonation.Fragment.AboutYouFragment;
import com.anikrakib.blooddonation.Fragment.AgeFragment;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.databinding.FragmentProfileBinding;

import java.util.Objects;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding fragmentProfileBinding;
    final Fragment fragment = new MainProfileDataFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProfileBinding = FragmentProfileBinding.inflate(getLayoutInflater());
        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();

        fm.beginTransaction().add(fragmentProfileBinding.profileContainer.getId(),fragment, "MainProfileFragment").commit();

        fragmentProfileBinding.settings.setOnClickListener(v->{
            QRCodeFragment qrCodeFragment = new QRCodeFragment();
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction()
                    .replace(fragmentProfileBinding.profileContainer.getId(),qrCodeFragment)
                    .addToBackStack(null)
                    .commit();
        });

        fragmentProfileBinding.back.setOnClickListener(v->{
            getFragmentManager().beginTransaction()
                    .replace(fragmentProfileBinding.profileContainer.getId(),fragment)
                    .addToBackStack(null)
                    .commit();
        });

        return fragmentProfileBinding.getRoot();
    }
}