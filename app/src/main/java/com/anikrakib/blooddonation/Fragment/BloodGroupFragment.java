package com.anikrakib.blooddonation.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.Activity.SignUpActivity;
import com.anikrakib.blooddonation.Adapter.BloodGroupAdapter;
import com.anikrakib.blooddonation.Model.BloodGroupModel;
import com.anikrakib.blooddonation.Model.EnumClass.BloodGroup;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.databinding.FragmentBloodGroupBinding;

import java.util.ArrayList;
import java.util.List;

public class BloodGroupFragment extends Fragment implements BloodGroupAdapter.OnBloodGroupCLickListener {
    FragmentBloodGroupBinding fragmentBloodGroupBinding;
    private static String bloodGroup = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBloodGroupBinding = FragmentBloodGroupBinding.inflate(getLayoutInflater());

        final List<BloodGroupModel> bloodGroupModels = new ArrayList<>();

        final BloodGroupAdapter adapter = new BloodGroupAdapter(getActivity(), bloodGroupModels,this);

        bloodGroupModels.add(new BloodGroupModel(BloodGroup.A_POS.getGroup()));
        bloodGroupModels.add(new BloodGroupModel(BloodGroup.A_NEG.getGroup()));
        bloodGroupModels.add(new BloodGroupModel(BloodGroup.B_POS.getGroup()));
        bloodGroupModels.add(new BloodGroupModel(BloodGroup.B_NEG.getGroup()));
        bloodGroupModels.add(new BloodGroupModel(BloodGroup.O_POS.getGroup()));
        bloodGroupModels.add(new BloodGroupModel(BloodGroup.O_NEG.getGroup()));
        bloodGroupModels.add(new BloodGroupModel(BloodGroup.AB_POS.getGroup()));
        bloodGroupModels.add(new BloodGroupModel(BloodGroup.AB_NEG.getGroup()));

        fragmentBloodGroupBinding.bloodGroupRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        fragmentBloodGroupBinding.bloodGroupRecyclerView.setNestedScrollingEnabled(false);
        fragmentBloodGroupBinding.bloodGroupRecyclerView.setAdapter(adapter);

        fragmentBloodGroupBinding.nextButton.setOnClickListener(v->{

            if(bloodGroup.isEmpty()){
                HelperClass.snackBar("Select Your Blood Group!", R.color.primaryColor,getContext());
            }else {
                SignUpActivity.userDataModel.setBloodGroup(bloodGroup);
                Log.d("data","BloodGroup:--"+SignUpActivity.userDataModel.toString());


                WeightFragment weightFragment = new WeightFragment();
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction()
                        .replace(SignUpActivity.activitySignUpBinding.container.getId(),weightFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        return fragmentBloodGroupBinding.getRoot();
    }

    @Override
    public void onBloodGroupCLickListener(String bloodGroup) {
        Log.d("position",bloodGroup);
        BloodGroupFragment.bloodGroup = bloodGroup;
    }
}