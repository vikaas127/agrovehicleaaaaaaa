package com.jaats.agrovehicle.net.WSAsyncTasks;


import android.os.AsyncTask;

import org.json.JSONObject;

import com.jaats.agrovehicle.model.TripCancellationBean;
import com.jaats.agrovehicle.net.invokers.TripCancellationInvoker;

public class TripCancellationTask extends AsyncTask<String, Integer, TripCancellationBean> {

    private TripCancellationTaskListener tripCancellationTaskListener;

    private JSONObject postData;

    public TripCancellationTask(JSONObject postData) {
        super();
        this.postData = postData;
    }

    @Override
    protected TripCancellationBean doInBackground(String... params) {
        System.out.println(">>>>>>>>>doInBackground");
        TripCancellationInvoker tripCancellationInvoker = new TripCancellationInvoker(null, postData);
        return tripCancellationInvoker.invokeTripCancellationWS();
    }

    @Override
    protected void onPostExecute(TripCancellationBean result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        if (result != null)
            tripCancellationTaskListener.dataDownloadedSuccessfully(result);
        else
            tripCancellationTaskListener.dataDownloadFailed();
    }

    public static interface TripCancellationTaskListener {

        void dataDownloadedSuccessfully(TripCancellationBean tripCancellationBean);

        void dataDownloadFailed();
    }

    public TripCancellationTaskListener getTripCancellationTaskListener() {
        return tripCancellationTaskListener;
    }

    public void setTripCancellationTaskListener(TripCancellationTaskListener tripCancellationTaskListener) {
        this.tripCancellationTaskListener = tripCancellationTaskListener;
    }
}
