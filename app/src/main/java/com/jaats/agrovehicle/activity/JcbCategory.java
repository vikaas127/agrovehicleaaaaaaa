package com.jaats.agrovehicle.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jaats.agrovehicle.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JcbCategory extends AppCompatActivity {

    RecyclerView recyclerviewjcb,recyclerviewaddonsejcb;
    String  Services="http://nkploggy.com/api/user/services";

    List<JCBProduct> JCB =new ArrayList<>();
    JCBListAdapter SubAdapter;

    List<JcbProductAddonse> jcbproduct=new ArrayList<>();
    JcbAddonseListAdapter AddonseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jcb_category);


        recyclerviewjcb=(RecyclerView)findViewById(R.id.recyclerviewjcb);

        recyclerviewaddonsejcb=(RecyclerView)findViewById(R.id.recyclerviewaddonsejcb);



        RequestQueue queue = Volley.newRequestQueue(JcbCategory.this);


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

                                    JCBProduct jcbProduct = new JCBProduct(id1, serviceType, NameAdd, imageAdd, priceAdd,type,image);

                                    JCB.add(jcbProduct);
                                    //Toast.makeText(LandingPageActivity.this, (CharSequence) subcategory, Toast.LENGTH_SHORT).show();

                                }
                                //Toast.makeText(LandingPageActivity.this, Addons, Toast.LENGTH_SHORT).show();







                            }


                            SubAdapter=new JCBListAdapter(JcbCategory.this,JCB);

                          recyclerviewjcb.setAdapter(SubAdapter);







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
                        Toast.makeText(JcbCategory.this, "Retry", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }


                }) {


        };

        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        queue.add(stringRequest);





        RequestQueue queue1 = Volley.newRequestQueue(JcbCategory.this);
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


                                    JcbProductAddonse jcbProductAddons = new JcbProductAddonse(id1, serviceType, NameAdd, imageAdd, priceAdd,type,image);
                                    jcbproduct.add(jcbProductAddons);
                                    //Toast.makeText(LandingPageActivity.this, (CharSequence) subcategory, Toast.LENGTH_SHORT).show();

                                }
                                //Toast.makeText(LandingPageActivity.this, Addons, Toast.LENGTH_SHORT).show();


                            }

                            AddonseAdapter =new JcbAddonseListAdapter(JcbCategory.this,jcbproduct);
                            recyclerviewaddonsejcb .setAdapter(AddonseAdapter);






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
                        Toast.makeText(JcbCategory.this, "Retry", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }


                }) {


        };

        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        queue1.add(string);



    }
    public void JcbContinue(View v){


    }

}
