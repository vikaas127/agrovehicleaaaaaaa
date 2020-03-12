package com.jaats.agrovehicle.activity;

import com.jaats.agrovehicle.listeners.AppStatusListener;
import com.jaats.agrovehicle.listeners.BasicListener;
import com.jaats.agrovehicle.listeners.CarInfoListener;
import com.jaats.agrovehicle.listeners.DriverDetailsListener;
import com.jaats.agrovehicle.listeners.DriverRatingListener;
import com.jaats.agrovehicle.listeners.EditProfileListener;
import com.jaats.agrovehicle.listeners.FreeRideListener;
import com.jaats.agrovehicle.listeners.LandingPageListener;
import com.jaats.agrovehicle.listeners.LocationSaveListener;
import com.jaats.agrovehicle.listeners.LoginListener;
import com.jaats.agrovehicle.listeners.OTPResendCodeListener;
import com.jaats.agrovehicle.listeners.PolyPointsListener;
import com.jaats.agrovehicle.listeners.PromoCodeListener;
import com.jaats.agrovehicle.listeners.RegistrationListener;
import com.jaats.agrovehicle.listeners.RequestRideListener;
import com.jaats.agrovehicle.listeners.RequestStatusListener;
import com.jaats.agrovehicle.listeners.SavedLocationListener;
import com.jaats.agrovehicle.listeners.SuccessListener;
import com.jaats.agrovehicle.listeners.TotalFareListener;
import com.jaats.agrovehicle.listeners.TripCancellationListener;
import com.jaats.agrovehicle.listeners.TripDetailsListener;
import com.jaats.agrovehicle.listeners.TripFeedbackListener;
import com.jaats.agrovehicle.listeners.TripListListener;
import com.jaats.agrovehicle.listeners.UserInfoListener;
import com.jaats.agrovehicle.model.AuthBean;
import com.jaats.agrovehicle.model.BasicBean;
import com.jaats.agrovehicle.model.CarBean;
import com.jaats.agrovehicle.model.DriverBean;
import com.jaats.agrovehicle.model.DriverRatingBean;
import com.jaats.agrovehicle.model.FareBean;
import com.jaats.agrovehicle.model.FreeRideBean;
import com.jaats.agrovehicle.model.LandingPageBean;
import com.jaats.agrovehicle.model.LocationBean;
import com.jaats.agrovehicle.model.PolyPointsBean;
import com.jaats.agrovehicle.model.PromoCodeBean;
import com.jaats.agrovehicle.model.RequestBean;
import com.jaats.agrovehicle.model.SuccessBean;
import com.jaats.agrovehicle.model.TripCancellationBean;
import com.jaats.agrovehicle.model.TripDetailsBean;
import com.jaats.agrovehicle.model.TripFeedbackBean;
import com.jaats.agrovehicle.model.TripListBean;
import com.jaats.agrovehicle.model.UserBean;
import com.jaats.agrovehicle.net.WSAsyncTasks.AppStatusTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.CarInfoTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.DriverDetailsTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.DriverRatingTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.EditProfileTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.FreeRideTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.LandingPageDetailsTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.LocationSaveTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.LoginTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.MobileAvailabilityCheckTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.NewPasswordTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.OTPResendCodeTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.PolyPointsTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.PromoCodeTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.RegistrationTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.RequestCancelTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.RequestRideTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.RequestStatusTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.RequestTriggeringTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.SavedLocationTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.SuccessDetailsTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.TotalFareTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.TripCancellationTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.TripDetailsTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.TripFeedbackTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.TripListTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.UpdateFCMTokenTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.UserInfoTask;
import com.jaats.agrovehicle.util.AppConstants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;




    public class DataManager {




        public static void fetchLandingPageDetails(HashMap<String, String> urlParams, final LandingPageListener landingPageListener) {

            LandingPageDetailsTask landingPageDetailsTask = new LandingPageDetailsTask(urlParams);
            landingPageDetailsTask.setLandingPageDetailsTaskListener(new LandingPageDetailsTask.LandingPageDetailsTaskListener() {

                @Override
                public void dataDownloadedSuccessfully(LandingPageBean landingPageListBean) {
                    if (landingPageListBean == null)
                        landingPageListener.onLoadFailed(null);
                    else {
                        if (landingPageListBean.getStatus().equalsIgnoreCase("Success")) {
                            landingPageListener.onLoadCompleted(landingPageListBean);
                        } else if (landingPageListBean.getStatus().equalsIgnoreCase("Error")) {
                            landingPageListener.onLoadFailed(landingPageListBean.getErrorMsg());
                        } else {
                          //  landingPageListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                        }
                    }
                }

                @Override
                public void dataDownloadFailed() {
                    //landingPageListener.onLoadFailed(AppConstants.WEB_ERROR_MSG);
                }
            });
            landingPageDetailsTask.execute();
        }


        public static void fetchPolyPoints(HashMap<String, String> urlParams, final PolyPointsListener polyPointsListener) {

            PolyPointsTask polyPointsTask = new PolyPointsTask(urlParams);
            polyPointsTask.setPolyPointsTaskListener(new PolyPointsTask.PolyPointsTaskListener() {
                @Override
                public void dataDownloadedSuccessfully(PolyPointsBean polyPointsBean) {
                    if (polyPointsBean == null)
                        polyPointsListener.onLoadFailed(null);
                    else {
                        if (polyPointsBean.getStatus().equalsIgnoreCase("Success")) {
                            polyPointsListener.onLoadCompleted(polyPointsBean);
                        } else if (polyPointsBean.getStatus().equalsIgnoreCase("Error")) {
                            polyPointsListener.onLoadFailed(polyPointsBean.getErrorMsg());
                        } else {
                            polyPointsListener.onLoadFailed(null);
                        }
                    }
                }

                @Override
                public void dataDownloadFailed() {
                    polyPointsListener.onLoadFailed(null);
                }
            });
            polyPointsTask.execute();
        }


}

