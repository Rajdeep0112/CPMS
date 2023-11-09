package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;

public class NoticesResponse {

    // initially expandable is false;
    private boolean isVisible = false;

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @SerializedName("name")
    private String name;

    @SerializedName("io_name")
    private String io_name;

    @SerializedName("io_assign_date")
    private String io_assign_date;

    @SerializedName("io_compliance_date")
    private String io_compliance_date;

    @SerializedName("notice_completed_date")
    private String notice_completed_date;

    @SerializedName("issue_date")
    private String issue_date;

    @SerializedName("appearance_date")
    private String appearance_date;

    @SerializedName("notice_compliance_date")
    private String notice_compliance_date;

    @SerializedName("from_district_name")
    private String from_district_name;

    @SerializedName("district_name")
    private String district_name;

    @SerializedName("ps_name")
    private String ps_name;

    @SerializedName("dist_refer_id")
    private String dist_refer_id;

    @SerializedName("court_name")
    private String court_name;

    @SerializedName("noticetypes_name")
    private String noticetypes_name;

    @SerializedName("FIR_no")
    private Integer FIR_no;

    @SerializedName("year")
    private String year;

    @SerializedName("reg_date")
    private String reg_date;

    @SerializedName("case_detail_id")
    private Integer case_detail_id;

    public NoticesResponse(String name, String io_name, String io_assign_date, String io_compliance_date, String notice_completed_date, String issue_date, String appearance_date, String notice_compliance_date, String from_district_name, String district_name, String ps_name, String dist_refer_id, String court_name, String noticetypes_name, Integer FIR_no, String year, String reg_date, Integer case_detail_id) {
        this.name = name;
        this.io_name = io_name;
        this.io_assign_date = io_assign_date;
        this.io_compliance_date = io_compliance_date;
        this.notice_completed_date = notice_completed_date;
        this.issue_date = issue_date;
        this.appearance_date = appearance_date;
        this.notice_compliance_date = notice_compliance_date;
        this.from_district_name = from_district_name;
        this.district_name = district_name;
        this.ps_name = ps_name;
        this.dist_refer_id = dist_refer_id;
        this.court_name = court_name;
        this.noticetypes_name = noticetypes_name;
        this.FIR_no = FIR_no;
        this.year = year;
        this.reg_date = reg_date;
        this.case_detail_id = case_detail_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIo_name() {
        return io_name;
    }

    public void setIo_name(String io_name) {
        this.io_name = io_name;
    }

    public String getIo_assign_date() {
        return io_assign_date;
    }

    public void setIo_assign_date(String io_assign_date) {
        this.io_assign_date = io_assign_date;
    }

    public String getIo_compliance_date() {
        return io_compliance_date;
    }

    public void setIo_compliance_date(String io_compliance_date) {
        this.io_compliance_date = io_compliance_date;
    }

    public String getNotice_completed_date() {
        return notice_completed_date;
    }

    public void setNotice_completed_date(String notice_completed_date) {
        this.notice_completed_date = notice_completed_date;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getAppearance_date() {
        return appearance_date;
    }

    public void setAppearance_date(String appearance_date) {
        this.appearance_date = appearance_date;
    }

    public String getNotice_compliance_date() {
        return notice_compliance_date;
    }

    public void setNotice_compliance_date(String notice_compliance_date) {
        this.notice_compliance_date = notice_compliance_date;
    }

    public String getFrom_district_name() {
        return from_district_name;
    }

    public void setFrom_district_name(String from_district_name) {
        this.from_district_name = from_district_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getPs_name() {
        return ps_name;
    }

    public void setPs_name(String ps_name) {
        this.ps_name = ps_name;
    }

    public String getDist_refer_id() {
        return dist_refer_id;
    }

    public void setDist_refer_id(String dist_refer_id) {
        this.dist_refer_id = dist_refer_id;
    }

    public String getCourt_name() {
        return court_name;
    }

    public void setCourt_name(String court_name) {
        this.court_name = court_name;
    }

    public String getNoticetypes_name() {
        return noticetypes_name;
    }

    public void setNoticetypes_name(String noticetypes_name) {
        this.noticetypes_name = noticetypes_name;
    }

    public Integer getFIR_no() {
        return FIR_no;
    }

    public void setFIR_no(Integer FIR_no) {
        this.FIR_no = FIR_no;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public Integer getCase_detail_id() {
        return case_detail_id;
    }

    public void setCase_detail_id(Integer case_detail_id) {
        this.case_detail_id = case_detail_id;
    }
}
