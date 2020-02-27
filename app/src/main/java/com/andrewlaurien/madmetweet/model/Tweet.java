package com.andrewlaurien.madmetweet.model;

import com.google.gson.annotations.SerializedName;

public class Tweet {
    @SerializedName("text")
    private String text;
    @SerializedName("created_at")
    private String created_at;
    private User user;


    public String getText() {
        return text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public User getUser() {
        return user;
    }
}
