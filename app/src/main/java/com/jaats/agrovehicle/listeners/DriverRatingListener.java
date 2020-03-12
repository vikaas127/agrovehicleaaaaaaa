package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.DriverRatingBean;

public interface DriverRatingListener {

    void onLoadCompleted(DriverRatingBean driverRatingBean);

    void onLoadFailed(String error);
}


