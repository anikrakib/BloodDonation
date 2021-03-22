package com.anikrakib.blooddonation.Fragment.Profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.anikrakib.blooddonation.Activity.SignInActivity;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.Utills.SharedPreferencesHelper;
import com.anikrakib.blooddonation.databinding.FragmentQRCodeBinding;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;


public class QRCodeFragment extends Fragment {
    FragmentQRCodeBinding fragmentQRCodeBinding;
    public static String userName = "";
    FirebaseFirestore database;
    FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentQRCodeBinding = FragmentQRCodeBinding.inflate(getLayoutInflater());
        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        getUserField();

        fragmentQRCodeBinding.save.setOnClickListener(v->{

            Map<String, Object> updateInfo = new HashMap<>();
            updateInfo.put("userFullName",fragmentQRCodeBinding.userName.getText().toString());

            database.collection(HelperClass.USERS_COLLECTION_NAME)
                    .document(userName)
                    .update(updateInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    fragmentQRCodeBinding.userFullName.setText(fragmentQRCodeBinding.userName.getText().toString());
                    fragmentQRCodeBinding.fullNameCardView.setVisibility(View.GONE);
                    fragmentQRCodeBinding.userFullName.setVisibility(View.VISIBLE);
                    fragmentQRCodeBinding.save.setVisibility(View.GONE);
                }
            });

        });

        fragmentQRCodeBinding.signOut.setOnClickListener(v->{
            auth.signOut();
            startActivity(new Intent(getContext(), SignInActivity.class));
            Objects.requireNonNull(getActivity()).finish();
        });

        return fragmentQRCodeBinding.getRoot();
    }

    public void generateQrCode(String imageUrl){
//        // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
        QRGEncoder qrgEncoder = new QRGEncoder(imageUrl, null, QRGContents.Type.TEXT, 800);
        qrgEncoder.setColorBlack(Color.parseColor("#747474"));
        qrgEncoder.setColorWhite(Color.WHITE);
        // Getting QR-Code as Bitmap
        Bitmap bitmap = qrgEncoder.getBitmap();
        // Setting Bitmap to ImageView
        fragmentQRCodeBinding.qrCode.setImageBitmap(bitmap);
    }

    public void getUserField(){
        database.collection("userName")
                .document(auth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null) {
                                userName = document.getString("userName");
                                assert userName != null;
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
                                                        String imageUrl = document.getString("userProfilePic");
                                                        String userFullName = document.getString("userFullName");
                                                        String userID = document.getString("userId");
                                                        if(Objects.requireNonNull(userFullName).isEmpty()){
                                                            fragmentQRCodeBinding.fullNameCardView.setVisibility(View.VISIBLE);
                                                            fragmentQRCodeBinding.userFullName.setVisibility(View.GONE);
                                                            fragmentQRCodeBinding.save.setVisibility(View.VISIBLE);
                                                        }else {
                                                            fragmentQRCodeBinding.userFullName.setText(userFullName);
                                                            fragmentQRCodeBinding.fullNameCardView.setVisibility(View.GONE);
                                                            fragmentQRCodeBinding.userFullName.setVisibility(View.VISIBLE);
                                                            fragmentQRCodeBinding.save.setVisibility(View.GONE);
                                                        }
                                                        fragmentQRCodeBinding.userId.setText("ID\n"+userID);
                                                        generateQrCode(imageUrl);
                                                    } else {
                                                        Log.d("userInfo", "No such document");
                                                    }
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