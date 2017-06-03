package com.lolmenow.lolmenow.models;

import com.google.auto.value.AutoValue;
import com.google.firebase.database.DataSnapshot;

import java.net.URL;

import me.mattlogan.auto.value.firebase.annotation.FirebaseValue;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

@AutoValue
@FirebaseValue
public abstract class Share {
    public abstract String shareUrl();
    public abstract Integer shareCount();

    public static Builder builder() {
        return new AutoValue_Share.Builder();
    }


    public static Share create(DataSnapshot ds){
        return ds.getValue(AutoValue_Share.FirebaseValue.class).toAutoValue();
    }

    public Object toFirebaseValue(){
        return new AutoValue_Share.FirebaseValue(this);
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder shareUrl(String shareUrl);

        public abstract Builder shareCount(Integer shareCount);

        public abstract Share build();
    }
}

