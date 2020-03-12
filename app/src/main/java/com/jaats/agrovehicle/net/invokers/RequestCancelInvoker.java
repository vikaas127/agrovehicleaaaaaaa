package com.jaats.agrovehicle.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;

import com.jaats.agrovehicle.model.BaseBean;
import com.jaats.agrovehicle.model.BasicBean;
import com.jaats.agrovehicle.net.ServiceNames;
import com.jaats.agrovehicle.net.WebConnector;
import com.jaats.agrovehicle.net.parsers.BasicParser;
import com.jaats.agrovehicle.net.utils.WSConstants;

public class RequestCancelInvoker extends BaseInvoker {

    public RequestCancelInvoker() {
        super();
    }

    public RequestCancelInvoker(HashMap<String, String> urlParams,
                                JSONObject postData) {
        super(urlParams, postData);
    }

    public BaseBean invokeRequestCancelWS() {

        System.out.println("POSTDATA>>>>>>>" + postData);

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.REQUEST_CANCEL), WSConstants.PROTOCOL_HTTP, null, postData);

        //		webConnector= new WebConnector(new StringBuilder(ServiceNames.AUTH_EMAIL), WSConstants.PROTOCOL_HTTP, postData,null);
        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
        String wsResponseString = webConnector.connectToPOST_service();
        //	String wsResponseString=webConnector.connectToGET_service(true);
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        BasicBean basicBean = null;
        if (wsResponseString.equals("")) {
            /*registerBean=new RegisterBean();
            registerBean.setWebError(true);*/
            return basicBean = null;
        } else {
            basicBean = new BasicBean();
            BasicParser basicParser = new BasicParser();
            basicBean = basicParser.parseBasicResponse(wsResponseString);
            return basicBean;
        }
    }
}

