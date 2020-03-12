package com.jaats.agrovehicle.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;

import com.jaats.agrovehicle.model.TripDetailsBean;
import com.jaats.agrovehicle.model.UserBean;
import com.jaats.agrovehicle.net.ServiceNames;
import com.jaats.agrovehicle.net.WebConnector;
import com.jaats.agrovehicle.net.parsers.TripDetailsParser;
import com.jaats.agrovehicle.net.parsers.UserInfoParser;
import com.jaats.agrovehicle.net.utils.WSConstants;

public class TripDetailsInvoker extends BaseInvoker {

    public TripDetailsInvoker() {
        super();
    }

    public TripDetailsInvoker(HashMap<String, String> urlParams,
                           JSONObject postData) {
        super(urlParams, postData);

    }

    public TripDetailsBean invokeTripDetailsWS() {

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.TRIP_DETAILS), WSConstants.PROTOCOL_HTTP, urlParams, null);

        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
//    String wsResponseString=webConnector.connectToPOST_service();
        String wsResponseString = webConnector.connectToGET_service(true);
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        TripDetailsBean tripDetailsBean = null;
        if (wsResponseString.equals("")) {
            /*registerBean=new RegisterBean();
			registerBean.setWebError(true);*/
            return tripDetailsBean = null;
        } else {
            tripDetailsBean = new TripDetailsBean();
            TripDetailsParser tripDetailsParser = new TripDetailsParser();
            tripDetailsBean = tripDetailsParser.parseTripDetailsResponse(wsResponseString);
            return tripDetailsBean;
        }
    }
}
