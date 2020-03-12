package com.jaats.agrovehicle.listeners;

import com.jaats.agrovehicle.model.CarBean;
import com.jaats.agrovehicle.model.TripFeedbackBean;

/**
 * Created by SIB-QC4 on 4/12/2017.
 */

public interface TripFeedbackListener {

    void onLoadFailed(String error);

    void onLoadCompleted(TripFeedbackBean tripFeedbackBean);
}
