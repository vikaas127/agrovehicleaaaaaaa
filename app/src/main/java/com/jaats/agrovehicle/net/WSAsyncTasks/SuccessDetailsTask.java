package com.jaats.agrovehicle.net.WSAsyncTasks;


import android.os.AsyncTask;

import java.util.HashMap;

import com.jaats.agrovehicle.model.SuccessBean;
import com.jaats.agrovehicle.model.UserBean;
import com.jaats.agrovehicle.net.invokers.SuccessDetailsInvoker;
import com.jaats.agrovehicle.net.invokers.UserInfoInvoker;

public class SuccessDetailsTask extends AsyncTask<String, Integer, SuccessBean>{

    private SuccessDetailsTask.SuccessDetailsTaskListener successDetailsTaskListener;

    private HashMap<String, String> urlParams;

    public SuccessDetailsTask(HashMap<String, String> urlParams) {
        super();
        this.urlParams = urlParams;
    }

    @Override
    protected SuccessBean doInBackground(String... params) {
        System.out.println(">>>>>>>>>doInBackground");
        SuccessDetailsInvoker successDetailsInvoker = new SuccessDetailsInvoker(urlParams, null);
        return successDetailsInvoker.invokeSuccessDetailsWS();
    }

    @Override
    protected void onPostExecute(SuccessBean result) {
        if (result != null)
            successDetailsTaskListener.dataDownloadedSuccessfully(result);
        else
            successDetailsTaskListener.dataDownloadFailed();
    }

    public interface SuccessDetailsTaskListener {

        void dataDownloadedSuccessfully(SuccessBean successBean);

        void dataDownloadFailed();

    }

    public SuccessDetailsTaskListener getSuccessDetailsTaskListener() {
        return successDetailsTaskListener;
    }

    public void setSuccessDetailsTaskListener(SuccessDetailsTaskListener successDetailsTaskListener) {
        this.successDetailsTaskListener = successDetailsTaskListener;
    }
}
