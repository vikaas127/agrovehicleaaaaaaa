package com.jaats.agrovehicle.net.invokers;

import org.json.JSONObject;

import java.util.HashMap;

import com.jaats.agrovehicle.model.AuthBean;
import com.jaats.agrovehicle.net.ServiceNames;
import com.jaats.agrovehicle.net.WebConnector;
import com.jaats.agrovehicle.net.parsers.RegistrationParser;
import com.jaats.agrovehicle.net.utils.WSConstants;

public class RegistrationInvoker extends BaseInvoker {

    public RegistrationInvoker() {
        super();
    }

    public RegistrationInvoker(HashMap<String, String> urlParams,
                               JSONObject postData) {
        super(urlParams, postData);
    }



}
