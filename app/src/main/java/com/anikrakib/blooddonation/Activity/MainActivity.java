package com.anikrakib.blooddonation.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.anikrakib.blooddonation.Fragment.Home.HomeFragment;
import com.anikrakib.blooddonation.Fragment.Map.MapFragment;
import com.anikrakib.blooddonation.Fragment.Notification.NotificationFragment;
import com.anikrakib.blooddonation.Fragment.Profile.ProfileFragment;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.SharedPreferencesHelper;
import com.anikrakib.blooddonation.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    FragmentManager fragmentManager;
    FirebaseAuth auth;
    FirebaseFirestore database;
    public static String userName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();


        //Log.d("username",HelperClass.getUserFieldData(HelperClass.USERS_COLLECTION_NAME,auth.getCurrentUser().getUid(),"userName",database)+"");
        getUserName();
        Log.d("username",userName);
        SharedPreferencesHelper.setUserName(getApplicationContext(),userName);
        Log.d("data", SharedPreferencesHelper.getUserName(getApplicationContext())+"");

        if(savedInstanceState == null){
            activityMainBinding.bottomNav.setItemSelected(R.id.home,true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.container,homeFragment)
                    .commit();
        }

        activityMainBinding.bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i){
                    case R.id.map:
                        //fragment = new MapFragment();
                        startActivity(new Intent(getApplicationContext(),MapActivity.class));
                        break;
                    case R.id.notification:
                        fragment = new NotificationFragment();
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                    default:
                        fragment = new HomeFragment();
                        break;
                }

                if(fragment!=null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container,fragment)
                            .commit();
                }
            }
        });

    }

    public void getUserName(){
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
                               // Log.d("username",userName);
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