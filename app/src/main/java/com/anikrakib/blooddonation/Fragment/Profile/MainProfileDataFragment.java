package com.anikrakib.blooddonation.Fragment.Profile;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anikrakib.blooddonation.Adapter.Profile.BadgesAdapter;
import com.anikrakib.blooddonation.Adapter.Profile.ViewDonationRequestsPagerAdapter;
import com.anikrakib.blooddonation.Adapter.Profile.ViewInfoContatctPagerAdapter;
import com.anikrakib.blooddonation.Model.BadgeModel;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.Utills.SharedPreferencesHelper;
import com.anikrakib.blooddonation.databinding.FragmentMainProfileDataBinding;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainProfileDataFragment extends Fragment {
    FragmentMainProfileDataBinding fragmentMainProfileDataBinding;
    FirebaseFirestore database;
    FirebaseAuth auth;
    private static String userName = "";
    Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMainProfileDataBinding = FragmentMainProfileDataBinding.inflate(getLayoutInflater());


        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        dialog = new  Dialog(getContext());

        /////*     initialize ViewPager   */////
        ViewDonationRequestsPagerAdapter viewDonationRequestsPagerAdapter = new ViewDonationRequestsPagerAdapter(
                Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager(),fragmentMainProfileDataBinding);

        ViewInfoContatctPagerAdapter ViewInfoContatctPagerAdapter = new ViewInfoContatctPagerAdapter(
                getActivity()
                        .getSupportFragmentManager(),fragmentMainProfileDataBinding);

        fragmentMainProfileDataBinding.viewPager.setAdapter(viewDonationRequestsPagerAdapter);
        fragmentMainProfileDataBinding.slidingTabs.setupWithViewPager(fragmentMainProfileDataBinding.viewPager);
        fragmentMainProfileDataBinding.slidingTabs.setTabRippleColor(null);
        fragmentMainProfileDataBinding.slidingTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("tab","Selected");
                fragmentMainProfileDataBinding.slidingTabs.setSelectedTabIndicatorColor(Color.parseColor("#c70100"));
                //fragmentMainProfileDataBinding.slidingTabs.setTabTextColors(ColorStateList.valueOf(Color.parseColor("#c70100")));
                fragmentMainProfileDataBinding.badgeRecyclerView.setVisibility(View.GONE);
                fragmentMainProfileDataBinding.viewPager2.setVisibility(View.GONE);
                fragmentMainProfileDataBinding.viewPager.setVisibility(View.VISIBLE);
                fragmentMainProfileDataBinding.badgeText.setGravity(Gravity.START | Gravity.CENTER);
                fragmentMainProfileDataBinding.badgeCardView.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#f6f7f9")));
                fragmentMainProfileDataBinding.infoAndContactCardview.setCardBackgroundColor(Color.parseColor("#f6f7f9"));//#f6f7f9
                fragmentMainProfileDataBinding.donationAndRequestedBackground.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                fragmentMainProfileDataBinding.badgeCardView.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#f6f7f9")));
                fragmentMainProfileDataBinding.infoAndContactCardview.setCardBackgroundColor(Color.parseColor("#f6f7f9"));//#f6f7f9
                fragmentMainProfileDataBinding.donationAndRequestedBackground.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        fragmentMainProfileDataBinding.viewPager2.setAdapter(ViewInfoContatctPagerAdapter);
        fragmentMainProfileDataBinding.slidingTabs2.setupWithViewPager(fragmentMainProfileDataBinding.viewPager2);
        fragmentMainProfileDataBinding.slidingTabs2.setTabRippleColor(null);
        fragmentMainProfileDataBinding.slidingTabs2.setEnabled(false);
        fragmentMainProfileDataBinding.slidingTabs2.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("tab","Selected");
                fragmentMainProfileDataBinding.slidingTabs2.setSelectedTabIndicatorColor(Color.parseColor("#c70100"));
                //fragmentMainProfileDataBinding.slidingTabs.setTabTextColors(ColorStateList.valueOf(Color.parseColor("#c70100")));
                fragmentMainProfileDataBinding.badgeRecyclerView.setVisibility(View.GONE);
                fragmentMainProfileDataBinding.viewPager2.setVisibility(View.VISIBLE);
                fragmentMainProfileDataBinding.viewPager.setVisibility(View.GONE);
                fragmentMainProfileDataBinding.badgeText.setGravity(Gravity.START | Gravity.CENTER);
                fragmentMainProfileDataBinding.badgeCardView.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#f6f7f9")));
                fragmentMainProfileDataBinding.infoAndContactCardview.setCardBackgroundColor(Color.WHITE);//#f6f7f9
                fragmentMainProfileDataBinding.donationAndRequestedBackground.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#f6f7f9")));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                fragmentMainProfileDataBinding.badgeCardView.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#f6f7f9")));
                fragmentMainProfileDataBinding.donationAndRequestedBackground.setCardBackgroundColor(Color.parseColor("#f6f7f9"));//#f6f7f9
                fragmentMainProfileDataBinding.infoAndContactCardview.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fragmentMainProfileDataBinding.badgeText.setOnClickListener(v->{
            fragmentMainProfileDataBinding.infoAndContactCardview.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#f6f7f9")));
            fragmentMainProfileDataBinding.donationAndRequestedBackground.setCardBackgroundColor(Color.parseColor("#f6f7f9"));//#f6f7f9
            fragmentMainProfileDataBinding.badgeCardView.setCardBackgroundColor(Color.WHITE);
            fragmentMainProfileDataBinding.badgeRecyclerView.setVisibility(View.VISIBLE);
            fragmentMainProfileDataBinding.viewPager2.setVisibility(View.GONE);
            fragmentMainProfileDataBinding.viewPager.setVisibility(View.GONE);
            fragmentMainProfileDataBinding.badgeText.setGravity(Gravity.CENTER);

        });


        final List<BadgeModel> badgeModels = new ArrayList<>();

        final BadgesAdapter adapter = new BadgesAdapter(getContext(), badgeModels);

        database.collection("badges")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        badgeModels.clear();
                        assert value != null;
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            BadgeModel model = snapshot.toObject(BadgeModel.class);
                            badgeModels.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

        fragmentMainProfileDataBinding.badgeRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        fragmentMainProfileDataBinding.badgeRecyclerView.setNestedScrollingEnabled(false);
        fragmentMainProfileDataBinding.badgeRecyclerView.setAdapter(adapter);

        getUserName();

        return fragmentMainProfileDataBinding.getRoot();
    }


    public void getUserName(){

        database.collection("userName")
                .document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null) {
                                userName = document.getString("userName");
                               // Log.d("asdcsvdsv", userName);
                                database.collection(HelperClass.USERS_COLLECTION_NAME)
                                        .document(userName)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @SuppressLint("SetTextI18n")
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document != null) {
                                                        String userProfilePic = document.getString("userProfilePic");
                                                        String userName = document.getString("userName");
                                                        String userFullName = document.getString("userFullName");
                                                        String bloodGroup = document.getString("bloodGroup");

                                                        Glide.with(Objects.requireNonNull(getActivity()))
                                                                .load(userProfilePic)
                                                                .into(fragmentMainProfileDataBinding.userImage);
                                                        fragmentMainProfileDataBinding.fullName.setText(userFullName);
                                                        fragmentMainProfileDataBinding.BloodGroup.setText(bloodGroup);
                                                    }

                                                } else {
                                                    Log.d("userInfo", "No such document");
                                                }
                                            }

                                        });

                            } else {
                                Log.d("userInfo", "No such document");
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

}