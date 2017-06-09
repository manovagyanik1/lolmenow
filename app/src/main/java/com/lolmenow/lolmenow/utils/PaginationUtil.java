package com.lolmenow.lolmenow.utils;

import com.lolmenow.lolmenow.models.PageInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by shubhamagrawal on 08/06/17.
 */

public class PaginationUtil {
    public static String getItems(JSONObject response) throws JSONException {
        return response.getString("items");
    }

    public static PageInfo getPageInfo(JSONObject response) throws JSONException, IOException {
        String pageInfoString = response.getString("pageInfo");
        return Gen.getObjectMapper().readValue(pageInfoString, PageInfo.class);
    }
}
