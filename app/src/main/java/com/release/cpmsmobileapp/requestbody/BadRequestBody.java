package com.release.cpmsmobileapp.requestbody;

import com.google.gson.annotations.SerializedName;

public class BadRequestBody {
    @SerializedName("statusCode")
    private Integer statusCode;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("message")
    private String message;
    @SerializedName("description")
    private String description;

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
