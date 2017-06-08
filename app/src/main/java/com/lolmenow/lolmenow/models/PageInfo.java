package com.lolmenow.lolmenow.models;

/**
 * Created by shubhamagrawal on 08/06/17.
 */

public class PageInfo {
    private String previousPageUrl;
    private String nextPageUrl;

    public PageInfo() {
    }

    public PageInfo(String previousPageUrl, String nextPageUrl) {
        this.previousPageUrl = previousPageUrl;
        this.nextPageUrl = nextPageUrl;
    }

    public String getPreviousPageUrl() {
        return previousPageUrl;
    }

    public void setPreviousPageUrl(String previousPageUrl) {
        this.previousPageUrl = previousPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }
}
