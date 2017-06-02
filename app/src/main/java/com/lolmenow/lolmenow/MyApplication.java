package com.lolmenow.lolmenow;

import android.app.Application;
import android.content.Context;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private static final String LOG_TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }
}