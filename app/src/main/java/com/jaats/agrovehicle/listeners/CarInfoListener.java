package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.CarBean;
import com.jaats.agrovehicle.model.UserBean;

public interface CarInfoListener {

    void onLoadFailed(String error);

    void onLoadCompleted(CarBean carBean);

}
