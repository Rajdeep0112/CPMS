package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PsResponse implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("districtId")
    private Integer districtId;
    @SerializedName("name")
    private String name;
    @SerializedName("name_hi")
    private String name_hi;

    public PsResponse(Integer id, Integer districtId, String name, String name_hi) {
        this.id = id;
        this.districtId = districtId;
        this.name = name;
        this.name_hi = name_hi;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public String getName() {
        return name;
    }

    public String getName_hi() {
        return name_hi;
    }
}
