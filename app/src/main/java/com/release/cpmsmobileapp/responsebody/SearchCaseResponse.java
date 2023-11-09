package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchCaseResponse implements Serializable {

    // initially expandable is false;
    private boolean isVisible = false;

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
    @SerializedName("FIR_no")
    private String FIR_no;
    @SerializedName("year")
    private String year;
    @SerializedName("reg_date")
    private String reg_date;

    @SerializedName("case_detail_id")
    private Integer case_detail_id;

    @SerializedName("act_section")
    private String act_section;
    @SerializedName("case_detail")
    private String case_detail;
    @SerializedName("remarks")
    private String remarks;
    @SerializedName("nodal_officer")
    private String nodal_officer;
    @SerializedName("CaseStatus_name")
    private String caseStatus_name;
    @SerializedName("Ps_name")
    private String ps_name;
    @SerializedName("Ps_name_hi")
    private String ps_name_hi;
    @SerializedName("District_name")
    private String district_name;
    @SerializedName("District_name_hi")
    private String district_name_hi;
    @SerializedName("MajorHead_name")
    private String majorHead_name;
    @SerializedName("MajorHead_name_hi")
    private String majorHead_name_hi;
    @SerializedName("MinorHead_name")
    private String minorHead_name;
    @SerializedName("MinorHead_name_hi")
    private String minorHead_name_hi;

    public SearchCaseResponse(String FIR_no, String year, String reg_date, Integer case_detail_id, String act_section, String case_detail, String remarks, String nodal_officer, String caseStatus_name, String ps_name, String ps_name_hi, String district_name, String district_name_hi, String majorHead_name, String majorHead_name_hi, String minorHead_name, String minorHead_name_hi) {
        this.FIR_no = FIR_no;
        this.year = year;
        this.reg_date = reg_date;
        this.case_detail_id = case_detail_id;
        this.act_section = act_section;
        this.case_detail = case_detail;
        this.remarks = remarks;
        this.nodal_officer = nodal_officer;
        this.caseStatus_name = caseStatus_name;
        this.ps_name = ps_name;
        this.ps_name_hi = ps_name_hi;
        this.district_name = district_name;
        this.district_name_hi = district_name_hi;
        this.majorHead_name = majorHead_name;
        this.majorHead_name_hi = majorHead_name_hi;
        this.minorHead_name = minorHead_name;
        this.minorHead_name_hi = minorHead_name_hi;
    }

    public void setFIR_no(String FIR_no) {
        this.FIR_no = FIR_no;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public void setCase_detail_id(Integer case_detail_id) {
        this.case_detail_id = case_detail_id;
    }

    public void setAct_section(String act_section) {
        this.act_section = act_section;
    }

    public void setCase_detail(String case_detail) {
        this.case_detail = case_detail;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setNodal_officer(String nodal_officer) {
        this.nodal_officer = nodal_officer;
    }

    public void setCaseStatus_name(String caseStatus_name) {
        this.caseStatus_name = caseStatus_name;
    }

    public void setPs_name(String ps_name) {
        this.ps_name = ps_name;
    }

    public void setPs_name_hi(String ps_name_hi) {
        this.ps_name_hi = ps_name_hi;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public void setDistrict_name_hi(String district_name_hi) {
        this.district_name_hi = district_name_hi;
    }

    public void setMajorHead_name(String majorHead_name) {
        this.majorHead_name = majorHead_name;
    }

    public void setMajorHead_name_hi(String majorHead_name_hi) {
        this.majorHead_name_hi = majorHead_name_hi;
    }

    public void setMinorHead_name(String minorHead_name) {
        this.minorHead_name = minorHead_name;
    }

    public void setMinorHead_name_hi(String minorHead_name_hi) {
        this.minorHead_name_hi = minorHead_name_hi;
    }

    public String getFIR_no() {
        return FIR_no;
    }

    public String getYear() {
        return year;
    }

    public String getReg_date() {
        return reg_date;
    }

    public Integer getCase_detail_id() {
        return case_detail_id;
    }

    public String getAct_section() {
        return act_section;
    }

    public String getCase_detail() {
        return case_detail;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getNodal_officer() {
        return nodal_officer;
    }

    public String getCaseStatus_name() {
        return caseStatus_name;
    }

    public String getPs_name() {
        return ps_name;
    }

    public String getPs_name_hi() {
        return ps_name_hi;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public String getDistrict_name_hi() {
        return district_name_hi;
    }

    public String getMajorHead_name() {
        return majorHead_name;
    }

    public String getMajorHead_name_hi() {
        return majorHead_name_hi;
    }

    public String getMinorHead_name() {
        return minorHead_name;
    }

    public String getMinorHead_name_hi() {
        return minorHead_name_hi;
    }
}
