package com.anikrakib.blooddonation.Utills;

import android.content.Context;
import android.graphics.Typeface;

import com.anikrakib.blooddonation.R;
import com.google.android.material.snackbar.Snackbar;
import com.kishandonga.csbx.CustomSnackbar;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HelperClass {
    public static final String SHARED_PREFERENCE_TAG = "shared_preferences";

    public static final String ON_BOARD = "onBoard";

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
}
