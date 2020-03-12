package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.SuccessBean;

public interface SuccessListener {

    void onLoadCompleted(SuccessBean successBean);

    void onLoadFailed(String error);
}
