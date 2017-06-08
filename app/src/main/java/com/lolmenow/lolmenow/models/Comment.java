package com.lolmenow.lolmenow.models;

import java.sql.Timestamp;

/**
 * Created by shubhamagrawal on 08/06/17.
 */

public class Comment {
    private Integer id;
    private String text;
    private Profile profile;
    private Integer numberOfLikes; // right now we are only having like as the reaction option for comment
    private Integer numberOfReplies;
    private Timestamp timestamp;

    public Comment() {
    }

    public Comment(Integer id, String text, Profile profile, Integer numberOfLikes, Integer numberOfReplies, Timestamp timestamp) {
        this.id = id;
        this.text = text;
        this.profile = profile;
        this.numberOfLikes = numberOfLikes;
        this.numberOfReplies = numberOfReplies;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public Integer getNumberOfReplies() {
        return numberOfReplies;
    }

    public void setNumberOfReplies(Integer numberOfReplies) {
        this.numberOfReplies = numberOfReplies;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimeStampMessage() {
        // TODO: implement this
        return "54m ago";
    }

    public String getNumberOfRepliesMessage() {
        if(this.getNumberOfReplies() == 0){
            return "Reply";
        } else if(this.getNumberOfReplies() == 1) {
            return "1 reply";
        } else {
            return this.getNumberOfReplies() + " replies";
        }
    }

    public String getNumberOfLikesMessage() {
        if(this.getNumberOfLikes() == 0) {
            return "";
        } else {
            return this.getNumberOfLikes().toString();
        }
    }
}
