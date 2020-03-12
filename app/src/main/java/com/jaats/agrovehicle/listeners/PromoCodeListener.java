package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.PromoCodeBean;
import com.jaats.agrovehicle.model.UserBean;

public interface PromoCodeListener {

    void onLoadCompleted(PromoCodeBean promoCodeBean);

    void onLoadFailed(String error);

}
