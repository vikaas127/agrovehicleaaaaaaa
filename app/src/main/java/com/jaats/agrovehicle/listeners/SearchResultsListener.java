package com.jaats.agrovehicle.listeners;


import com.jaats.agrovehicle.model.SearchResultsBean;

public interface SearchResultsListener {

    void onLoadCompleted(SearchResultsBean searchResultsBean);

    void onLoadFailed(String webErrorMsg);
}
