package com.release.cpmsmobileapp.requestbody;

import com.google.gson.annotations.SerializedName;

public class RefreshTokenRequestBody {

    public RefreshTokenRequestBody(String token) {
        this.token = token;
    }

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
