package com.anikrakib.blooddonation.Fragment.Profile;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.Activity.MainActivity;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.databinding.FragmentContactBinding;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class ContactFragment extends Fragment {
    FragmentContactBinding fragmentContactBinding;
    FirebaseFirestore database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentContactBinding = FragmentContactBinding.inflate(getLayoutInflater());

        database = FirebaseFirestore.getInstance();

        database.collection(HelperClass.USERS_COLLECTION_NAME)
                .document(MainActivity.userName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null) {

                               fragmentContactBinding.email.setText(document.getString("email"));
                               fragmentContactBinding.phoneNo.setText(document.getString("phoneNo"));
                               fragmentContactBinding.altPhoneNo.setText(document.getString("altPhoneNo"));
                               fragmentContactBinding.social.setText(document.getString("socialMediaLink"));
                               fragmentContactBinding.address.setText(document.getString("streetAddress")+","+document.getString("city")+","+document.getString("postalCode"));
                            }

                        } else {
                            Log.d("userInfo", "No such document");
                        }
                    }

                });

        return fragmentContactBinding.getRoot();
    }
}