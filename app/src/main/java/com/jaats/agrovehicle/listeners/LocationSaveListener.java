package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.LocationBean;

public interface LocationSaveListener {

    void onLoadCompleted(LocationBean locationBean);

    void onLoadFailed(String error);
}


