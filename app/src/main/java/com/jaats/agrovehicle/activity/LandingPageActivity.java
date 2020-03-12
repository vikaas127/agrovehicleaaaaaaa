package com.jaats.agrovehicle.activity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jaats.agrovehicle.R;
import com.jaats.agrovehicle.adapter.CarTypeRecyclerAdapter;
import com.jaats.agrovehicle.app.App;
import com.jaats.agrovehicle.config.Config;
import com.jaats.agrovehicle.dialogs.PopupMessage;
import com.jaats.agrovehicle.listeners.BasicListener;
import com.jaats.agrovehicle.listeners.CarInfoListener;
import com.jaats.agrovehicle.listeners.LandingPageListener;
import com.jaats.agrovehicle.listeners.LocationUpdateListener;
import com.jaats.agrovehicle.listeners.PermissionListener;
import com.jaats.agrovehicle.listeners.PolyPointsListener;
import com.jaats.agrovehicle.listeners.TotalFareListener;
import com.jaats.agrovehicle.model.BasicBean;
import com.jaats.agrovehicle.model.CarBean;
import com.jaats.agrovehicle.model.DriverBean;
import com.jaats.agrovehicle.model.FareBean;
import com.jaats.agrovehicle.model.LandingPageBean;
import com.jaats.agrovehicle.model.PlaceBean;
import com.jaats.agrovehicle.model.PlaceTractorBean;
import com.jaats.agrovehicle.model.PolyPointsBean;
import com.jaats.agrovehicle.net.WSAsyncTasks.FCMRegistrationTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.LocationNameTask;
import com.jaats.agrovehicle.net.WSAsyncTasks.LocationTask;
import com.jaats.agrovehicle.util.AppConstants;


public class LandingPageActivity extends BaseAppCompatActivity implements



        GoogleMap.OnMyLocationButtonClickListener ,VehicleListAdapter.onItemClickListener,
        OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener
{
    String fetchtotalfareforcar="http://nkploggy.com/api/user/mobile_fare";
    String providerFatchonMap="http://nkploggy.com/api/user/mobile_all_providers";
    String Services="http://nkploggy.com/api/user/services";
    public static final String EXTRA_IMAGE="imageAdd";
    public static final String EXTRA_PROVIDER="priceAdd";
    public static final String EXTRA_NAME="NameAdd";

//    public static final int EXTRA_SERVICETYPE= Integer.parseInt("serviceType");
//int ServiceType= Integer.parseInt("serviceType");
//   public int ServiceType= Integer.parseInt("");
//    public  int EXTRA_ServiceType= Integer.parseInt("serviceType");
  public PlaceBean homeLocationBean;
    public PlaceBean workLocationBean;
    TextView mLatitudeTextView;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private static final int UPDATE_INTERVAL = 10000;
    private static final int FASTEST_INTERVAL = 5000;
    private static final int DISPLACEMENT = 10;
    private static final String TAG = "LandingPA";
    private String carType = String.valueOf(-1);
    private static final int REQ_SEARCH_SOURCE_SELECT = 0;
    private static final int REQ_SEARCH_DESTINATION_SELECT = 1;

    private static final int REQ_DESTINATION_ESTIMATE_SELECT = 2;
    private static final int REQ_REQUEST_RIDE = 3;
    private static final int REQ_ESTIMATED_DESTINATION = 4;
    private static final int LOCATION_SOURCE = 0;
    private static final int LOCATION_DESTINATION = 1;

    private static GoogleMapOptions options = new GoogleMapOptions()
            .mapType(GoogleMap.MAP_TYPE_NORMAL)
            .compassEnabled(true)
            .rotateGesturesEnabled(true)
            .tiltGesturesEnabled(true)
            .zoomControlsEnabled(true)
            .scrollGesturesEnabled(true)
            .mapToolbarEnabled(true);

    //    private GoogleApiClient mGoogleApiClient;
    private Location LastLocation;
    private GoogleMap mMap;
    private Toolbar toolbarHome;
    private TextView txtActionSearch;
    private FrameLayout framePickup;
    private ImageView ivMarker;
    private ImageView ivBottomMarker;
    private LinearLayout llLandingBottomBar;
    private ImageView ivLocationButton;
    private SupportMapFragment mapFragment;
    //    private View lytBottom;
    private TextView txtTime;
    public TextView txtMaxSize;
    public TextView txtFare;

    //    private int searchPlaceType = AppConstants.SEARCH_SOURCE;
    private TextView txtSource;
    private LinearLayout llConfirmation;
    private LinearLayout llDestination;
    private boolean isConfirmationPage = false;
    private boolean isCameraMoved;
    public CardView cvConfirmationPage;
    public CardView cv_Destination_Page;
    private TextView txtDestination;
    private TextView txt1destination;
    private TextView txtTotalFare;
    private RelativeLayout rlFare;
    private View viewDottedLine;
    private TextView txtCarOne;
    private TextView txtCarTwo;
    private TextView txtCarThree;
    private TextView carFour;
    private TextView txtFareEstimate;
    private TextView txtTo;
    private LinearLayout llDestinationEstimated;
    private TextView txtEstimatedDestination;
    private Button btnRequest;
    private Button TankerRequest;
    private Button JCBRequest;
    private View.OnClickListener snackBarRefreshOnClickListener;

    public int searchType;
    private FareBean fareBean;
    private PolyPointsBean polyPointsBean;
    private Polyline polyLine;
    private LatLngBounds bounds;
    private LatLng newLatLng1;
    private LatLng newLatLng2;
    private LatLng newLatLng3;
    private LatLng newLatLng4;
    private ImageView carOneImage;
    private ImageView carTwoImage;
    private ImageView carThreeImage;
    private ImageView carFourImage;
    private TextView txtCarAvailability;
    private String time;
    private String distance;
    private boolean isDestinationEstimateSelect = false;
    private LinearLayout llFare;
    private LinearLayout Tanker_fare;
    private LinearLayout JCB_fare;
    private TextView txtCarArrivalEstimatedTime;
    private CarBean carBean;
    public LandingPageBean landingPageBean;
  private PlaceTractorBean destinationTractorBean;
  private PlaceTractorBean sourceTractorBean;
    private PlaceBean destinationBean;
    private PlaceBean sourceBean;
    private BottomSheetBehavior<LinearLayout> bottomSheetBehavior;
    private ViewGroup.LayoutParams param;
    private FrameLayout flLandingPage;
    private ViewGroup.LayoutParams param1;
    private TextView txtEstimatedFare;
    private boolean isMapInit = true;
    private TextView txtFareLabel;
    public LinearLayout llProgressBar;
    public LinearLayout llEstimation;
    private LinearLayout llConfirmationProgress;
    private boolean isInit = true;
    private CarTypeRecyclerAdapter adapterCarTypes;
    private RecyclerView rvCarTypes;
   public String sourceLatitude;
    public String sourceLongitude;
    public RecyclerView rvRecyclerview2;
    String selectedBrand;
    public FrameLayout frame_pickup_landing_page;


    Double lat;
    Double lng;
    List< SubcategoryProduct> subcategory =new ArrayList<>();
    SubadapterListAdapter SubAdapter;

    List<VehiclFatchProduct> VehicleList=new ArrayList<>();
    VehicleListAdapter adapter;

    List< ProviderFatchOnFatch> providerfatch =new ArrayList<>();
   // SubadapterListAdapter SubAdapter;


    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        isGetLocationEnabled = false;


       /* if (!checkForLocationPermissions()) {
            getLocationPermissions();
        } else {
            checkLocationSettingsStatus();
        }

        if (!checkForReadWritePermissions()) {
            getReadWritePermissions();
        }else{
            isGetLocationEnabled=true;
        }*/

        initViews();
        initMap();

       // setProgressScreenVisibility(true, true);
//        getData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(true);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isConfirmationPage) {
                onBackClick();
            } else {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    onBackPressed();
                }
            }
        }

        if (keyCode == KeyEvent.KEYCODE_MENU) {
            openOptionsMenu();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isConfirmationPage && sourceBean == null) {
            if (checkPlayServices()) {
                getCurrentLocation();
//            buildGoogleApiClient();
//            createLocationRequest();
            }
        }
    }

    private boolean checkPlayServices() {

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int result = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(result)) {
                googleApiAvailability.getErrorDialog(this, result,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }
        return true;
    }

    private void getData() {


            fetchLandingPageDetails();

    }

    public void initViews() {

        fatchCarMap();
        fetchCarDetails();

        snackBarRefreshOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                //  mVibrator.vibrate(25);
                getData();
            }
        };

        btnRequest = (Button) findViewById(R.id.btn_request);

        rlFare = (RelativeLayout) findViewById(R.id.rl_fare);
        cvConfirmationPage = (CardView) findViewById(R.id.cv_confirmation_page);
        cv_Destination_Page=(CardView)findViewById(R.id.cv_Destination_Page);
        coordinatorLayout.removeView(toolbar);
