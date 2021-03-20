package com.anikrakib.blooddonation.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.Activity.SignUpActivity;
import com.anikrakib.blooddonation.Model.EnumClass.BloodGroup;
import com.anikrakib.blooddonation.Model.UserDataModel;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.Utills.Interface.DetailFragmentListener;
import com.anikrakib.blooddonation.databinding.FragmentContactInformationBinding;

public class ContactInformationFragment extends Fragment {
    FragmentContactInformationBinding fragmentContactInformationBinding;
    DetailFragmentListener detailFragmentListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentContactInformationBinding = FragmentContactInformationBinding.inflate(getLayoutInflater());

        fragmentContactInformationBinding.nextButton.setOnClickListener(v->{

            if(fragmentContactInformationBinding.phoneNo.getText().toString().isEmpty()){
                HelperClass.snackBar("Phone Number Required!", R.color.primaryColor,getContext());
            }else {
                SignUpActivity.userDataModel.setPhoneNo(fragmentContactInformationBinding.phoneNo.getText().toString());
                SignUpActivity.userDataModel.setAltPhoneNo(fragmentContactInformationBinding.altPhoneNo.getText().toString());
                SignUpActivity.userDataModel.setSocialMediaLink(fragmentContactInformationBinding.social.getText().toString());
                Log.d("data","Info:--"+SignUpActivity.userDataModel.toString());

                BloodGroupFragment bloodGroupFragment = new BloodGroupFragment();
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction()
                        .replace(SignUpActivity.activitySignUpBinding.container.getId(),bloodGroupFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        return fragmentContactInformationBinding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        detailFragmentListener = (DetailFragmentListener) getActivity();
    }
}