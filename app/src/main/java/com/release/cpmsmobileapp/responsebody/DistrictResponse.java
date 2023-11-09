package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;

public class DistrictResponse {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("name_hi")
    private String name_hi;

    @SerializedName("dist_name")
    private String dist_name;




    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getName_hi() {
        return name_hi;
    }

    public String getDist_name() { return dist_name; }


}
