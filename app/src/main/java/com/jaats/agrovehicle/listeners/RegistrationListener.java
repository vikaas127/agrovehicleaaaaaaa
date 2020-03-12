package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.AuthBean;

public interface RegistrationListener {

    void onLoadCompleted(AuthBean authBean);

    void onLoadFailed(String error);

}
