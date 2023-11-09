package com.release.cpmsmobileapp.requestbody;

import com.google.gson.annotations.SerializedName;

public class ByDateRequestBody {
    @SerializedName("gpf_no")
    private String gpfNumber;

    @SerializedName("date")
    private String date;

    public ByDateRequestBody(String gpfNumber, String date) {
        this.gpfNumber = gpfNumber;
        this.date = date;
    }

    public void setGpfNumber(String gpfNumber) {
        this.gpfNumber = gpfNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGpfNumber() {
        return gpfNumber;
    }

    public String getDate() {
        return date;
    }
}
