package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;

public class HearingsOfRespectiveCase {

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

    @SerializedName("remarks")
    private String remarks;

    @SerializedName("testifed")
    private String testified;

    public HearingsOfRespectiveCase(String hearing_date, Integer case_detail_id, String court_name, String witnesses, String bailer, String accused, String remarks, String testified) {
        this.hearing_date = hearing_date;
        this.case_detail_id = case_detail_id;
        this.court_name = court_name;
        this.witnesses = witnesses;
        this.bailer = bailer;
        this.accused = accused;
        this.remarks = remarks;
        this.testified = testified;
    }

    public String getHearing_date() {
        return hearing_date;
    }

    public void setHearing_date(String hearing_date) {
        this.hearing_date = hearing_date;
    }

    public Integer getCase_detail_id() {
        return case_detail_id;
    }

    public void setCase_detail_id(Integer case_detail_id) {
        this.case_detail_id = case_detail_id;
    }

    public String getCourt_name() {
        return court_name;
    }

    public void setCourt_name(String court_name) {
        this.court_name = court_name;
    }

    public String getWitnesses() {
        return witnesses;
    }

    public void setWitnesses(String witnesses) {
        this.witnesses = witnesses;
    }

    public String getBailer() {
        return bailer;
    }

    public void setBailer(String bailer) {
        this.bailer = bailer;
    }

    public String getAccused() {
        return accused;
    }

    public void setAccused(String accused) {
        this.accused = accused;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTestified() {
        return testified;
    }

    public void setTestified(String testified) {
        this.testified = testified;
    }
}
