package com.jaats.agrovehicle.net.WSAsyncTasks;

import android.os.AsyncTask;

import java.util.HashMap;

import com.jaats.agrovehicle.listeners.RecentSearchListener;
import com.jaats.agrovehicle.model.RecentSearchBean;
import com.jaats.agrovehicle.net.invokers.RecentSearchInvoker;

public class RecentSearchTask extends AsyncTask<String, Integer, RecentSearchBean> {

    private RecentSearchListener recentSearchListener;

    private HashMap<String, String> urlParams;

    public RecentSearchTask(HashMap<String, String> urlParams) {
        super();
        this.urlParams = urlParams;
    }

    @Override
    protected RecentSearchBean doInBackground(String... params) {

        System.out.println(">>>>>>>>>doInBackground");
        RecentSearchInvoker recentSearchInvoker = new RecentSearchInvoker(urlParams, null);
        return recentSearchInvoker.invokeRecentSearchWS();
    }

    @Override
    protected void onPostExecute(RecentSearchBean result) {
        super.onPostExecute(result);
        if (result != null)
            recentSearchListener.dataDownloadedSuccessfully(result);
        else
            recentSearchListener.dataDownloadFailed();
    }

    public static interface RecentSearchListener {

        void dataDownloadedSuccessfully(RecentSearchBean recentSearchBean);

        void dataDownloadFailed();
    }

    public RecentSearchListener getRecentSearchListener() {
        return recentSearchListener;
    }

    public void setRecentSearchListener(RecentSearchListener recentSearchListener) {
        this.recentSearchListener = recentSearchListener;
    }
}
