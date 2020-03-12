package com.jaats.agrovehicle.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;

import com.jaats.agrovehicle.model.PolyPointsBean;
import com.jaats.agrovehicle.net.ServiceNames;
import com.jaats.agrovehicle.net.WebConnector;
import com.jaats.agrovehicle.net.parsers.PolyPointsParser;
import com.jaats.agrovehicle.net.utils.WSConstants;

public class PolyPointsInvoker extends BaseInvoker {


    public PolyPointsInvoker() {
        super();
    }

    public PolyPointsInvoker(HashMap<String, String> urlParams,
                             JSONObject postData) {
        super(urlParams, postData);
    }

    public PolyPointsBean invokePolyPointsWS() {

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.POLY_POINTS), WSConstants.PROTOCOL_HTTP, urlParams, null);

        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
        //    String wsResponseString=webConnector.connectToPOST_service();
        String wsResponseString = webConnector.connectToGET_service(true);
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        PolyPointsBean polyPointsBean = null;
        if (wsResponseString.equals("")) {
                /*registerBean=new RegisterBean();
                registerBean.setWebError(true);*/
            return polyPointsBean = null;
        } else {
            polyPointsBean = new PolyPointsBean();
            PolyPointsParser polyPointsParser = new PolyPointsParser();
            polyPointsBean = polyPointsParser.parsePolyPointsResponse(wsResponseString);
            return polyPointsBean;
        }
    }
}

