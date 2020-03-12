package com.jaats.agrovehicle.net.WSAsyncTasks;

import android.os.AsyncTask;

import org.json.JSONObject;

import com.jaats.agrovehicle.model.BaseBean;
import com.jaats.agrovehicle.model.BasicBean;
import com.jaats.agrovehicle.net.invokers.RequestTriggeringInvoker;

/**
 * Created by SIB-QC4 on 5/23/2017.
 */

public class RequestTriggeringTask extends AsyncTask<String, Integer, BasicBean> {

    private RequestTriggeringTaskListener requestTriggeringTaskListener;

    private JSONObject postData;

    public RequestTriggeringTask(JSONObject postData) {
        super();
        this.postData = postData;
    }

    @Override
    protected BasicBean doInBackground(String... params) {
        System.out.println(">>>>>>>>>doInBackground");
        RequestTriggeringInvoker requestTriggeringInvoker = new RequestTriggeringInvoker(null, postData);
        return requestTriggeringInvoker.invokerequestTriggeringWS();
    }

    @Override
    protected void onPostExecute(BasicBean result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        if (result != null)
            requestTriggeringTaskListener.dataDownloadedSuccessfully(result);
        else
            requestTriggeringTaskListener.dataDownloadFailed();
    }

    public static interface RequestTriggeringTaskListener {

        void dataDownloadedSuccessfully(BasicBean basicBean);

        void dataDownloadFailed();
    }

    public RequestTriggeringTaskListener getRequestTriggeringTaskListener() {
        return requestTriggeringTaskListener;
    }

    public void setRequestTriggeringTaskListener(RequestTriggeringTaskListener requestTriggeringTaskListener) {
        this.requestTriggeringTaskListener = requestTriggeringTaskListener;
    }
}
