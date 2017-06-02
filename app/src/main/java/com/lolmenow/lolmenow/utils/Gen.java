package com.lolmenow.lolmenow.utils;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lolmenow.lolmenow.BuildConfig;
import com.lolmenow.lolmenow.MyApplication;
import com.lolmenow.lolmenow.R;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by shubhamagrawal on 03/04/17.
 */

public class Gen {
    public static final String TAG = Gen.class.getSimpleName();
    public static final String SERVER_URL = BuildConfig.SERVER_URL;
    public static final String NOTIFICATION_TYPE = "notification_type";
    public static final String FEEDBACK_NOTIFICATION = "FEEDBACK NOTIFICATION";
    public static final String CALL_ENDED_NOTIFICATION = "CALL ENDED NOTIFICATION";
    public static final String FCM_TOKEN_KEY = "fcm_token";
    public static final String RINGING_NOTIFICATION = "RINGING NOTIFICATION";
    private static Activity activity = null;
    private static Boolean appActive = false;

    public static void toast(String text) {
        Toast.makeText(MyApplication.getAppContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(String text) {
        Toast.makeText(MyApplication.getAppContext(), text, Toast.LENGTH_LONG).show();
    }

    private static ObjectMapper objectMapper;


    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public static String getTimeDiffFromCurrentTime(long seconds) {
        long hour = seconds / 3600;
        seconds = seconds % 3600;
        long min = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hour, min, seconds);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, MyApplication.getAppContext().getResources().getDisplayMetrics());
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static String getJSONString(Object obj) {
        ObjectMapper mapper = getObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void showVolleyError(VolleyError error) {
        Gen.showError(error);
        if (error.networkResponse != null) {
            if (error.networkResponse.data != null) {
                try {
                    String body = new String(error.networkResponse.data, "UTF-8");
                    Log.e(TAG, body);
                    AnalyticsManager.log(AnalyticsManager.Event.ERROR, AnalyticsManager.Param.ERROR_BODY, body);
                } catch (UnsupportedEncodingException e) {
                }
            }
        }
    }

    public static void showError(Exception e) {
        e.printStackTrace();
        AnalyticsManager.log(AnalyticsManager.Event.ERROR, AnalyticsManager.Param.STACK_TRACE, e.getMessage());
        toast("Some error occurred!!");
    }

    public static void saveUserIdToLocalStorage(String userId) {
        SharedPreferences settings = MyApplication.getAppContext().getSharedPreferences(Constants.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constants.USER_ID, userId);
        editor.commit();
    }

    public static String getUserIdFromLocalStorage() {
        SharedPreferences settings = MyApplication.getAppContext().getSharedPreferences(Constants.PREFS_NAME, 0);
        return settings.getString(Constants.USER_ID, "");
    }

    public static void saveSessionIdToLocalStorage(String userId) {
        SharedPreferences settings = MyApplication.getAppContext().getSharedPreferences(Constants.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constants.SESSION_ID, userId);
        editor.commit();
    }

    public static String getSessionIdFromLocalStorage() {
        SharedPreferences settings = MyApplication.getAppContext().getSharedPreferences(Constants.PREFS_NAME, 0);
        return settings.getString(Constants.SESSION_ID, "");
    }

    public static void saveFiltersToLocalStorage(boolean filters) {
        SharedPreferences settings = MyApplication.getAppContext().getSharedPreferences(Constants.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Constants.FILTERS, filters);
        editor.commit();
    }

    public static Boolean getFiltersFromLocalStorage() {
        SharedPreferences settings = MyApplication.getAppContext().getSharedPreferences(Constants.PREFS_NAME, 0);
        return settings.getBoolean(Constants.FILTERS, false);
    }


    public static void saveFCMTokenToLocalStorage(String fcmToken) {
        SharedPreferences settings = MyApplication.getAppContext().getSharedPreferences(Constants.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constants.FCM_TOKEN, fcmToken);
        editor.commit();
    }

    public static void setOtherUserFCMTokenToLocalStorage(String fcmToken) {
        SharedPreferences settings = MyApplication.getAppContext().getSharedPreferences(Constants.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constants.OTHER_USER_FCM_TOKEN, fcmToken);
        editor.commit();
    }

    public static String getFCMTokenFromLocalStorage() {
        SharedPreferences settings = MyApplication.getAppContext().getSharedPreferences(Constants.PREFS_NAME, 0);
        return settings.getString(Constants.FCM_TOKEN, "");
    }

    public static String getOtherUserFCMTokenFromLocalStorage() {
        SharedPreferences settings = MyApplication.getAppContext().getSharedPreferences(Constants.PREFS_NAME, 0);
        return settings.getString(Constants.OTHER_USER_FCM_TOKEN, "");
    }

    public static void startActivity(Intent intent, boolean clearStack) {
        if (clearStack) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        MyApplication.getAppContext().startActivity(intent);
    }

    public static void startActivity(Activity source, boolean clearStack, Class<?> destination) {
        Gen.hideLoader(source);
        Intent intent = new Intent(MyApplication.getAppContext(), destination);
        startActivity(intent, clearStack);
    }

    public static void startActivity(Activity source, boolean clearStack, Class<?> destination, String key, String value) {
        Gen.hideLoader(source);
        Intent intent = new Intent(MyApplication.getAppContext(), destination);
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        intent.putExtras(bundle);
        startActivity(intent, clearStack);
    }

    public static void showLoader(Activity activity) {
        ViewGroup view = (ViewGroup) activity.getWindow().getDecorView().getRootView();

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View loader = activity.findViewById(R.id.loading_indicator);
        if (loader == null) {
            inflater.inflate(R.layout.loading_indicator, view, true);
            loader = activity.findViewById(R.id.loading_indicator);
        }
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        loader.setVisibility(View.VISIBLE);
    }

    public static void hideLoader(Activity activity) {
        View loader = activity.findViewById(R.id.loading_indicator);
        if (loader != null) {
            loader.setVisibility(View.GONE);
        }
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public static void setCurrentForegroundActivity(Activity act){
        activity = act;
    }

    public static Activity getCurrentForegroundActivity(){
        return activity;
    }

    public static void handleNotification(Bundle bundle) {
        String activityName = bundle.getString(NOTIFICATION_TYPE);
    }

    public static Boolean isAppActive() {
        return appActive;
    }

    public static void setAppActive(Boolean appActive) {
        Gen.appActive = appActive;
    }

    public static String getCurrentAppVersion(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = MyApplication.getAppContext().getPackageManager().getPackageInfo(MyApplication.getAppContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo.versionName;
    }

    public static String getCurrentTimeZone(){
        return TimeZone.getDefault().getID();
    }

    public static String convertTime12To24format(String time) { // 23:02
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        try {
            Date date = parseFormat.parse(time);
            return displayFormat.format(date);
        } catch (ParseException e) {
            Gen.showError(e);
            return null;
        }

    }

    public static String convertTime24To12format(String time){ //12:20:am
        time = time.toUpperCase();
        SimpleDateFormat displayFormat = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat parseFormat = new SimpleDateFormat("HH:mm");
        try {
            Date date = parseFormat.parse(time);
            return displayFormat.format(date);
        } catch (ParseException e) {
            Gen.showError(e);
            return null;
        }

    }
}