package com.jaats.agrovehicle.app;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import io.fabric.sdk.android.Fabric;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.jaats.agrovehicle.config.Config;
import com.jaats.agrovehicle.model.AuthBean;
import com.jaats.agrovehicle.net.WSAsyncTasks.FCMRefreshTask;
import com.jaats.agrovehicle.util.AppConstants;
import com.jaats.agrovehicle.util.FileOp;
import com.jaats.agrovehicle.util.RobotoCondensedTextStyleExtractor;
import com.jaats.agrovehicle.util.TypefaceManager;

import static okhttp3.internal.Internal.instance;

public class App extends Application {











}
