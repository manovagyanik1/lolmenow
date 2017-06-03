package com.lolmenow.lolmenow.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by shubhamagrawal on 03/06/17.
 */

@IgnoreExtraProperties
public class PostData {
    public String mediaUrl;
    public Reaction reaction;
    public Share share;

    public PostData(String mediaUrl, Reaction reaction, Share share) {
        this.mediaUrl = mediaUrl;
        this.reaction = reaction;
        this.share = share;
    }

    public PostData() {
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }
}
