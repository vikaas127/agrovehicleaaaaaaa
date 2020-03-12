package com.jaats.agrovehicle.services;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.HashMap;

import com.jaats.agrovehicle.activity.TripFeedbackActivity;
import com.jaats.agrovehicle.listeners.SuccessListener;
import com.jaats.agrovehicle.model.SuccessBean;


public class DriverRatingService extends Service {

    private IBinder mBinder;


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;

    }

    @Override
    public void onCreate() {

        fetchTripCompletionDetails();

    }

    public void fetchTripCompletionDetails() {

        HashMap<String, String> urlParams = new HashMap<>();


    }

    /*private void populateDriverInfo(SuccessBean successBean) {

        onPlotLatLng(Double.parseDouble(successBean.getSourceLatitude()), Double.parseDouble(successBean.getSourceLongitude()), Double.parseDouble(successBean.getDestinationLatitude()), Double.parseDouble(successBean.getDestinationLongitude()));

        txtRatingPageDate.setText(App.getUserDateFromUnix(String.valueOf(successBean.getTime())));
        txtRatingPageTime.setText(App.getUserTimeFromUnix(String.valueOf(successBean.getTime())));
        txtRatingPageFare.setText(successBean.getFare());
        txtRatingPageDriverName.setText(successBean.getDriverName());

        Glide.with(getApplicationContext())
                .load(successBean.getDriverPhoto())
                .fallback(R.drawable.ic_dummy_photo)
                .error(R.drawable.ic_dummy_photo)
                .transform(new CenterCrop(getApplicationContext()), new CircleTransform(getApplicationContext()))
                .into(ivRatingPageDriverPhoto);
    }

    private void onPlotLatLng(double sourceLatitude, double sourceLongitude, double destinationLatitude, double destinationLongitude) {

        LatLng newLatLng = null;
        try {
            newLatLng = new LatLng(sourceLatitude, sourceLongitude);

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLatLng, 14));

            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.addMarker(new MarkerOptions().position(newLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_source_trips)));

            newLatLng = new LatLng(destinationLatitude, destinationLongitude);
            mMap.addMarker(new MarkerOptions().position(newLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_destination_trips)));

        } catch (NumberFormatException e) {
            e.printStackTrace();

        }
    }*/
}