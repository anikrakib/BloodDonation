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
import com.anikrakib.blooddonation.Model.UserDataModel;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.Interface.DetailFragmentListener;
import com.anikrakib.blooddonation.databinding.FragmentAboutYouBinding;

public class AboutYouFragment extends Fragment {
    private static final String TAG = "AboutYouFragment";
    FragmentAboutYouBinding fragmentAboutYouBinding;
    DetailFragmentListener detailFragmentListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAboutYouBinding = FragmentAboutYouBinding.inflate(getLayoutInflater());

        fragmentAboutYouBinding.nextButton.setOnClickListener(v->{

            SignUpActivity.userDataModel.setUserName(fragmentAboutYouBinding.userName.getText().toString().trim());
            SignUpActivity.userDataModel.setPassword(fragmentAboutYouBinding.password.getText().toString());
            SignUpActivity.userDataModel.setEmail(fragmentAboutYouBinding.email.getText().toString());
            Log.d("data","About:--"+SignUpActivity.userDataModel.toString());

            ContactInformationFragment contactInformationFragment = new ContactInformationFragment();
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction()
                    .replace(SignUpActivity.activitySignUpBinding.container.getId(),contactInformationFragment)
                    .commit();
        });

        return fragmentAboutYouBinding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        detailFragmentListener = (DetailFragmentListener) getActivity();
    }
}