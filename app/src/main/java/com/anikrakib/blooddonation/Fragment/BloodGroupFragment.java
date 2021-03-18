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
import com.anikrakib.blooddonation.databinding.FragmentBloodGroupBinding;

import java.util.ArrayList;
import java.util.List;

public class BloodGroupFragment extends Fragment implements BloodGroupAdapter.OnBloodGroupCLickListener {
    FragmentBloodGroupBinding fragmentBloodGroupBinding;
    private static String bloodGroup;

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
            Bundle receiveBundle = this.getArguments();
            Bundle sendBundle = new Bundle();

            assert receiveBundle != null;
            sendBundle.putString("email",receiveBundle.getString("email"));
            sendBundle.putString("useName",receiveBundle.getString("userName"));
            sendBundle.putString("password",receiveBundle.getString("userName"));
            sendBundle.putString("phoneNo",receiveBundle.getString("phoneNo"));
            sendBundle.putString("altPhoneNo",receiveBundle.getString("altPhoneNo"));
            sendBundle.putString("social",receiveBundle.getString("social"));
            sendBundle.putString("bloodGroup",bloodGroup);

            for (String key: sendBundle.keySet())
            {
                Log.d ("sendBundle", key + " is a key in the bundle");
            }

            for (String key: receiveBundle.keySet())
            {
                Log.d ("receiveBundle", key + " is a key in the bundle");
            }

            WeightFragment weightFragment = new WeightFragment();
            weightFragment.setArguments(sendBundle);
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction()
                    .replace(SignUpActivity.activitySignUpBinding.container.getId(),weightFragment)
                    .commit();
        });


        return fragmentBloodGroupBinding.getRoot();
    }

    @Override
    public void onBloodGroupCLickListener(String bloodGroup) {
        Log.d("position",bloodGroup);
        BloodGroupFragment.bloodGroup = bloodGroup;
    }
}