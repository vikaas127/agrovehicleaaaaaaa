package com.jaats.agrovehicle.activity;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jaats.agrovehicle.R;
import com.jaats.agrovehicle.listeners.FreeRideListener;
import com.jaats.agrovehicle.model.FreeRideBean;
import com.jaats.agrovehicle.model.TripBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionActivity extends BaseAppCompatNoDrawerActivity {
    CouponListAdapter adapter;
    List<CouponProduct> productList=new ArrayList<>();
    String Url = "http://nkploggy.com/api/user/mobile_promocodes?id=111",pid;
    private Toolbar toolbarPromotion;
    private TextView txtDateTime;
    private TripBean bean;
    private EditText etxtPromoCode;
    private TextView txtAddCode;
    private String freeRideCode;
    ListView coupon_list_view;
    LinearLayout couponListCardView;
    ListAdapter couponAdapter;
    Context context;
RecyclerView rvTrips;
    private View.OnClickListener snackBarRefreshOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        initViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (etxtPromoCode.isShown()) {
                onBackClick();

            } else {
                onBackPressed();
            }
        }
        return true;
    }

    private void onBackClick() {

       /* etxtPromoCode.setVisibility(View.GONE);
        txtAddCode.setText("ADD CODE");*/
        finish();
    }

    public void initViews() {

        coordinatorLayout.removeView(toolbar);

        toolbarPromotion = (Toolbar) getLayoutInflater().inflate(R.layout.toolbar_promotion, toolbar);
        coordinatorLayout.addView(toolbarPromotion, 0);
        setSupportActionBar(toolbarPromotion);
        rvTrips = (RecyclerView) findViewById(R.id.coupon_RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTrips.setLayoutManager(layoutManager);
        rvTrips.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        performFreeRide();
        //etxtPromoCode = (EditText) findViewById(R.id.etxt_promo_code);
        //txtAddCode = (TextView) findViewById(R.id.txt_add_code);

    }

    public boolean validatePromocode() {

        boolean flag = true;

        if (etxtPromoCode.getText().toString().length() <= 2) {
            Snackbar.make(coordinatorLayout, R.string.message_enter_a_valid_promocode, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.btn_refresh, snackBarRefreshOnClickListener).show();
            etxtPromoCode.requestFocus();
            flag = false;
        }
        return flag;
    }

    public void performFreeRide() {
        RequestQueue queue = Volley.newRequestQueue(PromotionActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject product = array.getJSONObject(i);

                                int id=product.getInt("id");
                                String promo_code=product.getString("promo_code");
                                int discount=product.getInt("discount");
                                String discount_type=product.getString("discount_type");
                                String expiration=product.getString("expiration");
                                String status=product.getString("status");
                                String deleted_at=product.getString("deleted_at");

                                CouponProduct couponProduct= new CouponProduct(id,promo_code,discount,discount_type,expiration,status,deleted_at);
                                productList.add(couponProduct);
                               // Toast.makeText(PromotionActivity.this, response, Toast.LENGTH_SHORT).show();

                            }

                            adapter=new CouponListAdapter(PromotionActivity.this,productList);
                            rvTrips.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })

        {
        @Override
        protected Map<String, String> getParams() {

            // Creating Map String Params.
            Map<String, String> params = new HashMap<String, String>();

            // Adding All values to Params.


            params.put("id",pid);
            // params.put("password",passwordHolder);
            return params;
        }

    };

    // Creating RequestQueue.


    // Adding the StringRequest object into requestQueue.
        queue.add(stringRequest);







    //  swipeView.setRefreshing(true);




    ;

    // Creating RequestQueue.



}

    private ArrayList<JSONObject> getArrayListFromJSONArray(JSONArray jsonArray) {

        ArrayList<JSONObject> aList = new ArrayList<JSONObject>();

        try {
            if (jsonArray != null) {

                for (int i = 0; i < jsonArray.length(); i++) {

                    aList.add(jsonArray.getJSONObject(i));

                }
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }

        return aList;

    }



    private JSONObject getFreeRideJSObj() {
        JSONObject postData = new JSONObject();

        freeRideCode = etxtPromoCode.getText().toString();

        try {
            postData.put("free_ride_code", freeRideCode);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }

    public void onAddCodeClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        if (etxtPromoCode.isShown()) {
            if (validatePromocode()) {
                performFreeRide();
            }
        } else {
            etxtPromoCode.setVisibility(View.VISIBLE);
            txtAddCode.setText(R.string.btn_apply_promo_code);
        }
    }
}
