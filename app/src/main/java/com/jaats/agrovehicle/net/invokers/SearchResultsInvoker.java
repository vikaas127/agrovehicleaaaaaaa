package com.jaats.agrovehicle.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;

import com.jaats.agrovehicle.model.CarBean;
import com.jaats.agrovehicle.model.SearchResultsBean;
import com.jaats.agrovehicle.net.ServiceNames;
import com.jaats.agrovehicle.net.WebConnector;
import com.jaats.agrovehicle.net.parsers.CarInfoParser;
import com.jaats.agrovehicle.net.parsers.SearchResultsParser;
import com.jaats.agrovehicle.net.utils.WSConstants;

public class SearchResultsInvoker extends BaseInvoker {

    public SearchResultsInvoker(HashMap<String, String> urlParams,
                          JSONObject postData) {
        super(urlParams, postData);
    }

    public SearchResultsBean invokeSearchresultsWS() {

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.SEARCH_RESULTS), WSConstants.PROTOCOL_HTTP, urlParams, null);

        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
//    String wsResponseString=webConnector.connectToPOST_service();
        String wsResponseString = webConnector.connectToGET_service(true);
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        SearchResultsBean searchResultsBean = null;
        if (wsResponseString.equals("")) {
            return searchResultsBean = null;
        } else {
            searchResultsBean = new SearchResultsBean();
            SearchResultsParser searchResultsParser = new SearchResultsParser();
            searchResultsBean = searchResultsParser.parseSearchResultsResponse(wsResponseString);
            return searchResultsBean;
        }
    }
}
