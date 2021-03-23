package com.anikrakib.blooddonation.Fragment.Notification;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.Activity.MainActivity;
import com.anikrakib.blooddonation.Adapter.Notification.NotificationAdapter;
import com.anikrakib.blooddonation.Adapter.Profile.BadgesAdapter;
import com.anikrakib.blooddonation.Model.BadgeModel;
import com.anikrakib.blooddonation.Model.NotificationModel;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.databinding.FragmentNotificationBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {
    FragmentNotificationBinding fragmentNotificationBinding;
    List<NotificationModel> notificationModels = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentNotificationBinding = FragmentNotificationBinding.inflate(getLayoutInflater());

        notificationModels.add(new NotificationModel("O+",getResources().getString(R.string.hints2),"1 hour ago"));
        notificationModels.add(new NotificationModel("A+",getResources().getString(R.string.hints),"1 day ago"));
        notificationModels.add(new NotificationModel("AB+",getResources().getString(R.string.hints),"1 month ago"));

        final NotificationAdapter adapter = new NotificationAdapter(getContext(), notificationModels);

        fragmentNotificationBinding.notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentNotificationBinding.notificationRecyclerView.setNestedScrollingEnabled(false);
        fragmentNotificationBinding.notificationRecyclerView.setAdapter(adapter);


        return fragmentNotificationBinding.getRoot();
    }
}