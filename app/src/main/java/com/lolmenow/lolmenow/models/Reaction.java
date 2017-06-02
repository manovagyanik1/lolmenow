package com.lolmenow.lolmenow.models;

import com.google.auto.value.AutoValue;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

@AutoValue
public abstract class Reaction{
    public abstract Integer rofl();
    public abstract Integer xd();
    public abstract Integer wow();
    public abstract Integer claps();

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
