package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.FareBean;

public interface TotalFareListener {

    void onLoadCompleted(FareBean fareBean);

    void onLoadFailed(String error);
}
