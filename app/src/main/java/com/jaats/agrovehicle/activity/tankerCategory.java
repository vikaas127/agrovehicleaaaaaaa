package com.jaats.agrovehicle.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class tankerCategory extends AppCompatActivity {

    private static final String TAG = "LandingPA";
    RecyclerView rvRecyclerviewtanker;
    StringBuilder cb=null;
    CheckBox checkbox;

    TextView tractorname,tractorPrise,Tractorname2,TractorPrise2;
    String  Services="http://nkploggy.com/api/user/services";
    String tractorfare="http://nkploggy.com/api/user/mobile_fare";
    List<TankerProduct> tanker =new ArrayList<>();

    TankerListAdapter tankerAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanker_category);
        checkbox=(CheckBox)findViewById(R.id.checkbox_meat);

        rvRecyclerviewtanker=(RecyclerView)findViewById(R.id.rvRecyclerviewtanker);


        RequestQueue queue = Volley.newRequestQueue(tankerCategory.this);
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
                                    String serviceTypeTanker = json_data1.getString("service_type_id");
                                    String NameAddTanker = json_data1.getString("name");
                                    String priceAddTanker = json_data1.getString("price");
                                    String typeTanker=json_data1.getString("type");
                                    String image=json_data1.getString("image");
                                    //String type=json_data1.getString("type");


                                    TankerProduct tankerProduct = new TankerProduct(serviceTypeTanker, NameAddTanker, priceAddTanker,typeTanker,image);
                                    tanker.add(tankerProduct);
                                    //Toast.makeText(LandingPageActivity.this, (CharSequence) subcategory, Toast.LENGTH_SHORT).show();

                                }
                                //Toast.makeText(LandingPageActivity.this, Addons, Toast.LENGTH_SHORT).show();







                            }


                            tankerAdapter=new TankerListAdapter(tankerCategory.this,tanker);
                            rvRecyclerviewtanker .setAdapter(tankerAdapter);






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
                        Toast.makeText(tankerCategory.this, "Retry", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }


                }) {


        };

        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        queue.add(stringRequest);





    }


    public void Continue(View v){

        ToContinue();



    }

    public void ToContinue(){
        cb=new StringBuilder();
        ;

        int j=0;


        do {
            TankerProduct  tankerProduct=tankerAdapter.tankerProducts.get(j);

            cb.append(tankerProduct.getTankerSubcategoryPrice());


            if (j!=tankerAdapter.tankerProducts.size()-1 ){
                cb.append("\n");



            }


            j++;


        }while (j<tankerAdapter.tankerProducts.size());
        Toast.makeText(this, cb, Toast.LENGTH_SHORT).show();
        if (tankerAdapter.tankerProducts.size()>0){
            Log.e(TAG,"ToContinue: "+j );
            Toast.makeText(this, cb.toString(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Please Checked Item First", Toast.LENGTH_SHORT).show();
        }
        if (tankerAdapter.tankerProducts.size()<0){
            Toast.makeText(this, cb.toString(), Toast.LENGTH_SHORT).show();
        }


        RequestQueue queue = Volley.newRequestQueue(tankerCategory.this);


        final int finalI = j;
        Log.e(TAG, "ToContinue: "+j );
        Log.e(TAG, "ToContinue: "+cb );
        StringRequest stringRequest = new StringRequest(Request.Method.POST, tractorfare,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        Toast.makeText(tankerCategory.this, ServerResponse, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.


                        // Showing error message if something goes wrong.
                        Toast.makeText(tankerCategory.this, "Retry", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }


                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.


                params.put("s_latitude",  Config.getInstance().getCurrentLatitude());
                params.put("s_longitude",Config.getInstance().getCurrentLongitude());
                params.put("d_latitude","28.5528694");
                params.put("d_longitude","77.0456441");
                params.put("service_type","1");
                params.put("days","2");
                params.put("addons["+(finalI)+"]", String.valueOf(cb));
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

