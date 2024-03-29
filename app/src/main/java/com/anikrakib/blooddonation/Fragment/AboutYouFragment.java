package com.anikrakib.blooddonation.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.Activity.SignUpActivity;
import com.anikrakib.blooddonation.Model.UserDataModel;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
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

        fragmentAboutYouBinding.showOrHidePassword.setOnClickListener(v->{
            if(fragmentAboutYouBinding.password.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                fragmentAboutYouBinding.showOrHidePassword.setImageResource(R.drawable.ic_red_eye);
                fragmentAboutYouBinding.password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else {
                fragmentAboutYouBinding.showOrHidePassword.setImageResource(R.drawable.ic_hide_password);
                fragmentAboutYouBinding.password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        });

        fragmentAboutYouBinding.nextButton.setOnClickListener(v->{

            if(fragmentAboutYouBinding.userName.getText().toString().isEmpty()){
                HelperClass.snackBar("Username Can't Empty!", R.color.primaryColor,getContext());
            }else {
                if(fragmentAboutYouBinding.email.getText().toString().isEmpty()){
                    HelperClass.snackBar("Email Can't Empty!", R.color.primaryColor,getContext());
                }else {
                    if(!fragmentAboutYouBinding.password.getText().toString().isEmpty() && fragmentAboutYouBinding.password.getText().toString().length() >= 8){
                        SignUpActivity.userDataModel.setUserName(fragmentAboutYouBinding.userName.getText().toString().trim());
                        SignUpActivity.userDataModel.setPassword(fragmentAboutYouBinding.password.getText().toString());
                        SignUpActivity.userDataModel.setEmail(fragmentAboutYouBinding.email.getText().toString());
                        Log.d("data","About:--"+SignUpActivity.userDataModel.toString());

                        ContactInformationFragment contactInformationFragment = new ContactInformationFragment();
                        assert getFragmentManager() != null;
                        getFragmentManager().beginTransaction()
                                .replace(SignUpActivity.activitySignUpBinding.container.getId(),contactInformationFragment)
                                .addToBackStack(null)
                                .commit();
                    }else {
                        HelperClass.snackBar("Password Must be 8 Character!", R.color.primaryColor,getContext());
                    }
                }
            }
        });

        return fragmentAboutYouBinding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        detailFragmentListener = (DetailFragmentListener) getActivity();
    }
}