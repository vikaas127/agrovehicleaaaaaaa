package com.jaats.agrovehicle.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.FileProvider;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jaats.agrovehicle.R;
import com.jaats.agrovehicle.app.App;
import com.jaats.agrovehicle.config.Config;
import com.jaats.agrovehicle.dialogs.SelectPhotoDialog;
import com.jaats.agrovehicle.listeners.BasicListener;
import com.jaats.agrovehicle.listeners.EditProfileListener;
import com.jaats.agrovehicle.listeners.UserInfoListener;
import com.jaats.agrovehicle.model.BasicBean;
import com.jaats.agrovehicle.model.UserBean;
import com.jaats.agrovehicle.util.AppConstants;
import com.jaats.agrovehicle.util.ImageFilePath;


public class ProfileEditActivity extends BaseAppCompatNoDrawerActivity {
    String userProfile = "http://nkploggy.com/api/user/mobiledetails";
  String UpdateUserProfile= "http://nkploggy.com/api/user/mobile_update_profile";
    private static final String TAG = "ProfileEditA";
    private static final int REQUEST_IMAGE_CAMERA = 1;
    private static final int REQUEST_IMAGE_GALLERY = 2;
    private static final int REQ_MOBILE_VERIFICATION = 3;
    private Toolbar toolbarEdit;
    private EditText etxtName;
    private EditText etxtLname;
    private EditText etxtPhone;
    private UserBean userBean;
    private Spinner spinner;
    private ImageView ivProfilePhoto;
    private String imagePath = "";
    private String displayPicImage = "";
    private String email;
    private ArrayAdapter<CharSequence> adapter;
    private String name;
    private String phone;
    SharedPrefManager prefManager;
    private Bitmap bitmap;

    //    private AuthConfig authConfig;
    private View.OnClickListener snackBarRefreshOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        fetchUserDetails();
        //userBean = (UserBean) getIntent().getSerializableExtra("bean");

        initViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
       /* if (userBean != null) {
            getData(true);
        } else {
            setProgressScreenVisibility(true, true);
            getData(false);
        }*/
    }

    private void getData(boolean isSwipeRefreshing) {

        swipeView.setRefreshing(isSwipeRefreshing);

    }

    public void initViews() {

        snackBarRefreshOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

//                mVibrator.vibrate(25);
                setProgressScreenVisibility(true, true);
                getData(false);
            }
        };
       /* AuthConfig.Builder builder = new AuthConfig.Builder();

        builder.withAuthCallBack(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {

                performMobileAvailabilityCheck(phoneNumber);
            }

            @Override
            public void failure(DigitsException exception) {

            }
        });

        authConfig = builder.build();*/

        coordinatorLayout.removeView(toolbar);

        toolbarEdit = (Toolbar) getLayoutInflater().inflate(R.layout.toolbar_edit_page, toolbar);
        coordinatorLayout.addView(toolbarEdit, 0);
        setSupportActionBar(toolbarEdit);

        etxtName = (EditText) findViewById(R.id.etxt_name_edit_page);
        etxtLname = (EditText) findViewById(R.id.etxt_LastName_edit_page);
        etxtPhone = (EditText) findViewById(R.id.etxt_number_edit_page);

        ivProfilePhoto = (ImageView) findViewById(R.id.iv_profile_photo);

        if (prefManager!= null)

     populateUserDetails();

    }

    public void populateUserDetails() {
        etxtName.setText(SharedPrefManager.getInstans(getApplicationContext()).getUsername());
        etxtLname.setText(SharedPrefManager.getInstans(getApplicationContext()).getLastName());
        etxtPhone.setText(SharedPrefManager.getInstans(getApplicationContext()).getmobile());
       // ivProfilePhoto.setImageBitmap(SharedPrefManager.getInstans((getApplicationContext().getImage)));


        swipeView.setRefreshing(false);
        setProgressScreenVisibility(false, false);

    }

    public void fetchUserDetails() {
        RequestQueue queue = Volley.newRequestQueue(ProfileEditActivity.this);


        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, userProfile,
                new Response.Listener<String>()  {
                    @Override
                    public void onResponse(String response) {
                        //JSONObject jsonobject = new JSONObject();


                        try {
                            JSONObject jsonObject2, jsonObject3, jsonObject = new JSONObject(response);

                            Log.d( "onResponse: ",jsonObject.toString());

                            if (jsonObject.getString("status").equals("1")) {


                                SharedPrefManager.getInstans(getApplicationContext()).fatchdata(
                                        jsonObject.getString("mobile"));


                                //jsonObject2 = jsonObject.getJSONObject("response");
//                                jsonObject3 = jsonObject.getJSONObject("customer_data");
//                                SessionManager.save_user_id(prefs, jsonObject3.getString("customer_id"));
//                                SessionManager.save_fisrtName(prefs, jsonObject3.getString("fname"));
//                                SessionManager.save_lastName(prefs, jsonObject3.getString("lname"));
//                                SessionManager.save_emailid(prefs, jsonObject3.getString("email"));
////                                SessionManager.save_lastName(prefs, jsonObject2.getString("lastname"));
////                                SessionManager.save_dob(prefs, jsonObject2.getString("dob"));
//                                SessionManager.save_mobile(prefs, jsonObject3.getString("phone"));
                                //  jsonObject2 = jsonObject.getJSONObject("customer_data");

                               // Toast.makeText(ProfileEditActivity.this, "success", Toast.LENGTH_SHORT).show();
                                // jsonObject.getString("mobile"));
                                populateUserDetails();
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


                        }
                        //Toast.makeText(LoginActivity.this, "abc", Toast.LENGTH_SHORT).show();


                        catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileEditActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.

                //  params.put(SharedHelper.getKey(LoginActivity.this,"id"))
                //    params.put("id", SharedHelper.getKey(LoginActivity.this,"id")+"");
                params.put("id", (SharedPrefManager.getInstans(getApplicationContext()).getUserId()));
                return params;
            }

        };

        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        queue.add(jsonObjectRequest);


    };









        //I will this side Edit Email is mendatory

       /* if (email.equalsIgnoreCase("")) {
            Snackbar.make(coordinatorLayout, R.string.message_email_is_required, Snackbar.LENGTH_LONG)
                    .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Snackbar.make(coordinatorLayout, R.string.message_enter_a_valid_email_address, Snackbar.LENGTH_LONG)
                    .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
            return false;
        }*/




    public void onSuccessButtonClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);



                performEditProfile();



    }

    public void performEditProfile() {




        final String    FNameholder = etxtName.getText().toString().trim();
        final String LNameHolder = etxtLname.getText().toString().trim();
        final String Mobileholder=etxtPhone.getText().toString().trim();


        if (TextUtils.isEmpty(FNameholder)) {
            etxtName.setError("Please enter First Name");
            etxtName.requestFocus();

            return;
        }
        if (TextUtils.isEmpty(LNameHolder)) {
            etxtLname.setError("Please enter Last Name");
            etxtLname.requestFocus();

            return;
        }

        if (TextUtils.isEmpty(Mobileholder)) {

        etxtPhone.setError("please enter new Mobile no");
        etxtPhone.requestFocus();
        return;

        }

        RequestQueue queue = Volley.newRequestQueue(ProfileEditActivity.this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, UpdateUserProfile,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {


                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(ServerResponse);




                            //obj.put("", "password");





                            //if no error in response
                            //SharedHelper.putKey(LoginActivity.this, "mobile", mobile.getText().toString());
                            // SharedHelper.putKey(LoginActivity.this, "password", Password.getText().toString());

                            Toast.makeText(ProfileEditActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Toast.makeText(LoginActivity.this, "abc", Toast.LENGTH_SHORT).show();


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.


                        // Showing error message if something goes wrong.
                        Toast.makeText(ProfileEditActivity.this, "Retry", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.


                params.put("id",  (SharedPrefManager.getInstans(getApplicationContext()).getUserId()));
                params.put("first_name", FNameholder);
                params.put("last_name",LNameHolder);
                params.put("mobile",Mobileholder);
                params.put("capture", String.valueOf(ivProfilePhoto));
                return params;
            }

        };

        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        queue.add(stringRequest);


    }










    public void onPhotoEditClick(final View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        SelectPhotoDialog selectPhotoDialog = new SelectPhotoDialog(this);
        selectPhotoDialog.setSelectPhotoDialogActionListener(new SelectPhotoDialog.SelectPhotoDialogActionListener() {
            @Override
            public void onSelectGalleryClick() {
                onAddProfilePhotoFromGallery();
            }

            @Override
            public void onSelectCameraClick() {
                onAddProfilePhotoFromCamera();
            }
        });
        selectPhotoDialog.show();
    }


    public void onAddProfilePhotoFromGallery() {

        if (!checkForReadWritePermissions()) {
            getReadWritePermissions();

        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_IMAGE_GALLERY);

        }
    }

    public void onAddProfilePhotoFromCamera() {

        if (!checkForReadWritePermissions()) {
            getReadWritePermissions();
        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile(0);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                if (photoFile != null) {
                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(photoFile));
                    } else {
                        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                                getApplicationContext().getPackageName() + ".provider", photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    }
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAMERA);

                }
            }
        }
    }

    private File createImageFile(int op) throws IOException {
        File image = null;

        if (op == 0) {
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());
            String imageFileName = "LaTaxi" + timeStamp + "_";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File storageDir = new File(
                        Environment.getExternalStorageDirectory() + "/LaTaxi/Photo/");
                if (!storageDir.exists()) {
                    storageDir.mkdirs();
                }
                image = new File(storageDir + imageFileName + ".jpg");
            } else {
                image = new File(getFilesDir() + "/" + imageFileName + ".jpg");
            }

            image.createNewFile();
            // Save a file: path for use with ACTION_VIEW intents
            imagePath = image.getAbsolutePath();
        }
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAMERA && resultCode == RESULT_OK) {
            displayPicImage = imagePath;
            //    setBannerPic(tempImagePath);
            setDisplayPic(imagePath);
        }
        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {

            String imageFilePath = ImageFilePath.getPath(getApplicationContext(), data.getData());
            System.out.println(imageFilePath);

            displayPicImage = imageFilePath;
            setDisplayPic(imageFilePath);

        }

    }
    private void setDisplayPic(String tempImagePath) {

        Glide.with(getApplicationContext())
                .load(tempImagePath)
                .apply(new RequestOptions()
                        .error(R.drawable.ic_dummy_photo)
                        .fallback(R.drawable.ic_dummy_photo)
                        .centerCrop()
                        .circleCrop())
                .into(ivProfilePhoto);


    }



    public void onProfileEditPhoneClick(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

/*        Digits.logout();
        Digits.authenticate(authConfig);
        new Digits.Builder().withTheme(R.style.AppTheme).build();*/

        FirebaseAuth.getInstance().signOut();
        startActivityForResult(new Intent(this, MobileVerificationActivity.class)
                , REQ_MOBILE_VERIFICATION);

    }

    public void performMobileAvailabilityCheck(final String phoneNumber) {

        swipeView.setRefreshing(true);

        JSONObject postData = getMobileAvailabilityCheckJSObj(phoneNumber);


    }

    private JSONObject getMobileAvailabilityCheckJSObj(String phoneNumber) {

        JSONObject postData = new JSONObject();

        try {

            postData.put("phone", phoneNumber);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postData;
    }
}
