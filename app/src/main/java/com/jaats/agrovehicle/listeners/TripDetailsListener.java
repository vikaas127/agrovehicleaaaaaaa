package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.TripDetailsBean;

public interface TripDetailsListener {

    void onLoadCompleted(TripDetailsBean tripDetailsBean);

    void onLoadFailed(String error);
}
