package com.jaats.agrovehicle.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.jaats.agrovehicle.R;
import com.jaats.agrovehicle.adapter.SearchResultsRecyclerAdapter;
import com.jaats.agrovehicle.config.Config;
import com.jaats.agrovehicle.model.LocationBean;
import com.jaats.agrovehicle.model.PlaceBean;
import com.jaats.agrovehicle.model.PlaceTractorBean;
import com.jaats.agrovehicle.util.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchPageTractorLocation extends BaseAppCompatNoDrawerActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    String FatchsaveLocation="http://nkploggy.com/api/user/user_address";
    private static final String TAG = "search";

    private static final int REQ_SEARCH_WORK = 1;
    private static final int WORK_LOCATION = 1;
    String Locationsave=" http://nkploggy.com/api/user/add_user_address";
    private int locationSelect = AppConstants.LOCATION_SELECTED_ONITEMCLICK;

    private SearchResultsRecyclerAdapter adapterSearch;
    private RecyclerView rvSearchResults;



    private TextView txtWork;
    private int searchType;
    private GoogleApiClient mGoogleApiClient;
    private int addHome;
    private int addWork;
    private PlaceTractorBean workLocationBean;

    private PlaceTractorBean placeBean;
    FatchLocationAdapter adapter;
    List<fatchLocationProduct> LocationproductList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page_tractor_location);

        initViews();

        searchType = getIntent().getIntExtra("search_type", 0);

       /* if (searchType == AppConstants.SEARCH_SOURCE) {

            etxtSearchPlace.setHint(R.string.hint_enter_the_source);
        } else if (searchType == AppConstants.SEARCH_DESTINATION) {
            etxtSearchPlace.setHint(R.string.hint_enter_the_destination);
        } else {
            etxtSearchPlace.setHint(R.string.hint_enter_the_destination);
        }
*/
       /* getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);*/

       /* mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();*/
        getSupportActionBar().hide();
        swipeView.setPadding(0, 0, 0, 0);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    public void initViews() {

        Places.initialize(getApplicationContext(), getString(R.string.api_key));

        PlacesClient placesClient = Places.createClient(SearchPageTractorLocation.this);

        //  setProgressScreenVisibility(true, true);

        fetchSavedLocation();

        coordinatorLayout.removeView(toolbar);

       /* toolbarSearch = (Toolbar) getLayoutInflater().inflate(R.layout.toolbar_search_page, toolbar);
        coordinatorLayout.addView(toolbarSearch, 0);
        setSupportActionBar(toolbarSearch);

        etxtSearchPlace = (EditText) toolbarSearch.findViewById(R.id.etxt_search_place);*/


        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.search_autocomplete_fragment);

        assert autocompleteFragment != null;
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {

                //Log.e( "Aniket ", "place");
                placeBean = new PlaceTractorBean();

                if (place != null) {
                    placeBean.setAddress(String.valueOf(place.getAddress()));
                    placeBean.setLatitude(String.valueOf(place.getLatLng().latitude));
                    placeBean.setLongitude(String.valueOf(place.getLatLng().longitude));
                    placeBean.setName(place.getName());

                    Log.e(TAG, "onPlaceSelected: "+place.getLatLng().latitude );
                    Log.e(TAG, "onPlaceSelected: "+place.getLatLng().longitude );
                    Log.e(TAG, "onPlaceSelected: "+place.getAddress() );
                    Log.e(TAG, "onPlaceSelected: "+place.getName() );
                }

                Intent intent = new Intent();
                intent.putExtra("bean", placeBean);
                intent.putExtra("locationSelect", locationSelect);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onError(Status status) {

            }
        });


        rvSearchResults = (RecyclerView) findViewById(R.id.rv_search_results);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvSearchResults.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSearchResults.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));


        txtWork = (TextView) findViewById(R.id.txt_search_page_add_work);







       /* etxtSearchPlace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                txtInput = etxtSearchPlace.getText().toString();

                Log.i(TAG, "onTextChanged: Text" + txtInput);

                txtInputResponse();

                swipeView.setRefreshing(true);
//                populateSearchResults();

                PlaceListTask placesListTask = new PlaceListTask(txtInput);
                placesListTask.setStrAddress(txtInput);
                placesListTask.execute();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if (requestCode == REQ_SEARCH_WORK && resultCode == RESULT_OK) {

            workLocationBean = (PlaceTractorBean) data.getSerializableExtra("location");
            Log.e(TAG, "onActivityResult: "+workLocationBean.getDLatitude());
            Log.e(TAG, "onActivityResult: "+workLocationBean.getDLongitude() );

            txtWork.setText(workLocationBean.getName());

            Toast.makeText(getApplicationContext(), R.string.message_work_location_added,
                    Toast.LENGTH_LONG).show();

            performLocationSave(WORK_LOCATION);

        }
    }

    private void performLocationSave(final int type) {
        swipeView.setRefreshing(false);


        RequestQueue queue = Volley.newRequestQueue(SearchPageTractorLocation.this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Locationsave,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {



                        if (type == WORK_LOCATION) {
                            Config.getInstance().setAddWork(workLocationBean.getName());
                            Config.getInstance().setWorkLatitude(workLocationBean.getLatitude());
                            Config.getInstance().setWorkLongitude(workLocationBean.getLongitude());
                            SharedHelper.putKey(SearchPageTractorLocation.this, "lattitude",workLocationBean.getLatitude());
                            SharedHelper.putKey(SearchPageTractorLocation.this,"longitude",workLocationBean.getLongitude());
                            SharedHelper.putKey(SearchPageTractorLocation.this,"name",workLocationBean.getName());
                        }



                        Toast.makeText(SearchPageTractorLocation.this, ServerResponse, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.


                        // Showing error message if something goes wrong.
                        //Toast.makeText(SearchPageActivity.this, "Retry", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }


                }) {
            @Override
            protected Map<String, String> getParams() {


                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();


                // Adding All values to Params.

                if (type == WORK_LOCATION){

                    params.put("user_id",SharedPrefManager.getInstans(getApplicationContext()).getUserId());

                    params.put("tag", "work");
                    params.put("name",workLocationBean.getName());
                    params.put("lattitude", workLocationBean.getLatitude());
                    params.put("longitude", workLocationBean.getLongitude());
                }


                return params;
            }

        };

        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        queue.add(stringRequest);




    }


   /* @Override
    public void onConnected(@Nullable Bundle bundle) {
        PendingResult<AutocompletePredictionBuffer> result = Places.GeoDataApi.getAutocompletePredictions(mGoogleApiClient, "infopark", null, null);

        result.setResultCallback(new ResultCallback<AutocompletePredictionBuffer>() {
            @Override
            public void onResult(@NonNull AutocompletePredictionBuffer autocompletePredictions) {
                Log.i(TAG, "onConnected: Result" + autocompletePredictions);


            }
        });
    }


    private class PlaceListTask extends AsyncTask<String, Integer, ArrayList<AutocompletePrediction>> {

        private String strAddress;

        public PlaceListTask(String strAddress) {
            super();
            this.strAddress = strAddress;
        }

        public String getStrAddress() {
            return strAddress;
        }

        public void setStrAddress(String strAddress) {
            this.strAddress = strAddress;
        }

        @Override
        protected ArrayList<AutocompletePrediction> doInBackground(String... params) {

            if (mGoogleApiClient.isConnected()) {
                Log.i(TAG, "Starting autocomplete query for: " + strAddress);

                PendingResult<AutocompletePredictionBuffer> results =
                        Places.GeoDataApi
                                .getAutocompletePredictions(mGoogleApiClient, strAddress, null,
                                        null);

                AutocompletePredictionBuffer autocompletePredictions = results
                        .await(60, TimeUnit.SECONDS);

                final com.google.android.gms.common.api.Status status = autocompletePredictions.getStatus();
                if (!status.isSuccess()) {
                    Log.e(TAG, "Error getting autocomplete prediction API call: " + status.toString());
                    autocompletePredictions.release();
                    return null;
                }

                Log.i(TAG, "Query completed. Received " + autocompletePredictions.getCount()
                        + " predictions.");

                return DataBufferUtils.freezeAndClose(autocompletePredictions);
            }
            Log.e(TAG, "Google API client is not connected for autocomplete query.");
            return null;

        }

        @Override
        protected void onPostExecute(ArrayList<AutocompletePrediction> result) {
            super.onPostExecute(result);

            if (result != null) {

                populateSearchResults(result);

            } else {
                swipeView.setRefreshing(false);

            }
        }
    }


    private void populateSearchResults(ArrayList<AutocompletePrediction> result) {

        if (adapterSearch == null) {
            adapterSearch = new SearchResultsRecyclerAdapter(this, mGoogleApiClient, result);

            adapterSearch.setSearchResultsRecyclerAdapterListener(new SearchResultsRecyclerAdapter.SearchResultsRecyclerAdapterListener() {

                public PlaceBean placeBean;

                @Override
                public void onItemSelected(Place place) {

                    locationSelect = AppConstants.LOCATION_SELECTED_ONITEMCLICK;

                    placeBean = new PlaceBean();

                    placeBean.setAddress(String.valueOf(place.getAddress()));
                    placeBean.setLatitude(String.valueOf(place.getLatLng().latitude));
                    placeBean.setLongitude(String.valueOf(place.getLatLng().longitude));
                    placeBean.setName((String) place.getName());

                    Intent intent = new Intent();
                    intent.putExtra("bean", placeBean);
                    intent.putExtra("locationSelect", locationSelect);
                    setResult(RESULT_OK, intent);
                    finish();

                }

                @Override
                public void onSnackBarShow(String message) {

                }
            });
            rvSearchResults.setAdapter(adapterSearch);
        } else {

            adapterSearch.setmResultList(result);
            adapterSearch.notifyDataSetChanged();
        }

        setProgressScreenVisibility(false, false);
        swipeView.setRefreshing(false);

    }

    private void txtInputResponse() {

        if (etxtSearchPlace.getText().toString().length() >= 1) {

            llHome.setVisibility(View.GONE);
            llWork.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
        } else {
            llHome.setVisibility(View.VISIBLE);
            llWork.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.VISIBLE);

        }
    }
*/


    public void onAddWorkClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        addWork = AppConstants.ADD_WORK;

        if (Config.getInstance().getAddWork() == null || Config.getInstance().getAddWork().equalsIgnoreCase("null")
                || Config.getInstance().getAddWork().equalsIgnoreCase("")) {
            Intent intent = new Intent(SearchPageTractorLocation.this, SearchHomeWorkActivity.class);
            intent.putExtra("search_type", addWork);
            startActivityForResult(intent, REQ_SEARCH_WORK);
        } else {

            locationSelect = AppConstants.LOCATION_SELECTED_ONWORKCLICK;

            Intent intent = new Intent();

            PlaceBean placeBean = new PlaceBean();
            placeBean.setName(Config.getInstance().getAddWork());
            placeBean.setLatitude(Config.getInstance().getWorkLatitude());
            placeBean.setLongitude(Config.getInstance().getWorkLongitude());
            intent.putExtra("bean", placeBean);
           // Log.e(TAG, "PlaceBean "+placeBean );
            intent.putExtra("locationSelect", locationSelect);


            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.e(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());

        // TODO(Developer): Check error code and notify the user of error state and resolution.
        Toast.makeText(this,
                "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(),
                Toast.LENGTH_SHORT).show();

    }

    public void fetchSavedLocation() {


        RequestQueue queue = Volley.newRequestQueue(SearchPageTractorLocation.this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, FatchsaveLocation,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        try {
                            JSONObject object = new JSONObject(ServerResponse);
                            JSONArray Address=object.getJSONArray("address");
                            for (int i=0;i<Address.length();i++){

                                JSONObject productObject=Address.getJSONObject(i);

                                // String tag=productObject.getString("tag");
                                String d_name=productObject.getString("name");
                                String s_name=productObject.getString("name");

                                fatchLocationProduct fatchlocationproduct=new fatchLocationProduct(d_name,s_name);
                                LocationproductList.add(fatchlocationproduct);
                                //  Log.e(TAG, "onResponse: ",s_name );
                                // Toast.makeText(SearchPageActivity.this, ServerResponse, Toast.LENGTH_SHORT).show();

                            }
                            adapter=new FatchLocationAdapter(SearchPageTractorLocation.this,LocationproductList);
                            rvSearchResults.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                        //Toast.makeText(SearchPageActivity.this, ServerResponse, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.


                        // Showing error message if something goes wrong.
                        Toast.makeText(SearchPageTractorLocation.this, "Retry", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }


                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.


                params.put("id", (SharedPrefManager.getInstans(getApplicationContext()).getUserId()));

                return params;
            }



        };

        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        queue.add(stringRequest);




    }

    private void setLocationBean(LocationBean locationBean) {

        Config.getInstance().setAddHome(locationBean.getHomeLocation());
        Config.getInstance().setHomeLatitude(locationBean.getHomeLatitude());
        Config.getInstance().setHomeLongitude(locationBean.getHomeLongitude());
        Config.getInstance().setAddWork(locationBean.getWorkLocation());
        Config.getInstance().setWorkLatitude(locationBean.getWorkLatitude());
        Config.getInstance().setWorkLongitude(locationBean.getWorkLongitude());

    }


}

