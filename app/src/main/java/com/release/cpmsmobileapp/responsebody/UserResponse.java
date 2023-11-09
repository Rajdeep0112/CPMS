package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("range_id")
    private Integer range_id;

    @SerializedName("district_id")
    private Integer district_id;

    @SerializedName("ps_id")
    private Integer ps_id;

    @SerializedName("pin")
    private Integer pin;

    @SerializedName("created_on")
    private String created_on;

    @SerializedName("roles")
    private Roles roles;

    @SerializedName("username")
    private String username;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getRange_id() {
        return range_id;
    }

    public Integer getDistrict_id() {
        return district_id;
    }

    public Integer getPs_id() {
        return ps_id;
    }

    public Integer getPin() {
        return pin;
    }

    public String getCreated_on() {
        return created_on;
    }

    public Roles getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public static class Roles{
        @SerializedName("id")
        private Integer id;

        @SerializedName("name")
        private String name;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
