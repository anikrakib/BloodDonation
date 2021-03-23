package com.anikrakib.blooddonation.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.anikrakib.blooddonation.Adapter.Notification.NotificationAdapter;
import com.anikrakib.blooddonation.Adapter.UrgentRequestAdapter;
import com.anikrakib.blooddonation.Model.BadgeModel;
import com.anikrakib.blooddonation.Model.BloodRequestModel;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.databinding.ActivityUrgentRequestBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UrgentRequestActivity extends AppCompatActivity {
    ActivityUrgentRequestBinding activityUrgentRequestBinding;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUrgentRequestBinding = ActivityUrgentRequestBinding.inflate(getLayoutInflater());
        setContentView(activityUrgentRequestBinding.getRoot());

        database = FirebaseFirestore.getInstance();

        final List<BloodRequestModel> bloodRequestModels = new ArrayList<>();
        final UrgentRequestAdapter adapter = new UrgentRequestAdapter(getApplicationContext(), bloodRequestModels);

        database.collection(HelperClass.REQUEST_FOR_BLOOD)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        bloodRequestModels.clear();
                        assert value != null;
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            BloodRequestModel model = snapshot.toObject(BloodRequestModel.class);
                            bloodRequestModels.add(model);

                        }
                        adapter.notifyDataSetChanged();
                    }
                });


        activityUrgentRequestBinding.requestedRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        activityUrgentRequestBinding.requestedRecyclerView.setAdapter(adapter);

    }
}