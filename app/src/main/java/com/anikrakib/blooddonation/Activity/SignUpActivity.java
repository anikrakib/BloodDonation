package com.anikrakib.blooddonation.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.anikrakib.blooddonation.Fragment.AboutYouFragment;
import com.anikrakib.blooddonation.Model.UserDataModel;
import com.anikrakib.blooddonation.Utills.Interface.DetailFragmentListener;
import com.anikrakib.blooddonation.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity implements DetailFragmentListener {
    public static ActivitySignUpBinding activitySignUpBinding;
    final Fragment fragment = new AboutYouFragment();
    Fragment active = fragment;
    final FragmentManager fm = getSupportFragmentManager();
    public static UserDataModel userDataModel = new UserDataModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(activitySignUpBinding.getRoot());

        //fm.beginTransaction().add(activitySignUpBinding.container.getId(), fragment, "2").hide(fragment2).commit();
        fm.beginTransaction().add(activitySignUpBinding.container.getId(),fragment, "AboutYouFragment").commit();

    }

    @Override
    public void onDetailsFragment(UserDataModel userDataModel) {
        Log.d("userModel",userDataModel.toString());
    }
}