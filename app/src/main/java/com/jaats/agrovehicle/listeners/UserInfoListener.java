package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.AuthBean;
import com.jaats.agrovehicle.model.UserBean;

public interface UserInfoListener {

    void onLoadCompleted(UserBean userBean);

    void onLoadFailed(String error);

}
