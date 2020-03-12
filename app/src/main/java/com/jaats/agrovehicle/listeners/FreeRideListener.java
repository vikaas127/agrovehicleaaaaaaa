package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.FreeRideBean;

public interface FreeRideListener {

    void onLoadCompleted(FreeRideBean freeRideBean);

    void onLoadFailed(String error);

}
