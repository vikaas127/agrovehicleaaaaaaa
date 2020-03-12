package com.jaats.agrovehicle.activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jaats.agrovehicle.R;
import com.jaats.agrovehicle.adapter.TripDetailsRecyclerAdapter;
import com.jaats.agrovehicle.model.TripBean;
import com.jaats.agrovehicle.model.TripListBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TripsActivity extends BaseAppCompatNoDrawerActivity {
   String Url= "http://nkploggy.com/api/user/mobiletrips?id=3",pid;

   ProductAdapter adapter;
   List<TripsProduct> productList=new ArrayList<>();

    RelativeLayout errorLayout;
TextView tvv;
    private Toolbar toolbarTrips;
    private TripBean recentSearchBean;
    private MapView mMapView;
    private TripListBean tripListBean;
    private TripDetailsRecyclerAdapter recyclerAdapter;
    private RecyclerView rvTrips;
    private GoogleMap map;
    private String tripList;

   /* private static GoogleMapOptions options = new GoogleMapOptions()
            .mapType(GoogleMap.MAP_TYPE_NORMAL)
            .compassEnabled(false)
            .rotateGesturesEnabled(false)
            .tiltGesturesEnabled(false)
            .zoomControlsEnabled(false)
            .scrollGesturesEnabled(false)
            .mapToolbarEnabled(false);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        fetchTripsList();
        initViews();

        getSupportActionBar().setTitle(R.string.title_trip_list);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

       // swipeView.setRefreshing(true);

    }

    public void initViews() {


tvv=(TextView)findViewById(R.id.tv);
        rvTrips = (RecyclerView) findViewById(R.id.rv_trips);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTrips.setLayoutManager(layoutManager);
        rvTrips.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));


//        mMapView = (MapView) findViewById(R.id.list_map_view);

    }



    public void fetchTripsList() {


        RequestQueue queue = Volley.newRequestQueue(TripsActivity.this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
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

    private void populateTripList() {

        if (recyclerAdapter == null) {
            recyclerAdapter = new TripDetailsRecyclerAdapter(this, tripListBean);

            recyclerAdapter.setTripDetailsRecyclerAdapterListener(new TripDetailsRecyclerAdapter.TripDetailsRecyclerAdapterListener() {

                @Override
                public void onRequestNextPage(boolean isLoadMore, int currentPageNumber) {

                }

                @Override
                public void onItemSelected(TripBean bean) {

                }

                @Override
                public void onRefresh() {

                }

                @Override
                public void onSwipeRefreshingChange(boolean isSwipeResfreshing) {

                }

                @Override
                public void onSnackBarShow(String message) {

                }
            });

            rvTrips.setAdapter(recyclerAdapter);

        } else {

            recyclerAdapter.setTripListBean(tripListBean);
            recyclerAdapter.notifyDataSetChanged();
        }

        if (tripListBean.getTrips() == null || tripListBean.getTrips().isEmpty()) {
            setMessageScreenVisibility(true, getString(R.string.message_no_trips_taken_yet));
        } else {
            setMessageScreenVisibility(false, getString(R.string.message_no_trips_taken_yet));

        }

        setProgressScreenVisibility(false, false);
        swipeView.setRefreshing(false);
    }
}
