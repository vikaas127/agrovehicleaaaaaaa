package com.jaats.agrovehicle.net.WSAsyncTasks;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.HashMap;

import com.jaats.agrovehicle.model.AuthBean;
import com.jaats.agrovehicle.net.invokers.RegistrationInvoker;

public class RegistrationTask extends AsyncTask<String, Integer, AuthBean> {

    private RegistrationTaskListener registrationTaskListener;

    private JSONObject postData;

    public RegistrationTask(JSONObject postData) {
        super();
        this.postData = postData;
    }


    @Override
    protected AuthBean doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(AuthBean result) {
        super.onPostExecute(result);
        if (result != null)
            registrationTaskListener.dataDownloadedSuccessfully(result);
        else
            registrationTaskListener.dataDownloadFailed();
    }

    public static interface RegistrationTaskListener {

        void dataDownloadedSuccessfully(AuthBean authBean);

        void dataDownloadFailed();
    }

    public RegistrationTaskListener getRegistrationTaskListener() {
        return registrationTaskListener;
    }

    public void setRegistrationTaskListener(RegistrationTaskListener registrationTaskListener) {
        this.registrationTaskListener = registrationTaskListener;
    }
}
