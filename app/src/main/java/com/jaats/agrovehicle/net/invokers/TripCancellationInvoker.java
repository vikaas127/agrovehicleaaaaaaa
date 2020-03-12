package com.jaats.agrovehicle.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;

import com.jaats.agrovehicle.model.TripCancellationBean;
import com.jaats.agrovehicle.net.ServiceNames;
import com.jaats.agrovehicle.net.WebConnector;
import com.jaats.agrovehicle.net.parsers.TripCancellationParser;
import com.jaats.agrovehicle.net.utils.WSConstants;

public class TripCancellationInvoker extends BaseInvoker {

    public TripCancellationInvoker() {
        super();
    }

    public TripCancellationInvoker(HashMap<String, String> urlParams,
                                   JSONObject postData) {
        super(urlParams, postData);
    }

    public TripCancellationBean invokeTripCancellationWS() {

        System.out.println("POSTDATA>>>>>>>" + postData);

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.TRIP_CANCELLATION), WSConstants.PROTOCOL_HTTP, null, postData);

        //		webConnector= new WebConnector(new StringBuilder(ServiceNames.AUTH_EMAIL), WSConstants.PROTOCOL_HTTP, postData,null);
        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
        String wsResponseString = webConnector.connectToPOST_service();
        //	String wsResponseString=webConnector.connectToGET_service(true);
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        TripCancellationBean tripCancellationBean = null;
        if (wsResponseString.equals("")) {
            /*registerBean=new RegisterBean();
            registerBean.setWebError(true);*/
            return tripCancellationBean = null;
        } else {
            tripCancellationBean = new TripCancellationBean();
            TripCancellationParser dummyParser = new TripCancellationParser();
            tripCancellationBean = dummyParser.parseTripCancellation(wsResponseString);
            return tripCancellationBean;
        }
    }
}

