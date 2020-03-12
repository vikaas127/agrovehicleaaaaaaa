package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.CarBean;
import com.jaats.agrovehicle.model.DriverBean;

public interface AppStatusListener {

    void onLoadFailed(String error);

    void onLoadCompleted(DriverBean driverBean);

}
