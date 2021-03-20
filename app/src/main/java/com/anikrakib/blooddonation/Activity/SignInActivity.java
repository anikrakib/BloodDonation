package com.anikrakib.blooddonation.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {
    ActivitySignInBinding activitySignInBinding;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignInBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(activitySignInBinding.getRoot());

        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Signed In....");

        if(auth.getCurrentUser() != null) {
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
            finish();
        }

        activitySignInBinding.signUp.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
        });

        activitySignInBinding.showOrHidePassword.setOnClickListener(v->{
            if(activitySignInBinding.password.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                activitySignInBinding.showOrHidePassword.setImageResource(R.drawable.ic_red_eye);
                activitySignInBinding.password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else {
                activitySignInBinding.showOrHidePassword.setImageResource(R.drawable.ic_hide_password);
                activitySignInBinding.password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        });

        activitySignInBinding.logInButton.setOnClickListener(v->{
            String email, pass;
            email = activitySignInBinding.email.getText().toString();
            pass = activitySignInBinding.password.getText().toString();

            dialog.show();

            if(HelperClass.isValidEmail(email)) {
                Log.d("userInfo","Email: "+email);
                logInWithEmail(email,pass);
            }
            else if (HelperClass.isValidUserName(email)){
                getUserEmailAndPassword(email,pass);
                Log.d("userInfo","UserName: "+email);
            }
            else {
                HelperClass.snackBar("Provided Valid Username Or Email!", R.color.white,SignInActivity.this);
            }

        });
    }

    private void getUserEmailAndPassword(String userName,String password) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();

        database.collection(HelperClass.USERS_COLLECTION_NAME)
                .document(userName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null) {
                                String retrieveEmail = document.getString("email");
                                String retrieveHashPassword = document.getString("password");
                                Log.d("userInfo", "RetrieveEmail: "+retrieveEmail+"\n"+"RetrieveHashPassword: "+retrieveHashPassword);

                                // if password match then login with retrieveEmail and password
                                if(HelperClass.getHash(password).equals(retrieveHashPassword)) logInWithEmail(retrieveEmail,password);
                                else HelperClass.snackBar("Invalid Credentials", R.color.primaryColor,SignInActivity.this);

                            } else {
                                Log.d("userInfo", "No such document");
                            }
                        } else {
                            HelperClass.snackBar(task.getException().getLocalizedMessage(), R.color.primaryColor,SignInActivity.this);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        HelperClass.snackBar("Invalid UserName", R.color.primaryColor,SignInActivity.this);
                    }
                });

    }


    public void logInWithEmail(String email, String password){

        auth.signInWithEmailAndPassword(email, HelperClass.getHash(password)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                dialog.dismiss();
                if(task.isSuccessful()) {
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    finish();
                    HelperClass.snackBar("Success", R.color.black,SignInActivity.this);

                } else {
                    HelperClass.snackBar(Objects.requireNonNull(task.getException()).getLocalizedMessage(), R.color.white,SignInActivity.this);
                }
            }
        });
    }
}