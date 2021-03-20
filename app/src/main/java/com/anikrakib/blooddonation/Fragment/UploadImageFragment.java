package com.anikrakib.blooddonation.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.Activity.SignUpActivity;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.databinding.FragmentUploadImageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UploadImageFragment extends Fragment {
    FragmentUploadImageBinding fragmentUploadImageBinding;
    FirebaseAuth auth;
    FirebaseFirestore database;
    ProgressDialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentUploadImageBinding = FragmentUploadImageBinding.inflate(getLayoutInflater());

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Create an account");
        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

        fragmentUploadImageBinding.startJourneyButton.setOnClickListener(v->{

            if(SignUpActivity.userDataModel.getEmail().isEmpty()){
                HelperClass.snackBar("Your Email is empty", getResources().getColor(R.color.colorPrimary),getContext());
            }else {
                if(SignUpActivity.userDataModel.getPassword().isEmpty()) {
                    HelperClass.snackBar("Your password is empty", getResources().getColor(R.color.colorPrimary),getContext());
                }else {
                    createUser();
                }
            }

        });

        return fragmentUploadImageBinding.getRoot();
    }

    private void createUser(){
        String email, pass, name, referCode;

//        final User user = new User(name, email, Utils.getHash(pass));
//        final PuzzleBestScore puzzleBestScore = new PuzzleBestScore();
//        final NumberPuzzleBestScoreModel numberPuzzleBestScoreModel = new NumberPuzzleBestScoreModel();



        dialog.show();
        auth.createUserWithEmailAndPassword(SignUpActivity.userDataModel.getEmail(), SignUpActivity.userDataModel.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    String uid = Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getUser()).getUid();
                    // create user Data Table
                    database.collection("users")
                            .document(SignUpActivity.userDataModel.getUserName())
                            .set(SignUpActivity.userDataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                dialog.dismiss();
//                                startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
//                                finish();
                            } else {
                                HelperClass.snackBar(Objects.requireNonNull(task.getException()).getLocalizedMessage(), R.color.colorPrimary,getContext());
                            }
                        }
                    });

                } else {
                    dialog.dismiss();
                    HelperClass.snackBar(Objects.requireNonNull(task.getException()).getLocalizedMessage(),R.color.colorPrimary,getContext());
                }
            }
        });

    }
}