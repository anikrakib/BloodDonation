package com.anikrakib.blooddonation.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anikrakib.blooddonation.Model.BloodRequestModel;
import com.anikrakib.blooddonation.Model.UserDataModel;
import com.anikrakib.blooddonation.Model.YourBadgeModel;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.databinding.ActivityBloodRequestBinding;
import com.anikrakib.blooddonation.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class BloodRequest extends AppCompatActivity {
    ActivityBloodRequestBinding activityBloodRequestBinding;
    public static BloodRequestModel bloodRequestModel = new BloodRequestModel();
    FirebaseFirestore database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBloodRequestBinding = ActivityBloodRequestBinding.inflate(getLayoutInflater());
        setContentView(activityBloodRequestBinding.getRoot());

        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        activityBloodRequestBinding.publish.setOnClickListener(v -> {

            final String doc_id = database.collection("requestForBlood").document().getId();
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            String day = new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime());
            String time = new SimpleDateFormat("hh:mm:ss", Locale.ENGLISH).format(date.getTime());
            String dateString = HelperClass.sdf.format(date.getTime());

            bloodRequestModel.setBloodGroup(activityBloodRequestBinding.bloodGroup.getText().toString());
            bloodRequestModel.setRelation(activityBloodRequestBinding.relationShip.getText().toString());
            bloodRequestModel.setLocation(activityBloodRequestBinding.location.getText().toString());
            bloodRequestModel.setHospitalName("Popular Medical Hospital");
            bloodRequestModel.setPostDateAndTime(day + " " + time);
            bloodRequestModel.setRequiredDate(dateString);
            bloodRequestModel.setUserId(Objects.requireNonNull(auth.getCurrentUser()).getUid());

            if (activityBloodRequestBinding.urgentOrNot.isChecked()) {
                bloodRequestModel.setUrgentOrScheduled(true);
            }

            database.collection(HelperClass.REQUEST_FOR_BLOOD)
                    .document(doc_id)
                    .set(bloodRequestModel);

            database.collection(HelperClass.USERS_COLLECTION_NAME)
                    .document(MainActivity.userName)
                    .collection(HelperClass.REQUEST_FOR_BLOOD)
                    .document(doc_id)
                    .set(bloodRequestModel);

            finish();

        });

    }
}