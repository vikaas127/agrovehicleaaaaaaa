package com.jaats.agrovehicle.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jaats.agrovehicle.R;
import com.jaats.agrovehicle.app.App;
import com.jaats.agrovehicle.net.ServiceNames;

import java.util.HashMap;
import java.util.Map;

import static com.jaats.agrovehicle.activity.SharedHelper.sharedPreferences;

public class LoginActivity extends BaseAppCompatNoDrawerActivity {
    public Context context = LoginActivity.this;
    private static final String SHARED_PREF_NAME = "mysharedpref";
    String device_token, device_UDID;
    String Url = "http://nkploggy.com/api/user/login";

    RequestQueue requestQueue;
    private EditText mobile;
    private EditText Password;
    private View.OnClickListener snackBarRefreshOnClickListener;
    private String TAG = " ";
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        requestQueue = Volley.newRequestQueue(LoginActivity.this);

        prefs = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);

        if (SharedPrefManager.getInstans(this).isLogin()) {
            finish();
            startActivity(new Intent(this, LandingPageActivity.class));
            return;
        }


        swipeView.setPadding(0, 0, 0, 0);
//        lytBase.setFitsSystemWindows(false);

        initViews();

    }





    public void initViews() {

        mobile = (EditText) findViewById(R.id.etxt_login_email);
        Password = (EditText) findViewById(R.id.etxt_login_password);

        mobile.setTypeface(typeface);
        Password.setTypeface(typeface);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
            onBackPressed();
        }
        return true;
    }

    public void onLoginButtonClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);


        performLogin();
    }


    public void performLogin() {
        swipeView.setRefreshing(true);
        final String Mobileholder = mobile.getText().toString().trim();
        final String passwordHolder = Password.getText().toString().trim();


        if (TextUtils.isEmpty(Mobileholder)) {
            mobile.setError("Please enter Mobile Number");
            mobile.requestFocus();

            return;
        }
        if (TextUtils.isEmpty(passwordHolder)) {
            Password.setError("Please enter Password");
            Password.requestFocus();

            return;
        }
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {


                        try {
                            JSONObject jsonObject2, jsonObject3, jsonObject = new JSONObject(ServerResponse);

                            if (jsonObject.getString("status").equals("1")) {


                                SharedPrefManager.getInstans(getApplicationContext()).userLogin(
                                        jsonObject.getString("id"),
                                        jsonObject.getString("first_name"),
                                        jsonObject.getString("last_name"));
                                       // jsonObject.getString("mobile"));

                            } else if (jsonObject.getString("status").equals("0")) {
                               
                            }
                           /* if (!object.getBoolean("sucsses")) {
                                SharedPrefManager.getInstans(getApplicationContext()).userLogin(
                                        object.getString("token"),
                                        object.getString("status"),
                                        object.getString("id"),
                                        object.getString("first_name"),
                                        object.getString("last_name"));

                            }else {
                                Toast.makeText(getApplicationContext(),"User Login UnSucssesFull", Toast.LENGTH_LONG).show();
                            }
*/
                                startActivity(new Intent(LoginActivity.this,LandingPageActivity.class));
                                finish();
                               
                            }
                            //Toast.makeText(LoginActivity.this, "abc", Toast.LENGTH_SHORT).show();


                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.


                        // Showing error message if something goes wrong.
                        Toast.makeText(LoginActivity.this, "Retry", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }


                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.


                params.put("mobile",Mobileholder);
                params.put("password",passwordHolder);
                return params;
            }

        };

        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        queue.add(stringRequest);


    }

    
    
    public void onForgotPasswordClick(View v){
        Intent t=new Intent(LoginActivity.this,ForgotPasswordActivity.class);
        startActivities(new Intent[]{t});
    }



}



















