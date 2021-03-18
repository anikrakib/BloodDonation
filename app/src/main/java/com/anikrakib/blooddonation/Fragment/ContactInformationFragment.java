package com.anikrakib.blooddonation.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.Activity.SignUpActivity;
import com.anikrakib.blooddonation.Model.EnumClass.BloodGroup;
import com.anikrakib.blooddonation.Model.UserDataModel;
import com.anikrakib.blooddonation.R;
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
//            UserDataModel userDataModel = new UserDataModel();
//            Bundle bundle = this.getArguments();
//
//            userDataModel.setPhoneNo(fragmentContactInformationBinding.phoneNo.getText().toString());
//            userDataModel.setAltPhoneNo(fragmentContactInformationBinding.altPhoneNo.getText().toString());
//            userDataModel.setSocialMediaLink(fragmentContactInformationBinding.social.getText().toString());
//            assert bundle != null;
//            userDataModel.setEmail(bundle.getString("email"));
//            userDataModel.setUserName(bundle.getString("userName"));
//            userDataModel.setPassword(bundle.getString("password"));
//            detailFragmentListener.onDetailsFragment(userDataModel);

            Bundle receiveBundle = this.getArguments();
            Bundle sendBundle = new Bundle();

            assert receiveBundle != null;
            sendBundle.putString("email",receiveBundle.getString("email"));
            sendBundle.putString("useName",receiveBundle.getString("userName"));
            sendBundle.putString("password",receiveBundle.getString("userName"));
            sendBundle.putString("phoneNo",fragmentContactInformationBinding.phoneNo.getText().toString());
            sendBundle.putString("altPhoneNo",fragmentContactInformationBinding.altPhoneNo.getText().toString());
            sendBundle.putString("social",fragmentContactInformationBinding.social.getText().toString());

            BloodGroupFragment bloodGroupFragment = new BloodGroupFragment();
            bloodGroupFragment.setArguments(sendBundle);
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction()
                    .replace(SignUpActivity.activitySignUpBinding.container.getId(),bloodGroupFragment)
                    .commit();
        });


        return fragmentContactInformationBinding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        detailFragmentListener = (DetailFragmentListener) getActivity();
    }
}