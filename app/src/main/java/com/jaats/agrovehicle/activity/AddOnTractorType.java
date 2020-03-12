package com.jaats.agrovehicle.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jaats.agrovehicle.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jaats.agrovehicle.activity.LandingPageActivity.EXTRA_IMAGE;
import static com.jaats.agrovehicle.activity.LandingPageActivity.EXTRA_NAME;
import static com.jaats.agrovehicle.activity.LandingPageActivity.EXTRA_PROVIDER;

public class AddOnTractorType extends AppCompatActivity {
    RecyclerView AddOnRV;
    TextView text_view1,text_view2;
    ImageView iv;
    List< SubcategoryProduct> subcategory =new ArrayList<>();
    SubadapterListAdapter SubAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_on_tractor_type);
        //AddOnRV=(RecyclerView)findViewById(R.id.AddOnRV);
        text_view1=(TextView) findViewById(R.id.text_view1);
        text_view2=(TextView) findViewById(R.id.text_view2);

        Intent intent=getIntent();
        String image=intent.getStringExtra(EXTRA_IMAGE);
        String price=intent.getStringExtra(EXTRA_PROVIDER);
        String name=intent.getStringExtra(EXTRA_NAME);

       // Picasso.get().load(image).fit().centerInside().into(iv);

        text_view1.setText(name);
        text_view2.setText(price);

      //  Toast.makeText(this, (CharSequence) SubAdapter, Toast.LENGTH_SHORT).show();
        //SubAdapter= new SubadapterListAdapter(AddOnTractorType.this,subcategory);
        //AddOnRV .setAdapter(SubAdapter);
    /*    SubadapterListAdapter adapter = new SubadapterListAdapter(AddOnTractorType.this,subcategory);
       AddOnRV.setHasFixedSize(true);
        AddOnRV.setLayoutManager(new LinearLayoutManager(this));
        AddOnRV.setAdapter(adapter);
*/
    }

/*
    public void fetchTripsList() {


        RequestQueue queue = Volley.newRequestQueue(AddOnTractorType.this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, FatchAddOn,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray Trips=object.getJSONArray("Trips");
                            for (int i=0;i<Trips.length();i++){
                                JSONObject productObject=Trips.getJSONObject(i);

                                int id=productObject.getInt("id");
                                String booking_id=productObject.getString("booking_id");
                                int user_id=productObject.getInt("user_id");
                                int provider_id=productObject.getInt("provider_id");
                                String payment_mode=productObject.getString("payment_mode");
                                String finished_at=productObject.getString("finished_at");
                                //Toast.makeText(TripsActivity.this, response, Toast.LENGTH_SHORT).show();
                                TripsProduct tripsProduct=new TripsProduct(id,booking_id,user_id, provider_id,payment_mode,finished_at);
                                productList.add(tripsProduct);

                            }
                            adapter=new ProductAdapter(TripsActivity.this,productList);
                            rvTrips.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.


                        // Showing error message if something goes wrong.
                        Toast.makeText(TripsActivity.this, "Retry", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }


                }) {
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




    }
*/
}
