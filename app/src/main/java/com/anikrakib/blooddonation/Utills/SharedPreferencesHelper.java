package com.anikrakib.blooddonation.Utills;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesHelper {

    public SharedPreferencesHelper(Context context) {

    }

    public static void setOnBoardCompleteOrNot(Context context, boolean completeOrNot) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(HelperClass.SHARED_PREFERENCE_TAG, MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(HelperClass.ON_BOARD, completeOrNot).apply();
    }

    public static boolean getOnBoardCompleteOrNot(Context context) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(HelperClass.SHARED_PREFERENCE_TAG, MODE_PRIVATE);
        return sharedPreferences.getBoolean(HelperClass.ON_BOARD, false);
    }

    public static void setUserName(Context context, String userName) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(HelperClass.SHARED_PREFERENCE_TAG, MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", userName).apply();
    }

    public static String getUserName(Context context) {
        android.content.SharedPreferences userPref = context.getSharedPreferences(HelperClass.SHARED_PREFERENCE_TAG, Context.MODE_PRIVATE);

        return userPref.getString("userName",null);
    }
}

