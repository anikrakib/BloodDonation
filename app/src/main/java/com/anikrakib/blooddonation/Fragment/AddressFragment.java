package com.anikrakib.blooddonation.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.Activity.SignUpActivity;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.databinding.FragmentAddressBinding;

public class AddressFragment extends Fragment {
    FragmentAddressBinding fragmentAddressBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAddressBinding = FragmentAddressBinding.inflate(getLayoutInflater());

        fragmentAddressBinding.nextButton.setOnClickListener(v->{

            if(fragmentAddressBinding.streetAddress.getText().toString().isEmpty()){
                HelperClass.snackBar("Street Address Required!", R.color.primaryColor,getContext());
            }else {
                if(fragmentAddressBinding.city.getText().toString().isEmpty()){
                    HelperClass.snackBar("City Required!", R.color.primaryColor,getContext());
                }else {
                    if(fragmentAddressBinding.postalCode.getText().toString().isEmpty()){
                        HelperClass.snackBar("Postal Code Required!", R.color.primaryColor,getContext());
                    }else {
                        SignUpActivity.userDataModel.setStreetAddress(fragmentAddressBinding.streetAddress.getText().toString());
                        SignUpActivity.userDataModel.setCity(fragmentAddressBinding.city.getText().toString());
                        SignUpActivity.userDataModel.setPostalCode(fragmentAddressBinding.postalCode.getText().toString());
                        Log.d("data","Address:--"+SignUpActivity.userDataModel.toString());

                        AgeFragment ageFragment = new AgeFragment();
                        assert getFragmentManager() != null;
                        getFragmentManager().beginTransaction()
                                .replace(SignUpActivity.activitySignUpBinding.container.getId(),ageFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            }
        });

        return fragmentAddressBinding.getRoot();
    }
}

/*
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
            sendBundle.putString("gender",receiveBundle.getString("gender"));
            sendBundle.putString("streetAddress",fragmentAddressBinding.streetAddress.getText().toString());
            sendBundle.putString("city",fragmentAddressBinding.city.getText().toString());
            sendBundle.putString("postalCode",fragmentAddressBinding.postalCode.getText().toString());

            AgeFragment ageFragment = new AgeFragment();
            ageFragment.setArguments(sendBundle);
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction()
                    .replace(SignUpActivity.activitySignUpBinding.container.getId(),ageFragment)
                    .commit();
 */