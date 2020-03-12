package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.LandingPageBean;
import com.jaats.agrovehicle.model.TripListBean;

public interface LandingPageDetailsListener {

    void onLoadCompleted(LandingPageBean landingPageListBean);

    void onLoadFailed(String error);
}
