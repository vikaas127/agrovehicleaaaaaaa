package com.jaats.agrovehicle.listeners;

import com.jaats.agrovehicle.model.OTPBean;
import com.jaats.agrovehicle.model.PromoCodeBean;



public interface OTPSubmitListener {

    void onLoadCompleted(OTPBean otpBean);

    void onLoadFailed(String error);

}