//      toolbar.setVisibility(View.GONE);
        toolbarHome = (Toolbar) getLayoutInflater().inflate(R.layout.toolbar_landing_page, toolbar);
        coordinatorLayout.addView(toolbarHome, 0);
        setSupportActionBar(toolbarHome);


rvRecyclerview2=(RecyclerView)findViewById(R.id.rvRecyclerview2);
      LinearLayoutManager layoutManager2=new LinearLayoutManager(this);
      layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
       rvRecyclerview2.setLayoutManager(layoutManager2);
        txtTime = (TextView) findViewById(R.id.txt_time);
        txtMaxSize = (TextView) findViewById(R.id.txt_max_size);
        txtFare = (TextView) findViewById(R.id.txt_fare);

        rvCarTypes = (RecyclerView) findViewById(R.id.rv_bottom_sheet_landing_car_types);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCarTypes.setLayoutManager(layoutManager);
        llEstimation = (LinearLayout) findViewById(R.id.ll_estimation);
        ivBottomMarker = (ImageView) findViewById(R.id.iv_bottom_marker);

        llConfirmationProgress = (LinearLayout) findViewById(R.id.ll_confirmation_progress);



        txtCarArrivalEstimatedTime = (TextView) findViewById(R.id.txt_min_time);

//        ivActionSearch = (ImageView) toolbarHome.findViewById(R.id.ic_action_search);
        mLatitudeTextView= (TextView) findViewById(R.id.LatitudeTextView);
        txtCarAvailability = (TextView) findViewById(R.id.txt_cars_available);
        txtSource = (TextView) findViewById(R.id.txt_source);
        txtDestination = (TextView) findViewById(R.id.txt_destination);
        txt1destination=(TextView)findViewById(R.id.txt1_destination);



        llFare = (LinearLayout) findViewById(R.id.ll_fare);
        Tanker_fare=(LinearLayout)findViewById(R.id.Tanker_fare);
        JCB_fare=(LinearLayout)findViewById(R.id.JCB_fare);

        flLandingPage = (FrameLayout) findViewById(R.id.fl_landing_page);

        framePickup = (FrameLayout) findViewById(R.id.frame_pickup_landing_page);
        ivMarker = (ImageView) findViewById(R.id.iv_marker);

        llLandingBottomBar = (LinearLayout) findViewById(R.id.ll_landing_estimation_bottom_sheet);
        ivLocationButton = (FloatingActionButton) findViewById(R.id.fab_location_button);

        txtActionSearch = (TextView) toolbarHome.findViewById(R.id.txt_action_search);

        txtTotalFare = (TextView) findViewById(R.id.txt_total_fare);

        viewDottedLine = (View) findViewById(R.id.view_dotted_line);
/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtActionSearch.setCompoundDrawablesRelative(null,null,null,null);
        }
