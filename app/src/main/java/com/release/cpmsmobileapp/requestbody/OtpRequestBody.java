package com.release.cpmsmobileapp.requestbody;

import com.google.gson.annotations.SerializedName;

public class OtpRequestBody {

    @SerializedName("mobile")
    private String mobile;

    public OtpRequestBody(String mobile) {
        this.mobile = mobile;
    }
}
