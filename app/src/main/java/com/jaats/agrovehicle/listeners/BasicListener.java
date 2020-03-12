package com.jaats.agrovehicle.listeners;

import com.jaats.agrovehicle.model.BaseBean;
import com.jaats.agrovehicle.model.BasicBean;

public interface BasicListener {

    void onLoadCompleted(BasicBean basicBean);

    void onLoadFailed(String error);

}
