package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.FreeRideBean;
import com.jaats.agrovehicle.model.RequestBean;

public interface RequestRideListener {

    void onLoadCompleted(RequestBean requestBean);

    void onLoadFailed(String error);
}
