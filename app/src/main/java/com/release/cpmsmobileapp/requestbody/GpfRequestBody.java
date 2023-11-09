package com.release.cpmsmobileapp.requestbody;

import com.google.gson.annotations.SerializedName;

public class GpfRequestBody {
    @SerializedName("gpf_no")
    private String gpfNo;

    public GpfRequestBody(String gpfNo) {
        this.gpfNo = gpfNo;
    }
}
