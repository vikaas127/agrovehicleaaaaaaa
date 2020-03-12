package com.jaats.agrovehicle.net.WSAsyncTasks;


import android.os.AsyncTask;

import java.util.HashMap;

import com.jaats.agrovehicle.model.FareBean;
import com.jaats.agrovehicle.net.invokers.TotalFareInvoker;

public class TotalFareTask extends AsyncTask<String, Integer, FareBean> {

    private TotalFareTask.TotalFareTaskListener totalFareTaskListener;

    private HashMap<String, String> urlParams;

    public TotalFareTask(HashMap<String, String> urlParams) {
        super();
        this.urlParams = urlParams;
    }

    @Override
    protected FareBean doInBackground(String... params) {

        System.out.println(">>>>>>>>>doInBackground");
        TotalFareInvoker totalFareInvoker = new TotalFareInvoker(urlParams, null);
        return totalFareInvoker.invokeTotalFareWS();

    }

    @Override
    protected void onPostExecute(FareBean result) {
        if (result != null)
            totalFareTaskListener.dataDownloadedSuccessfully(result);
        else
            totalFareTaskListener.dataDownloadFailed();
    }

    public interface TotalFareTaskListener {

        void dataDownloadedSuccessfully(FareBean fareBean);

        void dataDownloadFailed();

    }

    public TotalFareTaskListener getTotalFareTaskListener() {
        return totalFareTaskListener;
    }

    public void setTotalFareTaskListener(TotalFareTaskListener totalFareTaskListener) {
        this.totalFareTaskListener = totalFareTaskListener;
    }
}
