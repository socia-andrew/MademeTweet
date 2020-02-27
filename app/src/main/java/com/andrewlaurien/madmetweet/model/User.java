package com.andrewlaurien.madmetweet.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private long id;
    @SerializedName("id_str")
    private String id_str;
    @SerializedName("name")
    private String name;
    @SerializedName("screen_name")
    private String screen_name;
    @SerializedName("profile_background_image_url")
    private String profile_background_image_url;


    public long getId() {
        return id;
    }

    public String getId_str() {
        return id_str;
    }

    public String getName() {
        return name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getProfile_background_image_url() {
        return profile_background_image_url;
    }
}
