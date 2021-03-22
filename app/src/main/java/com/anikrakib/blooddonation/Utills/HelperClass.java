package com.anikrakib.blooddonation.Utills;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.anikrakib.blooddonation.Activity.SignInActivity;
import com.anikrakib.blooddonation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kishandonga.csbx.CustomSnackbar;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperClass {
    public static final String SHARED_PREFERENCE_TAG = "shared_preferences";
    public static final Pattern USER_NAME = Pattern.compile("^([a-z])+([\\w.]{2,})+$");
    public static final String ON_BOARD = "onBoard";
    public static final String USERNAME = "userName";
    public static final String USERS_COLLECTION_NAME = "users";
    public static final String LAST_BLOOD_DONATE_HISTORY = "lastBloodDonateHistory";
    public static final String BADGES = "badges";
    public static final String YOUR_BADGES = "yourBadges";
    public static final String USERS_PROFILE_PICTURE_FOLDER_NAME = "profile_pic";
    public static String data;

    public static void snackBar(String text, int color, Context context){
        CustomSnackbar sb = new CustomSnackbar(context);
        sb.message(text);
        sb.padding(15);
        sb.textColorRes(color);
        sb.backgroundColorRes(R.color.navigationItemSelectedBackground);
        sb.cornerRadius(15);
        sb.duration(Snackbar.LENGTH_LONG);
        sb.show();
    }

    public static String getHash(String password) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte[] messageDigest = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) hexString.append(Integer.toHexString(0xFF & b));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isValidUserName(String userName){
        Matcher matcher = USER_NAME.matcher(userName);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



}
