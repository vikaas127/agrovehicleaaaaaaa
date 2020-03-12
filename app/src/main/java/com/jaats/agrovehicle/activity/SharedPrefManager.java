package com.jaats.agrovehicle.activity;

import android.content.Context;
import android.content.SharedPreferences;

import com.jaats.agrovehicle.config.Config;

class SharedPrefManager {

    private static SharedPrefManager minst;
    private static Context mct;
    private static final String LATITUDE="LATITUDE";
    private static final String LONGITUDE="LONGITUDE";
    private static final String SHARD_PERFNAME="myshardperf624";
    private static final String KEY_ID="id";
    private static final String KEY_PICTURE="picture";
    private static final String KEY_USERNAME="name";
    private static final String KEY_BOOKING_ID="booking_id";
    private static final String KEY_USER_ID="user_id";
    private static final String KEY_EMAIL="email";
    private static final String KEY_FIRST_NAME="first_name";
    private static final String KEY_LAST_NAME="last_name";
    private static final String KEY_MOBILE="mobile";

    private static final String ADDHOME="setAddHome";




    private SharedPrefManager(Context context){
        mct=context;
    }
    public static synchronized SharedPrefManager getInstans(Context context){
        if (minst==null){
            minst=new SharedPrefManager(context);
        }
        return minst;
    }



    public boolean userLogin(String id, String first_name, String last_name){

        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_ID,id);
        editor.putString(KEY_FIRST_NAME,first_name);
        editor.putString(KEY_LAST_NAME,last_name);



        editor.apply();
        return true;
    }


    public boolean fatchdata( String mobile ){

        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(KEY_MOBILE,mobile);



        editor.apply();
        return true;
    }

    public static boolean getInstance(String setAddHome){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(ADDHOME,setAddHome);


        return true;
    }




    public boolean FatchTrips( String booking_id, String user_id){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(KEY_BOOKING_ID,booking_id);
        editor.putString(KEY_USER_ID,user_id);



        editor.apply();
        return true;
    }





    public boolean isLogin(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME,Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_ID,null)!=null){
            return true;
        }
        return false;
    }
    public boolean logout(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;

    }




    public String getUserId(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ID,null);

    }

    public String getUsername(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRST_NAME,null);

    }
    public String getLastName(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_LAST_NAME,null);

    }


    public String getmobile(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_MOBILE,null);

    }


    public String getLatitude() {
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(LATITUDE,null);

    }
    public String getLongitude(){
        SharedPreferences sharedPreferences=mct.getSharedPreferences(SHARD_PERFNAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(LONGITUDE,null);
    }



}
