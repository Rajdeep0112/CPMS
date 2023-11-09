package com.release.cpmsmobileapp.requestbody;

import com.google.gson.annotations.SerializedName;

public class UserRequestBody {

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("otp")
    private String otp;

    @SerializedName("password")
    private String password;

    public UserRequestBody(String mobile, String otp, String password) {
        this.mobile = mobile;
        this.otp = otp;
        this.password = password;
    }
}
