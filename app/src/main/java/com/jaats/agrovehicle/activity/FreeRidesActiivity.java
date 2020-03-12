package com.jaats.agrovehicle.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jaats.agrovehicle.R;
import com.jaats.agrovehicle.listeners.PromoCodeListener;
import com.jaats.agrovehicle.model.PromoCodeBean;

import org.json.JSONException;
import org.json.JSONObject;

public class FreeRidesActiivity extends BaseAppCompatNoDrawerActivity {
    String Url="http://nkploggy.com/api/user/mobile_promocodes";

    private Toolbar toolbarFreeRides;
    private PromoCodeBean promoCodeBean;
    private TextView txtPromoCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_rides);

        initViews();

//        swipeView.setRefreshing(true);
        setProgressScreenVisibility(true, true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

    }


    public void initViews() {

        coordinatorLayout.removeView(toolbar);

        toolbarFreeRides = (Toolbar) getLayoutInflater().inflate(R.layout.toolbar_free_rides, toolbar);
        coordinatorLayout.addView(toolbarFreeRides, 0);
        setSupportActionBar(toolbarFreeRides);

        txtPromoCode = (TextView) findViewById(R.id.txt_promo_code);

//        fetchPromoCode();
    }

    public void fetchPromoCode() {




    }

    private void populatePromoCode(PromoCodeBean promoCodeBean) {

        txtPromoCode.setText(promoCodeBean.getPromoCode());
    }
}
