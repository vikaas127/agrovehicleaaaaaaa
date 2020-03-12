package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.DriverBean;

public interface DriverDetailsListener {

    void onLoadCompleted(DriverBean driverBean);

    void onLoadFailed(String error);
}
