package com.lolmenow.lolmenow.models;

import com.google.auto.value.AutoValue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.IgnoreExtraProperties;

import java.net.URL;

import me.mattlogan.auto.value.firebase.annotation.FirebaseValue;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

@AutoValue
@FirebaseValue
@IgnoreExtraProperties
public abstract class Post{
    public abstract String mediaUrl();
    public abstract Reaction reaction();
    public abstract Share share();

    public static Post create(String mediaUrl, Reaction reaction, Share share) {
        return builder()
                .mediaUrl(mediaUrl)
                .reaction(reaction)
                .share(share)
                .build();
    }

    public static Post create(DataSnapshot ds){
        return ds.getValue(AutoValue_Post.FirebaseValue.class).toAutoValue();
    }

    public Object toFirebaseValue(){
        return new AutoValue_Post.FirebaseValue(this);
    }

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