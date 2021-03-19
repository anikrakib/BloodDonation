package com.anikrakib.blooddonation.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.Activity.SignUpActivity;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.databinding.FragmentGenderBinding;

public class GenderFragment extends Fragment {
    FragmentGenderBinding fragmentGenderBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentGenderBinding = FragmentGenderBinding.inflate(getLayoutInflater());

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
        sendBundle.putString("weight",receiveBundle.getString("weight"));

        AddressFragment addressFragment = new AddressFragment();
        addressFragment.setArguments(sendBundle);
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction()
                .replace(SignUpActivity.activitySignUpBinding.container.getId(),addressFragment)
                .commit();


//        Log.d ("data", "email:"+receiveBundle.getString("email"));
//        Log.d ("data", "useName:"+receiveBundle.getString("useName"));
//        Log.d ("data", "password:"+receiveBundle.getString("password"));
//        Log.d ("data", "phoneNo:"+receiveBundle.getString("phoneNo"));
//        Log.d ("data", "altPhoneNo:"+receiveBundle.getString("altPhoneNo"));
//        Log.d ("data", "social:"+receiveBundle.getString("social"));
//        Log.d ("data", "bloodGroup:"+receiveBundle.getString("bloodGroup"));
//        Log.d ("data", "weight:"+receiveBundle.getString("weight"));


        return fragmentGenderBinding.getRoot();
    }
}