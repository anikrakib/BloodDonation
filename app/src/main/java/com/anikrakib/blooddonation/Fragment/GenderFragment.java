package com.anikrakib.blooddonation.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.Activity.SignUpActivity;
import com.anikrakib.blooddonation.Model.EnumClass.Gender;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.databinding.FragmentGenderBinding;

public class GenderFragment extends Fragment {
    FragmentGenderBinding fragmentGenderBinding;
    private static String gender = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentGenderBinding = FragmentGenderBinding.inflate(getLayoutInflater());

        fragmentGenderBinding.genderMale.setOnClickListener(v->{
            gender = Gender.MALE.getGender();
        });

        fragmentGenderBinding.genderFemale.setOnClickListener(v->{
            gender = Gender.FEMALE.getGender();
        });

        fragmentGenderBinding.nextButton.setOnClickListener(v->{

            if(gender.isEmpty()){
                HelperClass.snackBar("Select Your Gender!", R.color.primaryColor,getContext());
            }else {
                SignUpActivity.userDataModel.setGender(gender);

                Log.d("data","Gender:--"+SignUpActivity.userDataModel.toString());

                AddressFragment addressFragment = new AddressFragment();
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction()
                        .replace(SignUpActivity.activitySignUpBinding.container.getId(),addressFragment)
                        .addToBackStack(null)
                        .commit();
            }

        });

        return fragmentGenderBinding.getRoot();
    }
}