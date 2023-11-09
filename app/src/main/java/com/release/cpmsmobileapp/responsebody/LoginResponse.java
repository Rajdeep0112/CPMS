package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("refresh_token")
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    @SerializedName("user")
    private UserResponse user;

    // getters and setters
    public String getToken() {
        return token;
    }

    public UserResponse getUser() {
        return user;
    }
}
