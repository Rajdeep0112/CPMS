package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AccusedOfRespectiveCase implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("case_detail_id")
    private Integer case_detail_id;

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

    @SerializedName("aadhar")
    private String aadhar;

    @SerializedName("remarks")
    private String remarks;

    @SerializedName("accused_status")
    private String accused_status;

    @SerializedName("bailer_name")
    private String bailer_name;

    public AccusedOfRespectiveCase(Integer id, Integer case_detail_id, String name, String fathers_name, String address, String phone_no, String email, String aadhar, String remarks, String accused_status, String bailer_name) {
        this.id = id;
        this.case_detail_id = case_detail_id;
        this.name = name;
        this.fathers_name = fathers_name;
        this.address = address;
        this.phone_no = phone_no;
        this.email = email;
        this.aadhar = aadhar;
        this.remarks = remarks;
        this.accused_status = accused_status;
        this.bailer_name = bailer_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCase_detail_id() {
        return case_detail_id;
    }

    public void setCase_detail_id(Integer case_detail_id) {
        this.case_detail_id = case_detail_id;
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

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAccused_status() {
        return accused_status;
    }

    public void setAccused_status(String accused_status) {
        this.accused_status = accused_status;
    }

    public String getBailer_name() {
        return bailer_name;
    }

    public void setBailer_name(String bailer_name) {
        this.bailer_name = bailer_name;
    }
}
