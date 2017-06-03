package com.lolmenow.lolmenow.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

@IgnoreExtraProperties
public class Share {
    public String shareUrl;
    public Integer shareCount;

    public Share(String shareUrl, Integer shareCount) {
        this.shareUrl = shareUrl;
        this.shareCount = shareCount;
    }

    public Share() {
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }
}

