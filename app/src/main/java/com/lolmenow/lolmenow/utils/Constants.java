package com.lolmenow.lolmenow.utils;

import com.lolmenow.lolmenow.BuildConfig;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

public class Constants {
    public static final String OTHER_USER_FCM_TOKEN = "other_user_fcm_token";
    public static String SHOW_LOGOUT_SCREEN = "show_logout_screen";
    public static String USER_ID = "user_id";
    public static String SESSION_ID = "session_id";
    public static String FCM_TOKEN = "fcm_token";
    public static String PREFS_NAME = "PreferencesFile";
    public static final String SERVER_URL = BuildConfig.SERVER_URL;
    public static final String FORCE_UPDATE = "force_update";
    public static final String BUILD_VERSION = "build_version";
    public static final String TIMEZONE = "timezone";
    public static final String COMMENT_URL = SERVER_URL + "/comment";

    // constants for feed posts fetch.
    public static final Integer FETCH_FEED_POSTS_SIZE = 20;
    public static final Integer FETCH_OFFSET = 10;

}