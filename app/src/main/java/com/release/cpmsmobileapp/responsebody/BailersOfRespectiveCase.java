package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;

public class BailersOfRespectiveCase {

    @SerializedName("id")
    private Integer id;

    @SerializedName("accused_name")
    private String accused_name;

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

    @SerializedName("id_no")
    private String id_no;

    @SerializedName("case_detail_id")
    private Integer case_detail_id;

    @SerializedName("bail_date")
    private String bail_date;

    @SerializedName("remarks")
    private String remarks;

    public BailersOfRespectiveCase(Integer id, String accused_name, String name, String fathers_name, String address, String phone_no, String email, String id_no, Integer case_detail_id, String bail_date, String remarks) {
        this.id = id;
        this.accused_name = accused_name;
        this.name = name;
        this.fathers_name = fathers_name;
        this.address = address;
        this.phone_no = phone_no;
        this.email = email;
        this.id_no = id_no;
        this.case_detail_id = case_detail_id;
        this.bail_date = bail_date;
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccused_name() {
        return accused_name;
    }

    public void setAccused_name(String accused_name) {
        this.accused_name = accused_name;
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

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public Integer getCase_detail_id() {
        return case_detail_id;
    }

    public void setCase_detail_id(Integer case_detail_id) {
        this.case_detail_id = case_detail_id;
    }

    public String getBail_date() {
        return bail_date;
    }

    public void setBail_date(String bail_date) {
        this.bail_date = bail_date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
