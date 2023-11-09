package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;

public class PublicProsecutorOfRespectiveCase {

    @SerializedName("status")
    private String status;

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("fathers_name")
    private String fathers_name;

    @SerializedName("address")
    private String address;

    @SerializedName("phone_no")
    private String phone_no;

    @SerializedName("email")
    private String email;

    @SerializedName("case_detail_id")
    private Integer case_detail_id;

    public PublicProsecutorOfRespectiveCase(String status, Integer id, String name, String fathers_name, String address, String phone_no, String email, Integer case_detail_id) {
        this.status = status;
        this.id = id;
        this.name = name;
        this.fathers_name = fathers_name;
        this.address = address;
        this.phone_no = phone_no;
        this.email = email;
        this.case_detail_id = case_detail_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathers_name() {
        return fathers_name;
    }

    public void setFathers_name(String fathers_name) {
        this.fathers_name = fathers_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCase_detail_id() {
        return case_detail_id;
    }

    public void setCase_detail_id(Integer case_detail_id) {
        this.case_detail_id = case_detail_id;
    }

}
