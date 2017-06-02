package com.lolmenow.lolmenow.models;

import com.google.auto.value.AutoValue;

import java.net.URL;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

@AutoValue
public abstract class Share {
    public abstract String shareUrl();
    public abstract Integer shareCount();

    public static Builder builder() {
        return new AutoValue_Share.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder shareUrl(String shareUrl);

        public abstract Builder shareCount(Integer shareCount);

        public abstract Share build();
    }
}

