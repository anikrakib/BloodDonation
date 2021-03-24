package com.anikrakib.blooddonation.Fragment.Profile;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.Activity.MainActivity;
import com.anikrakib.blooddonation.Adapter.UrgentRequestAdapter;
import com.anikrakib.blooddonation.Model.BloodRequestModel;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.databinding.FragmentRequestsBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RequestsFragment extends Fragment {
    FragmentRequestsBinding fragmentRequestsBinding;
    FirebaseFirestore database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentRequestsBinding = FragmentRequestsBinding.inflate(getLayoutInflater());

        database = FirebaseFirestore.getInstance();

        final List<BloodRequestModel> bloodRequestModels = new ArrayList<>();
        final UrgentRequestAdapter adapter = new UrgentRequestAdapter(getContext(), bloodRequestModels);

        database.collection(HelperClass.USERS_COLLECTION_NAME)
                .document(MainActivity.userName)
                .collection(HelperClass.REQUEST_FOR_BLOOD)
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


        fragmentRequestsBinding.requestedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentRequestsBinding.requestedRecyclerView.setAdapter(adapter);

        return fragmentRequestsBinding.getRoot();
    }
}