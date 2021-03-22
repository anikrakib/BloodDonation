package com.anikrakib.blooddonation.Fragment.Profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anikrakib.blooddonation.Activity.MainActivity;
import com.anikrakib.blooddonation.Activity.SignInActivity;
import com.anikrakib.blooddonation.Activity.SignUpActivity;
import com.anikrakib.blooddonation.Adapter.Profile.BadgesAdapter;
import com.anikrakib.blooddonation.Model.BadgeModel;
import com.anikrakib.blooddonation.Model.DonateBloodHistoryModel;
import com.anikrakib.blooddonation.Model.YourBadgeModel;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.databinding.FragmentInfoBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class InfoFragment extends Fragment {
    FragmentInfoBinding fragmentInfoBinding;
    FirebaseFirestore database;
    public static String lastDonateBloodDate = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentInfoBinding = FragmentInfoBinding.inflate(getLayoutInflater());
        database = FirebaseFirestore.getInstance();
        getDonateBloodInfo();


        final List<BadgeModel> badgeModels = new ArrayList<>();

        final BadgesAdapter adapter = new BadgesAdapter(getContext(), badgeModels);

        database.collection(HelperClass.USERS_COLLECTION_NAME)
                .document(MainActivity.userName)
                .collection(HelperClass.YOUR_BADGES)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        badgeModels.clear();
                        assert value != null;
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            BadgeModel model = snapshot.toObject(BadgeModel.class);
                            badgeModels.add(model);

                            //badgeModels.add(model);
                            Log.d("data",badgeModels.size()+"");
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

        fragmentInfoBinding.yourBadgeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        fragmentInfoBinding.yourBadgeRecyclerView.setNestedScrollingEnabled(false);
        fragmentInfoBinding.yourBadgeRecyclerView.setAdapter(adapter);


        return fragmentInfoBinding.getRoot();
    }

    public void getDonateBloodInfo(){

        database.collection(HelperClass.USERS_COLLECTION_NAME)
                .document(MainActivity.userName)
                .collection(HelperClass.LAST_BLOOD_DONATE_HISTORY)
                .document("fAw9CxJbLwQAw5jJ7PbY")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null) {
                                DonateBloodHistoryModel donateBloodHistoryModel = new DonateBloodHistoryModel();
                                donateBloodHistoryModel.setDate(document.getString("date"));
                                lastDonateBloodDate = document.getString("date");

                                Calendar calendar = new GregorianCalendar();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
                                String currentDate = sdf.format(calendar.getTime());

                                long dayPassed = HelperClass.getBloodDayRemaining(currentDate,lastDonateBloodDate);

                                fragmentInfoBinding.bloodDonateRemainingProgressbar.setProgress((Math.toIntExact(dayPassed)+10));
                                if(90-Math.toIntExact(dayPassed) <= 0){
                                    fragmentInfoBinding.remainDate.setText(getResources().getString(R.string.freeToDonateBlood));
                                    fragmentInfoBinding.text.setVisibility(View.GONE);
                                }else {
                                    fragmentInfoBinding.remainDate.setText(String.valueOf(90-Math.toIntExact(dayPassed)));
                                }

                            } else {
                                Log.d("userInfo", "No such document");
                            }
                        }
                    }
                });

    }
}