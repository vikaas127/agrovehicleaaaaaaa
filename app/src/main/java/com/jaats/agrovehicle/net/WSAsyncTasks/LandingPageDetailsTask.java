package com.jaats.agrovehicle.net.WSAsyncTasks;


import android.os.AsyncTask;

import java.util.HashMap;

import com.jaats.agrovehicle.model.CarBean;
import com.jaats.agrovehicle.model.LandingPageBean;
import com.jaats.agrovehicle.net.invokers.CarInfoInvoker;
import com.jaats.agrovehicle.net.invokers.LandingPageDetailsInvoker;

public class LandingPageDetailsTask extends AsyncTask<String, Integer, LandingPageBean> {

    private LandingPageDetailsTaskListener landingPageDetailsTaskListener;

    private HashMap<String, String> urlParams;

    public LandingPageDetailsTask(HashMap<String, String> urlParams) {
        super();
        this.urlParams = urlParams;
    }

    @Override
    protected LandingPageBean doInBackground(String... params) {

        System.out.println(">>>>>>>>>doInBackground");
        LandingPageDetailsInvoker landingPageDetailsInvoker = new LandingPageDetailsInvoker(urlParams, null);
        return landingPageDetailsInvoker.invokeLandingPageDetailsWS();
    }

    @Override
    protected void onPostExecute(LandingPageBean result) {
        if (result != null)
            landingPageDetailsTaskListener.dataDownloadedSuccessfully(result);
        else
            landingPageDetailsTaskListener.dataDownloadFailed();
    }

    public interface LandingPageDetailsTaskListener {

        void dataDownloadedSuccessfully(LandingPageBean landingPageBean);

        void dataDownloadFailed();
    }

    public LandingPageDetailsTaskListener getLandingPageDetailsTaskListener() {
        return landingPageDetailsTaskListener;
    }

    public void setLandingPageDetailsTaskListener(LandingPageDetailsTaskListener landingPageDetailsTaskListener) {
        this.landingPageDetailsTaskListener = landingPageDetailsTaskListener;
    }
}
