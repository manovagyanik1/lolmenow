package com.lolmenow.lolmenow.models;


import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

@IgnoreExtraProperties
public  class Reaction{
    public Integer lol;
    public Integer xd;
    public Integer wow;
    public Integer claps;

    public Reaction(Integer lol, Integer xd, Integer wow, Integer claps) {
        this.lol = lol;
        this.xd = xd;
        this.wow = wow;
        this.claps = claps;
    }

    public Reaction() {
    }

    public Integer getLol() {
        return lol;
    }

    public void setLol(Integer lol) {
        this.lol = lol;
    }

    public Integer getXd() {
        return xd;
    }

    public void setXd(Integer xd) {
        this.xd = xd;
    }

    public Integer getWow() {
        return wow;
    }

    public void setWow(Integer wow) {
        this.wow = wow;
    }

    public Integer getClaps() {
        return claps;
    }

    public void setClaps(Integer claps) {
        this.claps = claps;
    }
}
