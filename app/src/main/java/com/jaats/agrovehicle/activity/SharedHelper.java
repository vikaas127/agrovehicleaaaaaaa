package com.jaats.agrovehicle.activity;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.Key;

class SharedHelper {
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static void putKey(Context context, String Key, String Value) {
        sharedPreferences = context.getSharedPreferences("Cache", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(Key, Value);
        editor.commit();

    }



    public static void clearSharedPreferences(Context context)
    {
        sharedPreferences = context.getSharedPreferences("Cache", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }


    public static String getKey(String latitude) {

        String Value = sharedPreferences.getString(String.valueOf(latitude), "");
        return Value;
    }


}
