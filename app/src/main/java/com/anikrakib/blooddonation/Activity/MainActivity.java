package com.anikrakib.blooddonation.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.anikrakib.blooddonation.Fragment.HomeFragment;
import com.anikrakib.blooddonation.Fragment.MapFragment;
import com.anikrakib.blooddonation.Fragment.NotificationFragment;
import com.anikrakib.blooddonation.Fragment.ProfileFragment;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.databinding.ActivityMainBinding;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

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
                        fragment = new MapFragment();
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

}