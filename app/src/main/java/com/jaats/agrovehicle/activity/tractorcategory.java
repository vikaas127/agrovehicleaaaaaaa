package com.jaats.agrovehicle.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jaats.agrovehicle.R;
import com.jaats.agrovehicle.config.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jaats.agrovehicle.activity.LandingPageActivity.EXTRA_NAME;

import static com.jaats.agrovehicle.activity.LandingPageActivity.EXTRA_PROVIDER;

public class tractorcategory extends AppCompatActivity {


    private static final String TAG = "LandingPA";
    RecyclerView rvRecyclerview2,rvRecyclerview3;
    StringBuilder sb=null;
    StringBuilder ad=null;
    CheckBox checkbox;

    TextView tractorname,tractorPrise,Tractorname2,TractorPrise2;
    String  Services="http://nkploggy.com/api/user/services";
    String tractorfare="http://nkploggy.com/api/user/mobile_fare";
    List<TractorProduct>tractor =new ArrayList<>();
    TractorListAdapter SubAdapter;
    List<TractorProductAddons>tractorproduct=new ArrayList<>();
    TractorAddonseListAdapter AddonseAdapter;
    List<VehiclFatchProduct> VehicleList=new ArrayList<>();
    VehicleListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tractorcategory);
        checkbox=(CheckBox)findViewById(R.id.checkbox_meat);

        Tractorname2=(TextView)findViewById(R.id.tractorName2);
        TractorPrise2=(TextView)findViewById(R.id.tractorPrise2);
        rvRecyclerview2=(RecyclerView)findViewById(R.id.rvRecyclerview2);
        rvRecyclerview3=(RecyclerView)findViewById(R.id.rvRecyclerview3);

        RequestQueue queue = Volley.newRequestQueue(tractorcategory.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Services,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        try {
                            JSONArray array = new JSONArray(ServerResponse);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject product ,product1 = array.getJSONObject(i);


                                JSONArray jArray1 = product1.getJSONArray("addons");



                                for (int j = 0; j < jArray1.length(); j++) {



                                        JSONObject json_data1 = jArray1.getJSONObject(j);

                                        int id1 = json_data1.getInt("id");
                                        String serviceType = json_data1.getString("service_type_id");
                                        String NameAdd = json_data1.getString("name");
                                        String imageAdd = json_data1.getString("image");
                                        String priceAdd = json_data1.getString("price");
                                        String type=json_data1.getString("type");
                                       String image=product1.getString("image");
                                        //String type=json_data1.getString("type");


                                    TractorProduct tractorProduct = new TractorProduct(id1, serviceType, NameAdd, imageAdd, priceAdd,type,image);
                                        tractor.add(tractorProduct);
                                        //Toast.makeText(LandingPageActivity.this, (CharSequence) subcategory, Toast.LENGTH_SHORT).show();

                                }
                                //Toast.makeText(LandingPageActivity.this, Addons, Toast.LENGTH_SHORT).show();


                            }


                            SubAdapter=new TractorListAdapter(tractorcategory.this,tractor);
                            rvRecyclerview2 .setAdapter(SubAdapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(LandingPageActivity.this, ServerResponse, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.


                        // Showing error message if something goes wrong.
                        Toast.makeText(tractorcategory.this, "Retry", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {
        };

        // Creating RequestQueue.
        // Adding the StringRequest object into requestQueue.
        queue.add(stringRequest);

        RequestQueue queue1 = Volley.newRequestQueue(tractorcategory.this);
        StringRequest string = new StringRequest(Request.Method.GET, Services,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        try {
                            JSONArray array = new JSONArray(ServerResponse);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject product ,product1 = array.getJSONObject(i);

                                JSONArray jArray1 = product1.getJSONArray("addons");

                                for (int j = 0; j < jArray1.length(); j++) {

                                    JSONObject json_data1 = jArray1.getJSONObject(j);

                                    int id1 = json_data1.getInt("id");
                                    String serviceType = json_data1.getString("service_type_id");
                                    String NameAdd = json_data1.getString("name");
                                    String imageAdd = json_data1.getString("image");
                                    String priceAdd = json_data1.getString("price");
                                    String type=json_data1.getString("type");
                                    String image=product1.getString("image");
                                    //String type=json_data1.getString("type");


                                    TractorProductAddons tractorProductAddons = new TractorProductAddons(id1, serviceType, NameAdd, imageAdd, priceAdd,type,image);
                                    tractorproduct.add(tractorProductAddons);
                                    //Toast.makeText(LandingPageActivity.this, (CharSequence) subcategory, Toast.LENGTH_SHORT).show();

                                }
                                //Toast.makeText(LandingPageActivity.this, Addons, Toast.LENGTH_SHORT).show();



                            }

                            AddonseAdapter =new TractorAddonseListAdapter(tractorcategory.this,tractorproduct);
                            rvRecyclerview3 .setAdapter(AddonseAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(LandingPageActivity.this, ServerResponse, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.


                        // Showing error message if something goes wrong.
                        Toast.makeText(tractorcategory.this, "Retry", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }


                }) {


        };

        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        queue1.add(string);

    }

    public void Continue(View v){

            ToContinue();

    }

    public void ToContinue() {

        sb = new StringBuilder();
        ad = new StringBuilder();

        int i = 0;


        do {
            TractorProduct tractorProduct = SubAdapter.tractor.get(i);
            TractorProductAddons tractorProductAddons = AddonseAdapter.tractorproduct.get(i);
            sb.append(tractorProduct.getTractorSubcategoryPrice());
            ad.append(tractorProductAddons.getTractorSubcategoryPrice());

            if (i != SubAdapter.tractor.size() - 1 && i != AddonseAdapter.tractorproduct.size() - 1) {
                sb.append("\n");
                ad.append("\n");

            }

            i++;


        } while (i < SubAdapter.tractor.size() && i < AddonseAdapter.tractorproduct.size());
        Toast.makeText(this, sb, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ad, Toast.LENGTH_SHORT).show();
        if (SubAdapter.tractor.size() > 0) {
            Log.e(TAG, "ToContinue: " + i);
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please Checked Item First", Toast.LENGTH_SHORT).show();
        }
        if (SubAdapter.tractor.size() < 0) {
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        }


        RequestQueue queue = Volley.newRequestQueue(tractorcategory.this);


        final int finalI = i;
        Log.e(TAG, "ToContinue: " + i);
        Log.e(TAG, "ToContinue: " + sb);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, tractorfare,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        Toast.makeText(tractorcategory.this, ServerResponse, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.


                        // Showing error message if something goes wrong.
                        Toast.makeText(tractorcategory.this, "Retry", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }


                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.


                params.put("s_latitude", Config.getInstance().getCurrentLatitude());
                params.put("s_longitude", Config.getInstance().getCurrentLongitude());
                params.put("d_latitude", "28.5528694");
                params.put("d_longitude", "77.0456441");
                params.put("service_type", "1");
                params.put("days", "2");
                params.put("addons[" + (finalI) + "]", String.valueOf(sb));
                // params.put("addons",jsonObject.toString());
                // params.put("addons",sb);

                return params;
            }

        };

        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        queue.add(stringRequest);

    }






}
