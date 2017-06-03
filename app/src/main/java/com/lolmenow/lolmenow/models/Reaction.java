package com.lolmenow.lolmenow.models;

import com.google.auto.value.AutoValue;
import com.google.firebase.database.DataSnapshot;

import me.mattlogan.auto.value.firebase.annotation.FirebaseValue;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

@AutoValue
@FirebaseValue
public abstract class Reaction{
    public abstract Integer rofl();
    public abstract Integer xd();
    public abstract Integer wow();
    public abstract Integer claps();

    public static Reaction create(Integer rofl, Integer xd, Integer wow, Integer claps) {
        return builder()
                .rofl(rofl)
                .xd(xd)
                .wow(wow)
                .claps(claps)
                .build();
    }


    public static Reaction create(DataSnapshot ds){
        return ds.getValue(AutoValue_Reaction.FirebaseValue.class).toAutoValue();
    }

    public Object toFirebaseValue(){
        return new AutoValue_Reaction.FirebaseValue(this);
    }

    public static Builder builder() {
        return new AutoValue_Reaction.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder rofl(Integer rofl);

        public abstract Builder xd(Integer xd);

        public abstract Builder wow(Integer wow);

        public abstract Builder claps(Integer claps);

        public abstract Reaction build();
    }
}
