package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.PolyPointsBean;

public interface PolyPointsListener {

    void onLoadFailed(String error);

    void onLoadCompleted(PolyPointsBean polyPointsBean);
}
