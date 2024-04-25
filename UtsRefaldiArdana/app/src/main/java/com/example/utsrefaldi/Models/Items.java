package com.example.utsrefaldi.Models;

import com.google.gson.annotations.SerializedName;

public class Items {
    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("bio")
    private String bio;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getBio() {
        return bio;
    }
}
