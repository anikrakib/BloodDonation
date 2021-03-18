package com.anikrakib.blooddonation.Utills;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferences {

    public SharedPreferences(Context context) {

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
}
