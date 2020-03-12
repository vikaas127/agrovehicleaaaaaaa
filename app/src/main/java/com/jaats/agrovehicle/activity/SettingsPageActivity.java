package com.jaats.agrovehicle.activity;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import com.jaats.agrovehicle.R;
import com.jaats.agrovehicle.app.App;
import com.jaats.agrovehicle.config.Config;
import com.jaats.agrovehicle.listeners.LocationSaveListener;
import com.jaats.agrovehicle.listeners.UserInfoListener;
import com.jaats.agrovehicle.model.LocationBean;
import com.jaats.agrovehicle.model.PlaceBean;
import com.jaats.agrovehicle.model.UserBean;

//import com.digits.sdk.android.Digits;

public class SettingsPageActivity extends BaseAppCompatNoDrawerActivity {

    private static final int REQ_HOME_PLACE = 0;
    private static final int REQ_WORK_PLACE = 1;
    private static final int HOME_LOCATION = 0;
    private static final int WORK_LOCATION = 1;
    private static final String TAG = "SPAc";
    private Toolbar toolbarSettings;
    private TextView txtName;
    private TextView txtMobile;
    private TextView txtEmail;
    private UserBean userBean;
    private ImageView ivProfilePhoto;
    private TextView txtAddHome;
    private TextView txtAddWork;
    private int addHome;
    private int addWork;
    private View.OnClickListener snackBarRefreshOnClickListener;
    private PlaceBean homeLocationBean;
    private PlaceBean workLocationBean;
    private LinearLayout llOnAddHome,llOnAddWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        initViews();

        getData();

//        swipeView.setRefreshing(true);
       // setProgressScreenVisibility(true, true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    private void getData() {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        fetchUserDetails();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQ_HOME_PLACE && resultCode == RESULT_OK) {

            homeLocationBean = (PlaceBean) data.getSerializableExtra("location");
            Log.i(TAG, "onActivityResult: HOME LACE BEAN : " + new Gson().toJson(homeLocationBean));

            txtAddHome.setText(homeLocationBean.getName());

            Toast.makeText(getApplicationContext(), R.string.message_home_location_added,
                    Toast.LENGTH_LONG).show();

            performLocationSave(HOME_LOCATION);

        }

        if (requestCode == REQ_WORK_PLACE && resultCode == RESULT_OK) {

            workLocationBean = (PlaceBean) data.getSerializableExtra("location");
            Log.i(TAG, "onActivityResult: WORK LACE BEAN : " + new Gson().toJson(workLocationBean));

            txtAddWork.setText(workLocationBean.getName());

            Toast.makeText(getApplicationContext(), R.string.message_work_location_added,
                    Toast.LENGTH_LONG).show();

            performLocationSave(WORK_LOCATION);

        }
    }

    public void initViews() {

        snackBarRefreshOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                //  mVibrator.vibrate(25);
                getData();
            }
        };

        coordinatorLayout.removeView(toolbar);

        toolbarSettings = (Toolbar) getLayoutInflater().inflate(R.layout.toolbar_settings_page, toolbar);
        coordinatorLayout.addView(toolbarSettings, 0);
        setSupportActionBar(toolbarSettings);

        txtAddHome = (TextView) findViewById(R.id.txt_add_home);
        txtAddWork = (TextView) findViewById(R.id.txt_add_work);

        txtName = (TextView) findViewById(R.id.txt_name);
        txtMobile = (TextView) findViewById(R.id.txt_mobile);
//        txtMobileCode = (TextView) findViewById(R.id.txt_mobile_code);
        txtEmail = (TextView) findViewById(R.id.txt_email);

        ivProfilePhoto = (ImageView) findViewById(R.id.iv_profile_photo);

//        ivProfileBackground = (ImageView) findViewById(R.id.iv_profile_background);


        llOnAddHome=findViewById(R.id.ll_on_add_home);
        llOnAddHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);



                Intent intent = new Intent(SettingsPageActivity.this, SearchHomeWorkActivity.class);
                intent.putExtra("search_type", addHome);
                startActivityForResult(intent, REQ_HOME_PLACE);

            }
        });

        llOnAddWork=findViewById(R.id.ll_on_add_work);
        llOnAddWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);



                Intent intent = new Intent(SettingsPageActivity.this, SearchHomeWorkActivity.class);
                intent.putExtra("search_type", addWork);
                startActivityForResult(intent, REQ_WORK_PLACE);
            }
        });
    }

    public void fetchUserDetails() {

        HashMap<String, String> urlParams = new HashMap<>();

     populateUserInfo();


    }

    private void populateUserInfo() {



        ivProfilePhoto.setVisibility(View.VISIBLE);

        txtName.setText(SharedPrefManager.getInstans(getApplicationContext()).getUsername());
        txtEmail.setText(SharedPrefManager.getInstans(getApplicationContext()).getLastName());
        txtMobile.setText(SharedPrefManager.getInstans(getApplicationContext()).getmobile());
//        txtMobileCode.setText(userBean.getMobileCode());

        Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show();
    }

    public void onEditAccountClick(MenuItem item) {

        Intent intent = new Intent(SettingsPageActivity.this, ProfileEditActivity.class);
        intent.putExtra("bean", userBean);
        startActivity(intent);

    }

  /*  public void onAddHomeClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        addHome = AppConstants.ADD_HOME;

        Intent intent = new Intent(SettingsPageActivity.this, SearchHomeWorkActivity.class);
        intent.putExtra("search_type", addHome);
        startActivityForResult(intent, REQ_HOME_PLACE);
    }

    public void onAddWorkClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        addWork = AppConstants.ADD_WORK;

        Intent intent = new Intent(SettingsPageActivity.this, SearchHomeWorkActivity.class);
        intent.putExtra("search_type", addWork);
        startActivityForResult(intent, REQ_WORK_PLACE);
    }*/

    public void performLocationSave(int type) {

        JSONObject postData = getLocationSaveJSObj(type);


    }

    private JSONObject getLocationSaveJSObj(int type) {
        JSONObject postData = new JSONObject();

        try {

            if (type == HOME_LOCATION) {
                postData.put("type", HOME_LOCATION);
                postData.put("home", homeLocationBean.getName());
                postData.put("home_latitude", homeLocationBean.getLatitude());
                postData.put("home_longitude", homeLocationBean.getLongitude());
            } else {
                postData.put("type", WORK_LOCATION);
                postData.put("work", workLocationBean.getName());
                postData.put("work_latitude", workLocationBean.getLatitude());
                postData.put("work_longitude", workLocationBean.getLongitude());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }

    public void onSignOutClick(MenuItem item) {

       // App.logout();
        SharedPrefManager.getInstans(getApplicationContext()).logout();
        finish();
        startActivity(new Intent(SettingsPageActivity.this,WelcomeActivity.class));
    }
}

