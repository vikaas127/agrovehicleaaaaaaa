package com.jaats.agrovehicle.listeners;

import com.jaats.agrovehicle.model.AuthBean;
import com.jaats.agrovehicle.model.BasicBean;

public interface OTPResendCodeListener {

    void onLoadCompleted(BasicBean basicBean);

    void onLoadFailed(String error);
}
