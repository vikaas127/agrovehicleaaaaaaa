package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.CarBean;
import com.jaats.agrovehicle.model.PlaceBean;

public interface PolyLineListener {

    void onLoadFailed(String error);

    void onLoadCompleted(PlaceBean placeBean);
}
