package com.anikrakib.blooddonation.Fragment.Home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anikrakib.blooddonation.Activity.BloodRequest;
import com.anikrakib.blooddonation.Activity.MainActivity;
import com.anikrakib.blooddonation.Activity.UrgentRequestActivity;
import com.anikrakib.blooddonation.Adapter.Home.BannerPagerAdapter;
import com.anikrakib.blooddonation.Adapter.Profile.BadgesAdapter;
import com.anikrakib.blooddonation.Model.BadgeModel;
import com.anikrakib.blooddonation.Model.CampaignModel;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.databinding.FragmentHomeBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    FragmentHomeBinding fragmentHomeBinding;
    FirebaseFirestore database;
    BannerPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        database = FirebaseFirestore.getInstance();

        final List<CampaignModel> campaignModels = new ArrayList<>();

        adapter = new BannerPagerAdapter(getContext(), campaignModels);

        database.collection("campaign")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        campaignModels.clear();
                        assert value != null;
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            CampaignModel model = snapshot.toObject(CampaignModel.class);
                            campaignModels.add(model);

                            //badgeModels.add(model);
                            Log.d("data",campaignModels.size()+"");
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

        fragmentHomeBinding.bannerViwPager.setAdapter(adapter);

        fragmentHomeBinding.indicator.setViewPager(fragmentHomeBinding.bannerViwPager);
        // optional
        adapter.registerDataSetObserver(fragmentHomeBinding.indicator.getDataSetObserver());


        Timer timer = new Timer();
        Handler handler = new Handler();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        int i = fragmentHomeBinding.bannerViwPager.getCurrentItem();
                        if(i == campaignModels.size() - 1){
                            i = 0;
                        }else {
                            i++;
                        }
                        fragmentHomeBinding.bannerViwPager.setCurrentItem(i,true);

                    }
                });
            }
        },4000,4000);

        fragmentHomeBinding.postRequest.setOnClickListener(v->{
            startActivity(new Intent(getContext(),BloodRequest.class));
        });

        fragmentHomeBinding.urgentRequest.setOnClickListener(v->{
            startActivity(new Intent(getContext(), UrgentRequestActivity.class));
        });

        return fragmentHomeBinding.getRoot();
    }

}