package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;


public class HearingListResponse {

    // initially expandable is false;
    private boolean isVisible = false;

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @SerializedName("FIR_no")
    private Integer FIR_no;

    @SerializedName("year")
    private String year;

    @SerializedName("reg_date")
    private String reg_date;

    @SerializedName("ps_name")
    private String ps_name;

    @SerializedName("district_name")
    private String district_name;

    @SerializedName("hearing_date")
    private String hearing_date;

    @SerializedName("case_detail_id")
    private Integer case_detail_id;

    @SerializedName("court_name")
    private String court_name;

    @SerializedName("witnesses")
    private String witnesses;

    @SerializedName("bailer")
    private String bailer;

    @SerializedName("accused")
    private String accused;

    @SerializedName("io")
    private String io;

    public HearingListResponse(Integer FIR_no, String year, String reg_date, String ps_name, String district_name, String hearing_date, Integer case_detail_id, String court_name, String witnesses, String bailer, String accused, String io) {
        this.FIR_no = FIR_no;
        this.year = year;
        this.reg_date = reg_date;
        this.ps_name = ps_name;
        this.district_name = district_name;
        this.hearing_date = hearing_date;
        this.case_detail_id = case_detail_id;
        this.court_name = court_name;
        this.witnesses = witnesses;
        this.bailer = bailer;
        this.accused = accused;
        this.io = io;
    }

    public Integer getFIR_no() {
        return FIR_no;
    }

    public String getYear() {
        return year;
    }

    public String getReg_date() {
        return reg_date;
    }

    public String getPs_name() {
        return ps_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public String getHearing_date() {
        return hearing_date;
    }

    public Integer getCase_detail_id() {
        return case_detail_id;
    }

    public String getCourt_name() {
        return court_name;
    }

    public String getWitnesses() {
        return witnesses;
    }

    public String getBailer() {
        return bailer;
    }

    public String getAccused() {
        return accused;
    }

    public String getIo() {
        return io;
    }

    public void setFIR_no(Integer FIR_no) {
        this.FIR_no = FIR_no;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public void setPs_name(String ps_name) {
        this.ps_name = ps_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public void setHearing_date(String hearing_date) {
        this.hearing_date = hearing_date;
    }

    public void setCase_detail_id(Integer case_detail_id) {
        this.case_detail_id = case_detail_id;
    }

    public void setCourt_name(String court_name) {
        this.court_name = court_name;
    }

    public void setWitnesses(String witnesses) {
        this.witnesses = witnesses;
    }

    public void setBailer(String bailer) {
        this.bailer = bailer;
    }

    public void setAccused(String accused) {
        this.accused = accused;
    }

    public void setIo(String io) {
        this.io = io;
    }
}
