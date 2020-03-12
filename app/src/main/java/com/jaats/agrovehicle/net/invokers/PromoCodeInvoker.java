package com.jaats.agrovehicle.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;

import com.jaats.agrovehicle.model.PromoCodeBean;
import com.jaats.agrovehicle.net.ServiceNames;
import com.jaats.agrovehicle.net.WebConnector;
import com.jaats.agrovehicle.net.parsers.PromoCodeParser;
import com.jaats.agrovehicle.net.utils.WSConstants;

public class PromoCodeInvoker extends BaseInvoker {

    public PromoCodeInvoker() {
        super();
    }

    public PromoCodeInvoker(HashMap<String, String> urlParams,
                            JSONObject postData) {
        super(urlParams, postData);
    }

    public PromoCodeBean invokePromoCodeWS() {

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.PROMO_CODE), WSConstants.PROTOCOL_HTTP, urlParams, null);

        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
//    String wsResponseString=webConnector.connectToPOST_service();
        String wsResponseString = webConnector.connectToGET_service(true);
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        PromoCodeBean promoCodeBean = null;
        if (wsResponseString.equals("")) {
            /*registerBean=new RegisterBean();
            registerBean.setWebError(true);*/
            return promoCodeBean = null;

        } else {
            promoCodeBean = new PromoCodeBean();
            PromoCodeParser promoCodeParser = new PromoCodeParser();
            promoCodeBean = promoCodeParser.parsePromoCodeResponse(wsResponseString);
            return promoCodeBean;
        }
    }
}
