package com.jaats.agrovehicle.listeners;

import com.jaats.agrovehicle.model.RecentSearchBean;

public interface RecentSearchListener {

    void onLoadCompleted(RecentSearchBean recentSearchBean);

    void onLoadFailed(String webErrorMsg);
}
