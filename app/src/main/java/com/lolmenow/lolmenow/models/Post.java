package com.lolmenow.lolmenow.models;

import com.google.auto.value.AutoValue;

import java.net.URL;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

@AutoValue
public abstract class Post{
    public abstract String mediaUrl();
    public abstract Reaction reaction();
    public abstract Share share();

    public static Builder builder() {
        return new AutoValue_Post.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder mediaUrl(String mediaUrl);

        public abstract Builder reaction(Reaction reaction);

        public abstract Builder share(Share share);

        public abstract Post build();
    }
}