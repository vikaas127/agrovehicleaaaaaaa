package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.AuthBean;

public interface LoginListener {

    void onLoadCompleted(AuthBean authBean);

    void onLoadFailed(String error);
}
