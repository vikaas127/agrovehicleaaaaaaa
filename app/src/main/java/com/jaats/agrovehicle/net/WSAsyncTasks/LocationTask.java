package com.jaats.agrovehicle.net.WSAsyncTasks;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.jaats.agrovehicle.app.App;
import com.jaats.agrovehicle.config.Config;
import com.jaats.agrovehicle.model.PlaceBean;

/**
 * Created by Jemsheer K D on 11 July, 2017.
 * Package com.jaats.agrovehicle.net.WSAsyncTasks
 * Project LaTaxi
 */

public abstract class LocationTask extends AsyncTask<String, Integer, PlaceBean> {


    private static final String TAG = "LocationTask";
    private double latitude;
    private double longitude;
    private LocationTaskListener locationTaskListener;
    private PlaceBean placeBean;


    public LocationTask(double latitude, double longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }





    @Override
    protected void onPostExecute(PlaceBean result) {

        super.onPostExecute(result);
        if (result != null)
            locationTaskListener.dataDownloadedSuccessfully(result);
        else
            locationTaskListener.dataDownloadFailed();
    }

   /* @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);
        if (result != null)
            locationTaskListener.dataDownloadedSuccessfully(result);
        else
            locationTaskListener.dataDownloadFailed();
    }*/

    public interface LocationTaskListener {
        //        void dataDownloadedSuccessfully(String address);
        void dataDownloadedSuccessfully(PlaceBean placeBean);

        void dataDownloadFailed();
    }

    public LocationTask.LocationTaskListener getLocationTaskListener() {
        return locationTaskListener;
    }


    public void setLocationTaskListener(LocationTask.LocationTaskListener locationTaskListener) {
        this.locationTaskListener = locationTaskListener;
    }
}
