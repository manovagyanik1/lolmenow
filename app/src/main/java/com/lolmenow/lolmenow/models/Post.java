package com.lolmenow.lolmenow.models;


import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

@IgnoreExtraProperties
public class Post{
    private PostType type;
    private PostData data;

    public PostType getType() {
        return type;
    }

    public void setType(PostType type) {
        this.type = type;
    }

    public Post() {
    }

    public PostData getData() {
        return data;
    }

    public void setData(PostData data) {
        this.data = data;
    }

    public Post(PostType type, PostData data) {
        this.type = type;

        this.data = data;
    }


}