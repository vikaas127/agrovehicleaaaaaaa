package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.TripBean;
import com.jaats.agrovehicle.model.TripListBean;

public abstract interface TripListListener {

    void onLoadCompleted(TripListBean tripListBean);

    void onLoadFailed(String error);

}
