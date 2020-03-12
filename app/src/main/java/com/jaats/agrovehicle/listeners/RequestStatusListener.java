package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.RequestBean;

public interface RequestStatusListener {

    void onLoadCompleted(RequestBean requestBean);

    void onLoadFailed(String error);
}
