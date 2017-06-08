package com.lolmenow.lolmenow.models;

/**
 * Created by shubhamagrawal on 08/06/17.
 */

public class Profile {
    private Integer id;
    private String name;
    private String photoUrl;

    public Profile() {
    }

    public Profile(Integer id, String name, String photoUrl) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