*/
        llDestination=(LinearLayout)findViewById(R.id.llDestination);
        llConfirmation = (LinearLayout) findViewById(R.id.ll_confirmation);
        btnRequest = (Button) findViewById(R.id.btn_request);
        TankerRequest=(Button)findViewById(R.id.btn_Tanker_request);
        JCBRequest=(Button)findViewById(R.id.btn_JCB_request);



        setBottomSheetBehavior();

        param1 = flLandingPage.getLayoutParams();
        param1.height = (int) (height - getStatusBarHeight() - mActionBarHeight);
        Log.i(TAG, "onSlide: PAram Height : " + param1.height);
        flLandingPage.setLayoutParams(param1);

        LocationUpdateListener locationUpdateListener = new LocationUpdateListener() {
            @Override
            public void onLocationUpdated(final Location location) {
         String msg = "Updated Location: " +
                        Double.toString(location.getLatitude()) + "," +
                        Double.toString(location.getLongitude());
                mLatitudeTextView.setText(String.valueOf(location.getLatitude()));


                //  sourceBean= location.getLatitude();

                Config.getInstance().setCurrentLatitude("" + location.getLatitude());
                Config.getInstance().setCurrentLongitude("" + location.getLongitude());

                Log.d(TAG, "onLocationChanged: LATITUDE : " + location.getLatitude());
               Log.d(TAG, "onLocationChanged: LONGITUDE : " + location.getLongitude());

                if (isInit) {
                    getData();
                    isInit = false;
                }
                if (sourceBean == null && mMap != null) {
                    LastLocation = location;
                    displayLocation();
                }

            }
        };
        addLocationUpdateListener(locationUpdateListener);

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionCheckCompleted(int requestCode, boolean isPermissionGranted) {

                if (requestCode == REQUEST_PERMISSIONS_LOCATION & isPermissionGranted) {

                    Log.i(TAG, "onPermissionCheckCompleted: PERMISSION GRANTED !!!!");
                    if (checkLocationSettingsStatus() && checkPlayServices()) {
                        getCurrentLocation();
                    }
                }

            }
        };

        addPermissionListener(permissionListener);
        frame_pickup_landing_page=(FrameLayout)findViewById(R.id.frame_pickup_landing_page);


    }

    public void setBottomSheetBehavior() {

        bottomSheetBehavior = BottomSheetBehavior.from(llLandingBottomBar);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                /*if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_DRAGGING

                bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_SETTLING){
                    param = myMapFragment.getView().getLayoutParams();
                    param.height = (int) (height - getStatusBarHeight() - mActionBarHeight - bottomSheet.getHeight());
                    Log.i(TAG, "onSlide: PAram Height : " + param.height);
                    myMapFragment.getView().setLayoutParams(param);
                } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    param = myMapFragment.getView().getLayoutParams();
                    param.height = (int) (height - getStatusBarHeight() - mActionBarHeight - bottomSheet.getHeight());
                    Log.i(TAG, "onSlide: PAram Height : " + param.height);
                    myMapFragment.getView().setLayoutParams(param);
                } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    param = myMapFragment.getView().getLayoutParams();
                    param.height = (int) (height - getStatusBarHeight() - mActionBarHeight - bottomSheet.getHeight());
                    Log.i(TAG, "onSlide: PAram Height : " + param.height);
                    myMapFragment.getView().setLayoutParams(param);
                }/*/
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i(TAG, "onSlide: offset : " + slideOffset);
// mapFragmentView.animate().scaleX(1 - slideOffset).scaleY(1 - slideOffset).setDuration(0).start();

                try {
                    param = mapFragment.getView().getLayoutParams();
                    param.height = (int) (height - getStatusBarHeight() - mActionBarHeight/* - (80 * px * (1 - slideOffset))*/ - bottomSheet.getHeight() * (slideOffset));
//                Log.i(TAG, "onSlide: PAram Height : " + param.height);
                    mapFragment.getView().setLayoutParams(param);

                    param1 = flLandingPage.getLayoutParams();
                    param1.height = (int) (height - getStatusBarHeight() - mActionBarHeight /*- (80 * px * (1 - slideOffset))*/ - bottomSheet.getHeight() * (slideOffset));
                    Log.i(TAG, "onSlide: PAram Height : " + param1.height);
                    flLandingPage.setLayoutParams(param1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initMap() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_home_map);


        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.setPadding(0, (int) ((100 * px) + mActionBarHeight + getStatusBarHeight()), 0, (int) (100 * px));

                initMapLoad();

            }
        });
    }

    private void initMapLoad() {

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED || bottomSheetBehavior.getPeekHeight() == 100 * px) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {

            @Override
            public void onCameraMove() {

                /*if (sourceBean != null & destinationBean != null) {
                    fetchTotalfare();
                    txtFare.setText(fareBean.getTotalFare());
                }*/
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED || bottomSheetBehavior.getPeekHeight() == 100 * px) {
                    bottomSheetBehavior.setPeekHeight(0);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if (!isConfirmationPage) {
                    mMap.getUiSettings().setScrollGesturesEnabled(true);
                    mMap.setMaxZoomPreference(18f);
                    framePickup.setVisibility(View.INVISIBLE);
                    ivBottomMarker.setVisibility(View.INVISIBLE);
                    ivMarker.setVisibility(View.VISIBLE);
                    ivLocationButton.setVisibility(View.INVISIBLE);

                    isCameraMoved = true;
                }
            }
        });

        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                if (sourceBean != null & destinationBean != null) {
                    if (!isConfirmationPage) {
                        fetchPolyPoints(false);
                    }
                    if (fareBean != null) {
                        txtFare.setText(fareBean.getTotalFare());
                    }
                }

                if (sourceTractorBean!=null&destinationTractorBean!=null){
                    if (!isConfirmationPage){
                        fetchPolyPoints(false);
                    }
                    if (fareBean!=null){
                        txtFare.setText(fareBean.getTotalFare());
                    }
                }

                if (!isConfirmationPage) {

                    CameraPosition postion = mMap.getCameraPosition();
                    LatLng center = postion.target;

                    framePickup.setVisibility(View.VISIBLE);
                    ivBottomMarker.setVisibility(View.VISIBLE);
                    ivMarker.setVisibility(View.INVISIBLE);
                    ivLocationButton.setVisibility(View.VISIBLE);

                    if (bottomSheetBehavior.getPeekHeight() == 0) {
                        bottomSheetBehavior.setPeekHeight((int) (100 * px));
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                        llLandingBottomBar.animate().translationY(00*px).setDuration(1000).start();
                    }

                    Log.i(TAG, "onCameraIdle: GetLocationName Called : " + center);
                    if (isCameraMoved) {

                        getLocationName(String.valueOf(center.latitude), String.valueOf(center.longitude));
//                        getLocationName(center.latitude, center.longitude);

                        if (sourceBean == null)
                            sourceBean = new PlaceBean();
                        sourceBean.setLatitude(String.valueOf(center.latitude));
                        sourceBean.setLongitude(String.valueOf(center.longitude));
                        if (sourceTractorBean==null)
                            sourceTractorBean=new PlaceTractorBean();
                        sourceTractorBean.setLatitude(String.valueOf(center.latitude));
                        sourceTractorBean.setLongitude(String.valueOf(center.longitude));


                            fetchLandingPageDetails();
//                            fetchCarDetails();

                        if (destinationBean != null) {
//                            getEstimatedFare();
                        }
                        if (destinationTractorBean!=null){

                        }
                    }
                    isCameraMoved = false;
                }
            }
        });
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_SEARCH_DESTINATION_SELECT && resultCode == RESULT_OK) {

            destinationBean = (PlaceBean) data.getSerializableExtra("bean");


            if (sourceBean != null && destinationBean != null) {
                if (sourceBean.getName().equalsIgnoreCase(destinationBean.getName())) {

                    mMap.clear();
                    destinationBean = null;
                    txtDestination.setText("");

                    rlFare.setVisibility(View.GONE);

                    onSourceSelect();

                    Snackbar.make(coordinatorLayout, R.string.message_source_and_destination_are_same, Snackbar.LENGTH_LONG)
                            .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();

                }
            }



            Log.i(TAG, "onActivityResult: ON DESTINATION SELECT ");

            fetchCarDetails();

            if (destinationBean != null) {
                llFare.setVisibility(View.GONE);
                //llConfirmationProgress.setVisibility(View.VISIBLE);
            }
       if (destinationTractorBean!=null){
           JCB_fare.setVisibility(View.GONE);
           Tanker_fare.setVisibility(View.GONE);

       }

//            Log.i(TAG, "onActivityResult: DestinationLatitude : " + destinationBean.getDLatitude());
//            Log.i(TAG, "onActivityResult: DestinationLongitude : " + destinationBean.getDLongitude());



            if (destinationBean!=null){

                onDestinationSelect();

            }

        }

         /*hello how are you ==very bad*/

        if (requestCode ==REQ_DESTINATION_ESTIMATE_SELECT && resultCode == RESULT_OK) {

            destinationTractorBean = (PlaceTractorBean) data.getSerializableExtra("bean");


            if (sourceTractorBean != null && destinationTractorBean != null) {
                if (sourceTractorBean.getName().equalsIgnoreCase(destinationTractorBean.getName())) {

                    mMap.clear();

                    destinationTractorBean= null;

                    txt1destination.setText("");
                    rlFare.setVisibility(View.GONE);



                    Snackbar.make(coordinatorLayout, R.string.message_source_and_destination_are_same, Snackbar.LENGTH_LONG)
                            .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();

                }
            }

            Log.i(TAG, "onActivityResult: ON DESTINATION SELECT ");

            fetchCarDetails();

            if (destinationBean != null) {
                llFare.setVisibility(View.GONE);
                //llConfirmationProgress.setVisibility(View.VISIBLE);
            }


//            Log.i(TAG, "onActivityResult: DestinationLatitude : " + destinationBean.getDLatitude());
//            Log.i(TAG, "onActivityResult: DestinationLongitude : " + destinationBean.getDLongitude());



            if (destinationTractorBean!=null){
                JCB_fare.setVisibility(View.GONE);
                Tanker_fare.setVisibility((View.GONE));


                onDestinationTractorSelected();

            }



        }




        if (requestCode == REQ_SEARCH_SOURCE_SELECT && resultCode == RESULT_OK) {

            sourceBean = (PlaceBean) data.getSerializableExtra("bean");
           sourceTractorBean=(PlaceTractorBean)data.getSerializableExtra("bean");
            if (sourceBean != null && destinationBean != null) {
                if (sourceBean.getName().equalsIgnoreCase(destinationBean.getName())) {

                    mMap.clear();
                    destinationBean = null;
                    txtDestination.setText("");
                    txt1destination.setText("");
                    rlFare.setVisibility(View.GONE);

                    Snackbar.make(coordinatorLayout, R.string.message_source_and_destination_are_same, Snackbar.LENGTH_LONG)
                            .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
                }
            }

            if (sourceTractorBean != null && destinationTractorBean != null) {
                if (sourceTractorBean.getName().equalsIgnoreCase(destinationTractorBean.getName())) {

                    mMap.clear();
                    destinationTractorBean = null;
                    txt1destination.setText("");
                    rlFare.setVisibility(View.GONE);

                    Snackbar.make(coordinatorLayout, R.string.message_source_and_destination_are_same, Snackbar.LENGTH_LONG)
                            .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
                }
            }

            Log.i(TAG, "onActivityResult: SourceName" + sourceBean.getName());
//            Log.i(TAG, "onActivityResult: DestinationName" + destinationBean.getName());
            Log.i(TAG, "onActivityResult: SourceLatitude : " + sourceBean.getDLatitude());
            Log.i(TAG, "onActivityResult: SourceLongitude : " + sourceBean.getDLongitude());

            fetchCarDetails();
            if (sourceBean != null) {
                onSourceSelect();
            }
        }

        if (requestCode == REQ_REQUEST_RIDE && resultCode == RESULT_OK) {

            DriverBean driverBean = (DriverBean) data.getSerializableExtra("bean");
            startActivity(new Intent(this, OnTripActivity.class)
                    .putExtra("bean", driverBean)
                    .putExtra("source", sourceBean)
                    .putExtra("destination", destinationBean)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();

        }

        if (requestCode == REQ_ESTIMATED_DESTINATION && resultCode == RESULT_OK) {

            destinationBean = (PlaceBean) data.getSerializableExtra("bean");
            llProgressBar.setVisibility(View.VISIBLE);
            llEstimation.setVisibility(View.GONE);

            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }


            if (sourceBean == null && mMap != null) {
                sourceBean = new PlaceBean();
                LatLng center = mMap.getCameraPosition().target;
                sourceBean.setLatitude(String.valueOf(center.latitude));
                sourceBean.setLongitude(String.valueOf(center.longitude));
            } else if (sourceBean == null) {
                Snackbar.make(coordinatorLayout, "WEB_ERROR_MSG", Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
                return;
            }
            fetchPolyPoints(false);

           // showFareEstimation(destinationBean.getName());
        }




        if (requestCode == REQ_ESTIMATED_DESTINATION && resultCode == RESULT_OK) {

            destinationTractorBean = (PlaceTractorBean) data.getSerializableExtra("bean");
            llProgressBar.setVisibility(View.VISIBLE);
            llEstimation.setVisibility(View.GONE);

            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }


            if (sourceTractorBean == null && mMap != null) {
                sourceTractorBean = new PlaceTractorBean();
                LatLng center = mMap.getCameraPosition().target;
                sourceTractorBean.setLatitude(String.valueOf(center.latitude));
                sourceTractorBean.setLongitude(String.valueOf(center.longitude));
            } else if (sourceBean == null) {
                Snackbar.make(coordinatorLayout, "WEB_ERROR_MSG", Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
                return;
            }
            fetchPolyPoints(false);

            // showFareEstimation(destinationBean.getName());
        }
    }














    private void onSourceSelect() {

        mMap.clear();
        txtSource.setText(sourceBean.getName());
        Toast.makeText(this, "Source selected", Toast.LENGTH_SHORT).show();
        onPlotLocation(true, LOCATION_SOURCE, sourceBean.getDLatitude(), sourceBean.getDLongitude());
        try {
            if (destinationBean.getDLatitude() != 0 && destinationBean.getDLongitude() != 0) {
                onPlotLocation(true, LOCATION_DESTINATION, destinationBean.getDLatitude(), destinationBean.getDLongitude());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (sourceBean.getDLatitude() != 0 && sourceBean.getDLongitude() != 0 && destinationBean.getDLatitude() != 0 && destinationBean.getDLongitude() != 0) {
                rlFare.setVisibility(View.VISIBLE);
                viewDottedLine.setVisibility(View.VISIBLE);
                mapAutoZoom();
                fetchPolyPoints(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onDestinationSelect() {
        mMap.clear();
        onPlotLocation(true, LOCATION_SOURCE, sourceBean.getDLatitude(), sourceBean.getDLongitude());
        onPlotLocation(true, LOCATION_DESTINATION, destinationBean.getDLatitude(), destinationBean.getDLongitude());
        txtDestination.setText(destinationBean.getName());

        if (sourceBean.getDLatitude() != 0 && sourceBean.getDLongitude() != 0
                && destinationBean.getDLatitude() != 0 && destinationBean.getDLongitude() != 0) {

            rlFare.setVisibility(View.VISIBLE);
            viewDottedLine.setVisibility(View.VISIBLE);
            mapAutoZoom();
            fetchPolyPoints(true);
        }

    }
    private void onDestinationTractorSelected(){
        mMap.clear();
        onPlotLocation(true,LOCATION_DESTINATION,destinationTractorBean.getDLatitude(),destinationTractorBean.getDLongitude());
        txt1destination.setText(destinationTractorBean.getName());
        if (destinationTractorBean.getDLatitude() != 0 && destinationTractorBean.getDLongitude() != 0) {



           // rlFare.setVisibility(View.VISIBLE);
            viewDottedLine.setVisibility(View.VISIBLE);
            mapAutoTractorZoom();
            //fetchPolyPoints(true);
        }
    }



    public void onLocationButtonClick(View view) {


        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);


        Log.i(TAG, "onLocationButtonClick: Clicked");

//        displayLocation();

        sourceBean = null;
        if (!checkForLocationPermissions()) {
            getLocationPermissions();
        } else {
            if (checkLocationSettingsStatus()) {
                getCurrentLocation();
            }
        }
    }

    public void onFareEstimateClick(View view) {

        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        if (mMap != null) {
            LatLng center = mMap.getCameraPosition().target;
            if (sourceBean == null)
                sourceBean = new PlaceBean();
            sourceBean.setLatitude(String.valueOf(center.latitude));
            sourceBean.setLongitude(String.valueOf(center.longitude));
        }



        Intent intent = new Intent(LandingPageActivity.this, SearchPageActivity.class);
        intent.putExtra("search_type", searchType);
        startActivityForResult(intent, REQ_ESTIMATED_DESTINATION);
    }

    public void onPickUpLocationClick(View view) {
        frame_pickup_landing_page.setVisibility(View.INVISIBLE);

        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

/*        CarBean bean1 = landingPageBean.getCars().get(0);
        CarBean bean2 = landingPageBean.getCars().get(1);
        CarBean bean3 = landingPageBean.getCars().get(2);
        CarBean bean4 = landingPageBean.getCars().get(3);*/

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setPeekHeight(0);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        }



        llFare.setVisibility(View.VISIBLE);
        rlFare.setVisibility(View.GONE);

        LatLng center = mMap.getCameraPosition().target;
//        center = mMap.getProjection().getVisibleRegion().latLngBounds.getCenter();

        if (sourceBean == null) {
            sourceBean = new PlaceBean();
        }

        sourceBean.setLatitude(String.valueOf(center.latitude));
        sourceBean.setLongitude(String.valueOf(center.longitude));

        onPlotLocation(true, LOCATION_SOURCE, sourceBean.getDLatitude(), sourceBean.getDLongitude());

        if (txtDestination.length() > 0) {
            rlFare.setVisibility(View.VISIBLE);
            viewDottedLine.setVisibility(View.VISIBLE);

        }


        if (!isConfirmationPage) {

            layoutConfirmationPage();

            txtActionSearch.setText(R.string.label_confirmation);

            if (!llConfirmation.isShown()) {

                cvConfirmationPage.setVisibility(View.VISIBLE);
                llConfirmation.setVisibility(View.VISIBLE);

            }

            if (!btnRequest.isShown()) {
                btnRequest.setVisibility(View.VISIBLE);
            }
            isConfirmationPage = true;

        }

        if (destinationBean != null) {
            mMap.clear();
            llFare.setVisibility(View.GONE);
            llConfirmationProgress.setVisibility(View.VISIBLE);
            txtDestination.setText(destinationBean.getName());

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onDestinationSelect();
                }
            }, 2000);
            llEstimation.setVisibility(View.GONE);
        }
       /* if (carType.equalsIgnoreCase("1")) {
            btnRequest.setText("Request " + bean1.getCarName());
        }

        if (carType.equalsIgnoreCase("2")) {
            btnRequest.setText("Request " + bean2.getCarName());
        }

        if (carType.equalsIgnoreCase("3")) {
            btnRequest.setText("Request " + bean3.getCarName());
        }

        if (carType.equalsIgnoreCase("4")) {
            btnRequest.setText("Request " + bean4.getCarName());
        }*/





    }

    public void layoutConfirmationPage() {

        mMap.getUiSettings().setAllGesturesEnabled(false);
        mMap.getUiSettings().setScrollGesturesEnabled(false);
        mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.getUiSettings().setTiltGesturesEnabled(false);
        mMap.getUiSettings().setRotateGesturesEnabled(false);

        if (!isConfirmationPage) {
            bottomSheetBehavior.setPeekHeight(0);

            ivBottomMarker.setVisibility(View.GONE);

            ivMarker.setVisibility(View.GONE);

            ivLocationButton.setVisibility(View.GONE);

            framePickup.setVisibility(View.GONE);

        }
    }

    public void onBackClick() {

        mMap.clear();

        fetchLandingPageDetails();

        try {
            ViewGroup.LayoutParams params = mapFragment.getView().getLayoutParams();
            params.height = height;
            mapFragment.getView().setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        llConfirmationProgress.setVisibility(View.GONE);

        getCurrentLocation();
//        txtSource.setText("");
//        txtDestination.setText("");

        txtFare.setVisibility(View.VISIBLE);

        rlFare.setVisibility(View.GONE);
        llFare.setVisibility(View.GONE);

        viewDottedLine.setVisibility(View.GONE);

        Log.i(TAG, "onBackClick: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);

        CameraPosition postion = mMap.getCameraPosition();
        LatLng center = postion.target;

        txtActionSearch.setText(Config.getInstance().getCurrentLocation());

        cvConfirmationPage.setVisibility(View.GONE);
        cv_Destination_Page.setVisibility(View.GONE);
//        rvCarList.setVisibility(View.VISIBLE);
        bottomSheetBehavior.setPeekHeight((int) (100 * px));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

//        llEstimation.setVisibility(View.VISIBLE);
        framePickup.setVisibility(View.VISIBLE);
        ivBottomMarker.setVisibility(View.VISIBLE);
        ivMarker.setVisibility(View.GONE);
        ivLocationButton.setVisibility(View.VISIBLE);
        btnRequest.setVisibility(View.GONE);
        TankerRequest.setVisibility(View.GONE);
        JCBRequest.setVisibility(View.GONE);

        llConfirmation.setVisibility(View.GONE);
        llDestination.setVisibility(View.GONE);

        mMap.clear();

        sourceBean = null;
        destinationBean = null;

//       txtTo.setVisibility(View.GONE);
//        llDestinationEstimated.setVisibility(View.GONE);
//        txtFareEstimate.setVisibility(View.VISIBLE);

        isConfirmationPage = false;
    }


    public void onLaGoCarClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        carType = landingPageBean.getCars().get(0).getCarID();


            fetchCarDetails();


        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        llProgressBar.setVisibility(View.VISIBLE);
        llEstimation.setVisibility(View.GONE);

        txtCarOne.setBackgroundResource(R.drawable.btn_click_green_dark_rectangle_with_semicircle_edge);
        txtCarTwo.setBackgroundResource(R.color.transparent);
        txtCarThree.setBackgroundResource(R.color.transparent);
        carFour.setBackgroundResource(R.color.transparent);
        txtCarOne.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        txtCarTwo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        txtCarThree.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        carFour.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));

        if (sourceBean != null & destinationBean != null) {
            fetchPolyPoints(false);
            if (fareBean != null) {
                txtFare.setText(fareBean.getTotalFare());
            }
            txtFareLabel.setText(R.string.label_estd_fare);
        }
    }

    public void onLaXCarClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        carType = landingPageBean.getCars().get(1).getCarID();


            fetchCarDetails();


        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        llProgressBar.setVisibility(View.VISIBLE);
        llEstimation.setVisibility(View.GONE);

        txtCarTwo.setBackgroundResource(R.drawable.btn_click_green_dark_rectangle_with_semicircle_edge);
        txtCarOne.setBackgroundResource(R.color.transparent);
        txtCarThree.setBackgroundResource(R.color.transparent);
        carFour.setBackgroundResource(R.color.transparent);
        txtCarTwo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        txtCarOne.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        txtCarThree.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        carFour.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));

        if (sourceBean != null & destinationBean != null) {
            fetchPolyPoints(false);
            if (fareBean != null) {
                txtFare.setText(fareBean.getTotalFare());
            }
            txtFareLabel.setText(R.string.label_estd_fare);
        }
    }

    public void onCarXlClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        carType = landingPageBean.getCars().get(2).getCarID();


            fetchCarDetails();


        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        llProgressBar.setVisibility(View.VISIBLE);
        llEstimation.setVisibility(View.GONE);

        txtCarThree.setBackgroundResource(R.drawable.btn_click_green_dark_rectangle_with_semicircle_edge);
        txtCarOne.setBackgroundResource(R.color.transparent);
        txtCarTwo.setBackgroundResource(R.color.transparent);
        carFour.setBackgroundResource(R.color.transparent);
        txtCarThree.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        txtCarTwo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        txtCarOne.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        carFour.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));

        if (sourceBean != null & destinationBean != null) {
            fetchPolyPoints(false);
            if (fareBean != null) {
                txtFare.setText(fareBean.getTotalFare());
            }
            txtFareLabel.setText(R.string.label_estd_fare);
        }
    }

    public void onCarXxlClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        carType = landingPageBean.getCars().get(3).getCarID();


            fetchCarDetails();


        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        llProgressBar.setVisibility(View.VISIBLE);
        llEstimation.setVisibility(View.GONE);

        carFour.setBackgroundResource(R.drawable.btn_click_green_dark_rectangle_with_semicircle_edge);
        txtCarOne.setBackgroundResource(R.color.transparent);
        txtCarTwo.setBackgroundResource(R.color.transparent);
        txtCarThree.setBackgroundResource(R.color.transparent);
        carFour.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        txtCarThree.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        txtCarTwo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        txtCarOne.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));

        if (sourceBean != null & destinationBean != null) {
            fetchPolyPoints(false);
            if (fareBean != null) {
                txtFare.setText(fareBean.getTotalFare());
            }
            txtFareLabel.setText(R.string.label_estd_fare);
        }
    }


    public void onCarTypeSelected(int position, CarBean bean) {

        carType = bean.getCarID();



    }

    public void onSourceClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);



        Intent intent = new Intent(LandingPageActivity.this, SearchPageActivity.class);
        intent.putExtra("search_type", searchType);
        startActivityForResult(intent, REQ_SEARCH_SOURCE_SELECT);

    }

    public void onDestinationClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);



        Intent intent = new Intent(LandingPageActivity.this, SearchPageActivity.class);
        intent.putExtra("search_type", searchType);
        startActivityForResult(intent, REQ_SEARCH_DESTINATION_SELECT);
    }

    public void OnDestinationTractor(View v){
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);



        Intent intent = new Intent(LandingPageActivity.this, SearchPageTractorLocation.class);
        intent.putExtra("search_type", searchType);
        startActivityForResult(intent, REQ_DESTINATION_ESTIMATE_SELECT);

    }


    private void displayLocation() {

        Log.i(TAG, "displayLocation: OnPlotLocation Called .........>>>>>>>>>>>>>>>>>>>>>>>>>>>>..");

        if (LastLocation != null && !isConfirmationPage) {

            onPlotLocation(false, LOCATION_SOURCE, LastLocation.getLatitude(), LastLocation.getLongitude());
            getLocationName(String.valueOf(LastLocation.getLatitude()), String.valueOf(LastLocation.getLongitude()));
       getLocationName(LastLocation.getLatitude(), LastLocation.getLongitude());
        }
    }

    private void getLocationName( double currentLatitude, double currentLongitude) {

        LocationTask locationTask = new LocationTask(currentLatitude, currentLongitude) {
            @Override
            protected PlaceBean doInBackground(String... strings) {
                return null;
            }
        };
        locationTask.setLocationTaskListener(new LocationTask.LocationTaskListener() {
            @Override
            public void dataDownloadedSuccessfully(PlaceBean placeBean) {

                sourceBean = placeBean;

                if (placeBean != null) {
                    txtActionSearch.setText(placeBean.getName());
                    txtSource.setText(placeBean.getName());
                }
            }

            @Override
            public void dataDownloadFailed() {

            }
        });
        locationTask.execute();


    }

    protected void getLocationName(final String latitude, final String longitude) {

//        swipeView.setRefreshing(true);

        /*String currentLatitude = Config.getInstance().getCurrentLatitude();
        String currentLongitude = Config.getInstance().getCurrentLongitude();

        System.out.println("Current Location : " + currentLatitude + "," + currentLongitude);*/

        HashMap<String, String> urlParams = new HashMap<>();
        //	postData.put("uid", id);
        urlParams.put("latlng", latitude + "," + longitude);
        urlParams.put("sensor", "true");
        urlParams.put("key", getString(R.string.browser_api_key));

        LocationNameTask locationNameTask = new LocationNameTask(urlParams);
        locationNameTask.setLocationNameTaskListener(new LocationNameTask.LocationNameTaskListener() {

            @Override
            public void dataDownloadedSuccessfully(String address) {
                //	System.out.println(landingBean.getStatus());
                if (null != address) {
                    System.out.println("Location Name Retrieved : " + address);
                    Config.getInstance().setCurrentLocation(address);

                    txtActionSearch.setText(address);
                    txtSource.setText(address);
                    if (sourceBean == null)
                        sourceBean = new PlaceBean();
                    sourceBean.setAddress(address);
                    sourceBean.setName(address);
                    sourceBean.setLatitude(latitude);
                    sourceBean.setLongitude(longitude);
                    /*					txtLocation.setText(address);
                    Toast.makeText(CreateActivity.this,"Location Name Retrieved : "+address, Toast.LENGTH_SHORT).show();
					 */
                }
            }

            @Override
            public void dataDownloadFailed() {

            }
        });
        locationNameTask.execute();
    }

    private void fetchCarDetails() {


        RequestQueue queue = Volley.newRequestQueue(LandingPageActivity.this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, Services,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        try {
                            JSONArray array = new JSONArray(ServerResponse);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject product ,product1 = array.getJSONObject(i);

                                int id=product1.getInt("id");
                                String name=product1.getString("name");
                                String provider_name=product1.getString("provider_name");
                                String image=product1.getString("image");
                                int capacity=product1.getInt("capacity");
                                int fixed=product1.getInt("fixed");
                                int price=product1.getInt("price");
                                int minute=product1.getInt("minute");
                                int distance=product1.getInt("distance");
                                String calculator=product1.getString("calculator");
                                //String description=product.getString("desciption");
                                int status=product1.getInt("status");

                                JSONArray jArray1 = product1.getJSONArray("addons");


                                for (int j = 0; j < jArray1.length(); j++) {
                                    JSONObject json_data1 = jArray1.getJSONObject(j);


                                    int id1=json_data1.getInt("id");

                                    String NameAdd=json_data1.getString("name");
                                    String imageAdd=json_data1.getString("image");
                                    String priceAdd=json_data1.getString("price");
                                    String serviceType=json_data1.getString("service_type_id");
                                    String type=json_data1.getString("type");

                                    SubcategoryProduct subcategoryProduct= new SubcategoryProduct(id1,serviceType,NameAdd,imageAdd,priceAdd,type);
                                    subcategory.add(subcategoryProduct);
                                    //Toast.makeText(LandingPageActivity.this, (CharSequence) subcategory, Toast.LENGTH_SHORT).show();
                                }
                                //Toast.makeText(LandingPageActivity.this, Addons, Toast.LENGTH_SHORT).show();





                                   // Toast.makeText(LandingPageActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                VehiclFatchProduct vehiclFatchProduct= new VehiclFatchProduct(id,name,provider_name,image,capacity,fixed,price,minute,distance,calculator,status);

                                VehicleList.add(vehiclFatchProduct);
                               // Toast.makeText(LandingPageActivity.this, ServerResponse, Toast.LENGTH_SHORT).show();

                            }

                           adapter=new VehicleListAdapter(LandingPageActivity.this,VehicleList);
                            rvCarTypes .setAdapter(adapter);
                                adapter.setonItemClickListener(LandingPageActivity.this);

                            SubAdapter=new SubadapterListAdapter(LandingPageActivity.this,subcategory);



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
                        Toast.makeText(LandingPageActivity.this, "Retry", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }


                }) {


        };

        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        queue.add(stringRequest);


    }
    @Override
    public void onItemClicke(int position) {
        //AddOnTractorType.class
     /*  Intent detailIntent=new Intent(this,LandingPageActivity.class);
       SubcategoryProduct clickedItem= subcategory.get(position);

       detailIntent.putExtra(EXTRA_NAME,clickedItem.getSubCategoryName());
       detailIntent.putExtra(EXTRA_PROVIDER,clickedItem.getSubcategoryPrice());

       startActivity(detailIntent);
*/
        if (position == 0){
            frame_pickup_landing_page.setVisibility(View.INVISIBLE);

           // view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);


            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setPeekHeight(0);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            }

            // onPlotLocation(true, LOCATION_SOURCE, sourceBean.getDLatitude(), sourceBean.getDLongitude());

            llFare.setVisibility(View.VISIBLE);





            if (!isConfirmationPage) {

                layoutConfirmationPage();

                txtActionSearch.setText(R.string.label_confirmation);

                if (!llDestination.isShown()) {

                    cv_Destination_Page.setVisibility(View.VISIBLE);
                    llDestination.setVisibility(View.VISIBLE);

                }

                if (!btnRequest.isShown()) {
                    btnRequest.setVisibility(View.VISIBLE);
                }

                isConfirmationPage = true;

            }
            if (destinationBean != null) {
                mMap.clear();
               // llFare.setVisibility(View.GONE);
                //
                //llConfirmationProgress.setVisibility(View.VISIBLE);
                txt1destination.setText(destinationBean.getName());

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onDestinationTractorSelected();
                    }
                }, 2000);
                llEstimation.setVisibility(View.GONE);
            }



           // Intent i=new Intent(LandingPageActivity.this,tractorcategory.class);
            //startActivity(i);
        }

        if (position==1) {
            frame_pickup_landing_page.setVisibility(View.INVISIBLE);

            // view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);


            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setPeekHeight(0);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            }

            // onPlotLocation(true, LOCATION_SOURCE, sourceBean.getDLatitude(), sourceBean.getDLongitude());


            if (txtDestination.length() > 0) {
                rlFare.setVisibility(View.VISIBLE);
                viewDottedLine.setVisibility(View.VISIBLE);

            }

            JCB_fare.setVisibility(View.VISIBLE);


            if (!isConfirmationPage) {

                layoutConfirmationPage();

                txtActionSearch.setText(R.string.label_confirmation);

                if (!llDestination.isShown()) {

                    cv_Destination_Page.setVisibility(View.VISIBLE);
                    llDestination.setVisibility(View.VISIBLE);

                }

                if (!JCBRequest.isShown()) {
                    JCBRequest.setVisibility(View.VISIBLE);
                }

                isConfirmationPage = true;

            }
            if (destinationBean != null) {
                mMap.clear();
                // llFare.setVisibility(View.GONE);
                //
                //llConfirmationProgress.setVisibility(View.VISIBLE);
                txt1destination.setText(destinationBean.getName());

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onDestinationTractorSelected();
                    }
                }, 2000);
                llEstimation.setVisibility(View.GONE);
            }



        }


        if (position==2){


          //frame_pickup_landing_page.setVisibility(View.INVISIBLE);
           Intent i=new Intent(LandingPageActivity.this,SearchPageActivity.class);
          startActivity(i);
        }
        if (position==3){
            frame_pickup_landing_page.setVisibility(View.INVISIBLE);

            // view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);


            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setPeekHeight(0);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            }

            // onPlotLocation(true, LOCATION_SOURCE, sourceBean.getDLatitude(), sourceBean.getDLongitude());
            Tanker_fare.setVisibility(View.VISIBLE);



            if (!isConfirmationPage) {

                layoutConfirmationPage();

                txtActionSearch.setText(R.string.label_confirmation);

                if (!llDestination.isShown()) {

                    cv_Destination_Page.setVisibility(View.VISIBLE);
                    llDestination.setVisibility(View.VISIBLE);

                }

                if (!TankerRequest.isShown()) {
                    TankerRequest.setVisibility(View.VISIBLE);
                }

                isConfirmationPage = true;

            }
            if (destinationBean != null) {
                mMap.clear();
                // llFare.setVisibility(View.GONE);
                //
                //llConfirmationProgress.setVisibility(View.VISIBLE);
                txt1destination.setText(destinationBean.getName());

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onDestinationTractorSelected();
                    }
                }, 2000);
                llEstimation.setVisibility(View.GONE);
            }


             /*Addons add code*/
            SubAdapter=new SubadapterListAdapter(LandingPageActivity.this,subcategory);
            rvRecyclerview2.setAdapter(SubAdapter);
        }


    }








