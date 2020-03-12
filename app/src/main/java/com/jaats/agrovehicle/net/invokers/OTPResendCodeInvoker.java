package com.jaats.agrovehicle.net.invokers;

import org.json.JSONObject;

import java.util.HashMap;

import com.jaats.agrovehicle.model.AuthBean;
import com.jaats.agrovehicle.model.BasicBean;
import com.jaats.agrovehicle.net.ServiceNames;
import com.jaats.agrovehicle.net.WebConnector;
import com.jaats.agrovehicle.net.parsers.OTPResendCodeParser;
import com.jaats.agrovehicle.net.parsers.RegistrationParser;
import com.jaats.agrovehicle.net.utils.WSConstants;

public class OTPResendCodeInvoker extends BaseInvoker {

    public OTPResendCodeInvoker() {
        super();
    }

    public OTPResendCodeInvoker(HashMap<String, String> urlParams,
                               JSONObject postData) {
        super(urlParams, postData);
    }

    public BasicBean invokeRegistrationWS() {

        System.out.println("POSTDATA>>>>>>>" + postData);

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.OTP_RESEND_CODE), WSConstants.PROTOCOL_HTTP, null, postData);

        String wsResponseString = webConnector.connectToPOST_service();

        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        BasicBean basicBean = null;
        if (wsResponseString.equals("")) {

            return basicBean = null;
        } else {
            basicBean = new BasicBean();
            OTPResendCodeParser otpResendCodeParser = new OTPResendCodeParser();
            basicBean = otpResendCodeParser.parseRegistrationResponse(wsResponseString);
            return basicBean;
        }
    }
}
