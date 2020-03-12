package com.jaats.agrovehicle.listeners;

import com.jaats.agrovehicle.model.RequestBean;
import com.jaats.agrovehicle.model.TripCancellationBean;

public interface TripCancellationListener {

    void onLoadCompleted(TripCancellationBean tripCancellationBean);

    void onLoadFailed(String error);
}
