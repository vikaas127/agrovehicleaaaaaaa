package com.jaats.agrovehicle.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;

import com.jaats.agrovehicle.model.RecentSearchBean;
import com.jaats.agrovehicle.net.ServiceNames;
import com.jaats.agrovehicle.net.WebConnector;
import com.jaats.agrovehicle.net.parsers.RecentSearchParser;
import com.jaats.agrovehicle.net.utils.WSConstants;

public class RecentSearchInvoker extends BaseInvoker {

    private RecentSearchBean recentSearchBean;

    public RecentSearchInvoker(HashMap<String, String> urlParams,
                               JSONObject postData) {
        super(urlParams, postData);
    }

    public RecentSearchBean invokeRecentSearchWS() {

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.RECENT_SEARCHES), WSConstants.PROTOCOL_HTTP, urlParams, null);

        String wsResponseString = webConnector.connectToGET_service(true);
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        RecentSearchBean recentSearchBean = null;

        if (wsResponseString.equals("")) {

            return recentSearchBean = null;

        } else {
            recentSearchBean = new RecentSearchBean();
            RecentSearchParser recentSearchParser = new RecentSearchParser();
            recentSearchBean = recentSearchParser.parseRecentSearchResponse(wsResponseString);
            return recentSearchBean;
        }
    }
}
