package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.AuthBean;
import com.jaats.agrovehicle.model.LocationBean;

public interface SavedLocationListener {

    void onLoadCompleted(LocationBean locationBean);

    void onLoadFailed(String error);

}
