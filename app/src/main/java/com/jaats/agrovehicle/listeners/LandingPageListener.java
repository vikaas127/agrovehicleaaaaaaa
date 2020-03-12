package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.CarBean;
import com.jaats.agrovehicle.model.LandingPageBean;

public interface LandingPageListener {

    void onLoadFailed(String error);

    void onLoadCompleted(LandingPageBean landingPageBean);

}