//        swipeView.setRefreshing(true)
//        center = mMap.getProjection().getVisibleRegion().latLngBounds.getCenter();




    private void populateCarDetails(CarBean carBean) {

        if (carBean.getCarsAvailable().equalsIgnoreCase(getString(R.string.label_no_cars_available))) {
            txtCarAvailability.setText(R.string.label_no_cars_available);
            txtCarArrivalEstimatedTime.setVisibility(View.GONE);
        } else {
            txtCarArrivalEstimatedTime.setVisibility(View.VISIBLE);
            txtCarAvailability.setText(R.string.btn_set_pickup_location);
        }

        txtCarArrivalEstimatedTime.setText(carBean.getMinTime());
        txtTime.setText(carBean.getMinTime());
        txtMaxSize.setText(carBean.getMaxSize());
        if (destinationBean == null) {
            txtFare.setText(carBean.getMinFare());
        }

        if (destinationBean == null) {
            llEstimation.setVisibility(View.VISIBLE);
            llProgressBar.setVisibility(View.GONE);
        }
    }

    public void fetchTotalfare() {

        RequestQueue queue = Volley.newRequestQueue(LandingPageActivity.this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, fetchtotalfareforcar,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        if (isConfirmationPage) {
                            llFare.setVisibility(View.VISIBLE);
                            llConfirmationProgress.setVisibility(View.GONE);

                        }
                        swipeView.setRefreshing(false);

                       // populateFareDetails();
                        txtFare.setVisibility(View.VISIBLE);

                       // llProgressBar.setVisibility(View.GONE);
                        llEstimation.setVisibility(View.VISIBLE);


                        try {

                            JSONObject object = new JSONObject(ServerResponse);

                            String Estimate=object.getString("estimated_fare");
                            String Time=object.getString("time");
                           // Log.e(TAG, "onResponse: ",Time );

                            Log.e( TAG,"onResponse: "+ Estimate );
                            Log.e( TAG,"onsecondResponse: "+Time );
                            txtTotalFare.setText(Estimate, TextView.BufferType.EDITABLE);
                           // Toast.makeText(LandingPageActivity.this, ServerResponse, Toast.LENGTH_SHORT).show();





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        //Toast.makeText(LandingPageActivity.this, "Fatch Total Fare ", Toast.LENGTH_SHORT).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.


                        // Showing error message if something goes wrong.
                        Toast.makeText(LandingPageActivity.this, "Not Fatch any fare", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }


                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.


                params.put("s_latitude",sourceBean.getLatitude());
                params.put("s_longitude",sourceBean.getLongitude());
                params.put("d_latitude",destinationBean.getLatitude());
                params.put("d_longitude",destinationBean.getLongitude());
                params.put("service_type","3");
                return params;
            }

        };

        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        queue.add(stringRequest);





    }

    private void populateFareDetails() {


          //  txtTotalFare.setText(E, TextView.BufferType.EDITABLE);
            txtFare.setText(fareBean.getTotalFare());
        }


    public void getEstimatedFare() {

        String source = txtActionSearch.getText().toString();
        String destination = txtEstimatedDestination.getText().toString();

        HashMap<String, String> urlParams = new HashMap<>();
        urlParams.put("source", source);
        urlParams.put("destination", destination);

        urlParams.put("car_type", String.valueOf(carType));

        urlParams.put("source_latitude", sourceBean.getLatitude());
        urlParams.put("source_longitude", sourceBean.getLongitude());
        urlParams.put("destination_latitude", destinationBean.getLatitude());
        urlParams.put("destination_longitude", destinationBean.getLongitude());

        urlParams.put("distance", String.valueOf(distance));
        urlParams.put("time", String.valueOf(time));

        Log.i(TAG, "getEstimatedFare: Time " + time);


    }

    public void populateEstimatedFare(FareBean fareBean) {

        txtFare.setText(fareBean.getTotalFare());
    }

    public void onEstimatedDestinationClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        onFareEstimateClick(view);
    }

    public void onRequestRideClick(View view) {
        Intent j=new Intent(LandingPageActivity.this,tractorcategory.class);
        startActivity(j);

        }

        public void onTankerAddonseAdd(View v){
        Intent k=new Intent(LandingPageActivity.this,tankerCategory.class);
        startActivity(k);
        }
        public void onJCBAddonseAdd(View v){
        Intent l=new Intent(LandingPageActivity.this,JcbCategory.class);
        startActivity(l);
        }





    public void fetchLandingPageDetails() {

        Log.i(TAG, "fetchLandingPageDetails: AuthToken" + Config.getInstance().getAuthToken());

        HashMap<String, String> urlParams = new HashMap<>();

        if (mMap != null) {
            LatLng center = mMap.getCameraPosition().target;
            urlParams.put("latitude", String.valueOf(center.latitude));
            urlParams.put("longitude", String.valueOf(center.longitude));
        } else {
            urlParams.put("latitude", Config.getInstance().getCurrentLatitude());
            urlParams.put("longitude", Config.getInstance().getCurrentLongitude());
        }
        DataManager.fetchLandingPageDetails(urlParams, new LandingPageListener() {

            @Override
            public void onLoadCompleted(LandingPageBean landingPageBeanWS) {
                swipeView.setRefreshing(false);
                setProgressScreenVisibility(false, false);
                landingPageBean = landingPageBeanWS;
                populateLandingPageDetails(landingPageBeanWS);

            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);
                setProgressScreenVisibility(true, false);
                Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.btn_retry, snackBarRefreshOnClickListener).show();
            }
        });

    }

    private void populateLandingPageDetails(LandingPageBean landingPageBean) {


        Collections.sort(landingPageBean.getCars());

        /*CarBean bean1 = landingPageBean.getCars().get(0);
        CarBean bean2 = landingPageBean.getCars().get(1);
        CarBean bean3 = landingPageBean.getCars().get(2);
        CarBean bean4 = landingPageBean.getCars().get(3);

        txtCarOne.setText(bean1.getCarName());
        txtCarTwo.setText(bean2.getCarName());
        txtCarThree.setText(bean3.getCarName());
        carFour.setText(bean4.getCarName());

        Glide.with(getApplicationContext())
                .load(bean1.getCarImage())
                .into(carOneImage);

        Glide.with(getApplicationContext())
                .load(bean2.getCarImage())
                .into(carTwoImage);

        Glide.with(getApplicationContext())
                .load(bean3.getCarImage())
                .into(carThreeImage);

        Glide.with(getApplicationContext())
                .load(bean4.getCarImage())
                .into(carFourImage);*/


        if (adapterCarTypes == null) {

            adapterCarTypes = new CarTypeRecyclerAdapter(this, landingPageBean);
            adapterCarTypes.setCarTypeRecyclerAdapterListener(new CarTypeRecyclerAdapter.CarTypeRecyclerAdapterListener() {
                @Override
                public void onRefresh() {

                }

                @Override
                public void onSelectedCar(int position, CarBean carBean) {
                    carType = carBean.getCarID();
                    onCarTypeSelected(position, carBean);
                }
            });
            rvCarTypes.setAdapter(adapterCarTypes);
        } else {
            if (landingPageBean.getCars() != null && !landingPageBean.getCars().isEmpty()) {
                adapterCarTypes.setLandingPageBean(landingPageBean);
                adapterCarTypes.notifyDataSetChanged();
            } else {
                txtCarAvailability.setText(R.string.label_no_cars_available);
                txtCarArrivalEstimatedTime.setVisibility(View.GONE);
            }
        }

        if (carType.equalsIgnoreCase("") || landingPageBean.getCar(carType) == null) {
            carType = landingPageBean.getCars() != null && !landingPageBean.getCars().isEmpty()
                    ? landingPageBean.getCars().get(0).getCarID() : "-1";
        }

        fetchCarDetails();
    }

    public void onPlotLocation(boolean isMarkerNeeded, int type, double latitude, double longitude) {

        LatLng newLatLng = null;
        try {
            newLatLng = new LatLng(latitude, longitude);
            if (isMarkerNeeded) {
                switch (type) {
                    case LOCATION_SOURCE:
                        mMap.addMarker(new MarkerOptions().position(newLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_source_marker)));
                        break;
                    case LOCATION_DESTINATION:
                        mMap.addMarker(new MarkerOptions().position(newLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_destination_marker)));
                        break;
                    default:
                        mMap.addMarker(new MarkerOptions().position(newLatLng).icon(BitmapDescriptorFactory.defaultMarker()));
                        break;
                }
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLng, 18));
            Log.i(TAG, "onPlotLocation: Position" + newLatLng);

        } catch (NumberFormatException e) {
            e.printStackTrace();

        }
    }


    public void fetchPolyPoints(final boolean isPolyLineNeeded) {
        HashMap<String, String> urlParams = new HashMap<>();

//        if (sourceBean != null && destinationBean != null) {
        urlParams.put("origin", sourceBean.getLatitude() + "," + sourceBean.getLongitude());
        urlParams.put("destination", destinationBean.getLatitude() + "," + destinationBean.getLongitude());
        urlParams.put("mode", "driving");
        urlParams.put("key", getString(R.string.browser_api_key));
//        }

        DataManager.fetchPolyPoints(urlParams, new PolyPointsListener() {

            @Override
            public void onLoadCompleted(PolyPointsBean polyPointsBeanWS) {
                swipeView.setRefreshing(false);

                polyPointsBean = polyPointsBeanWS;
                time = String.valueOf(polyPointsBean.getTime());
                distance = String.valueOf(polyPointsBean.getDistance());

                Log.i(TAG, "onLoadCompleted: Time Taken" + polyPointsBean.getTimeText());
                Log.i(TAG, "onLoadCompleted: Distance" + polyPointsBean.getDistanceText());

                fetchTotalfare();

                if (isPolyLineNeeded) {
                    if (!isDestinationEstimateSelect)
                        populatePath();
                    isDestinationEstimateSelect = false;
                }
            }

            @Override
            public void onLoadFailed(String error) {
                swipeView.setRefreshing(false);
                Log.i(TAG, "onLoadFailed: POLYPOINTS : ");
                Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_LONG)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
            }
        });
    }

    private void populatePath() {

        List<List<HashMap<String, String>>> routes = polyPointsBean.getRoutes();

        ArrayList<LatLng> points = null;
        PolylineOptions polyLineOptions = null;

        // traversing through routes
        for (int i = 0; i < routes.size(); i++) {
            points = new ArrayList<LatLng>();
            polyLineOptions = new PolylineOptions();
            List path = routes.get(i);

            for (int j = 0; j < path.size(); j++) {
                HashMap point = (HashMap) path.get(j);

                double lat = Double.parseDouble((String) point.get("lat"));
                double lng = Double.parseDouble((String) point.get("lng"));
                LatLng position = new LatLng(lat, lng);

                points.add(position);
            }

            polyLineOptions.addAll(points);
            polyLineOptions.width(8);
            polyLineOptions.color(ContextCompat.getColor(getApplicationContext(), R.color.map_path));

        }

        polyLine = mMap.addPolyline(polyLineOptions);
    }

    public void mapAutoZoom() {


        if (sourceBean != null && destinationBean != null) {
            newLatLng1 = new LatLng(sourceBean.getDLatitude(), sourceBean.getDLongitude());
            newLatLng2 = new LatLng(destinationBean.getDLatitude(), destinationBean.getDLongitude());

        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        builder.include(newLatLng1);
        builder.include(newLatLng2);
        bounds = builder.build();

//        mMap.setPadding(0, (int) (height - getStatusBarHeight() - mActionBarHeight - (px * 160)), 0, (int) (height - getStatusBarHeight() - mActionBarHeight - (px * 120)));

//        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, (int) (40 * px)));
        if (mMap != null && mapFragment.getView() != null && mapFragment.getView().getHeight() > 0) {
            if (mapFragment.getView().getHeight() > 150 * px)
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, (int) (40 * px)));
            else
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, (int) (5 * px)));
        }

    }

    public void mapAutoTractorZoom(){




        if (sourceTractorBean!=null&&destinationTractorBean!=null) {
            newLatLng1=new LatLng(destinationTractorBean.getDLatitude(),destinationTractorBean.getDLongitude());
            newLatLng2=new LatLng(sourceTractorBean.getDLatitude(),sourceTractorBean.getDLongitude());
        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        builder.include(newLatLng1);
        builder.include(newLatLng2);
        bounds = builder.build();

//        mMap.setPadding(0, (int) (height - getStatusBarHeight() - mActionBarHeight - (px * 160)), 0, (int) (height - getStatusBarHeight() - mActionBarHeight - (px * 120)));

//        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, (int) (40 * px)));
        if (mMap != null && mapFragment.getView() != null && mapFragment.getView().getHeight() > 0) {
            if (mapFragment.getView().getHeight() > 150 * px)
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, (int) (40 * px)));
            else
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, (int) (5 * px)));
        }

    }

    public void onLayoutClickLandingPage(View view) {

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }



    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        if (!checkForLocationPermissions()) {
            getLocationPermissions();
        } else {
            if (checkLocationSettingsStatus()) {
                getCurrentLocation();
            }
        }
        return false;
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }


    public void fatchCarMap() {




        RequestQueue queue = Volley.newRequestQueue(LandingPageActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, providerFatchonMap,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        try {
                            JSONArray array = new JSONArray(ServerResponse);
                            Drawable circleDrawable = getResources().getDrawable(R.mipmap.tractorimage);
                            BitmapDescriptor markerIcon = getMarkerIconFromDrawable(circleDrawable);
                            // BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_train_black_24dp);
                            List<Marker> markers = new ArrayList<Marker>();
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject product = array.getJSONObject(i);
                                String Fid=product.getString("id");
                                Double latitude=product.getDouble("latitude");
                                Double longitude=product.getDouble("longitude");
                               // Toast.makeText(LandingPageActivity.this, "latitude", Toast.LENGTH_SHORT).show();

                                ProviderFatchOnFatch  providerFatchOnFatch= new ProviderFatchOnFatch(Fid,latitude,longitude);

                                providerfatch.add(providerFatchOnFatch);


                                LatLng latlng = new LatLng(latitude, longitude);
                                if (latlng == latlng) {



                                    Marker marker = mMap.addMarker(new MarkerOptions().title(Fid)

                                                    .position(new LatLng(latitude, longitude))
                                                    .icon(markerIcon)
                                            //.icon(BitmapDescriptorFactory.defaultMarker())
                                    );

                                    markers.add(marker);
                                    // Moving CameraPosition to last clicked position

                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));

                                    // Setting the zoom level in the map on last position is clicked
                                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));

                                    mMap.animateCamera(CameraUpdateFactory.zoomTo(2));
                                }
                                else {

                                    Toast.makeText(LandingPageActivity.this, "Update Your Location", Toast.LENGTH_SHORT).show();
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Toast.makeText(LandingPageActivity.this, ServerResponse, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(LandingPageActivity.this, ServerResponse, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.


                        // Showing error message if something goes wrong.
                        Toast.makeText(LandingPageActivity.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }



                }) {



        };

        queue.add(stringRequest);




    }
    private BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


             @Override
             public void onPolygonClick(Polygon polygon) {

             }

             @Override
             public void onPolylineClick(Polyline polyline) {

             }

             @Override
             public void onMapReady(GoogleMap googleMap) {

             }
         }

