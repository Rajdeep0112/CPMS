package com.release.cpmsmobileapp.requestbody;

public class SearchCaseRequestBody {
    private String fir_no;
    private String year;
    private String district_id;
    private String ps_id;

    public SearchCaseRequestBody(String fir_no, String year, String district_id, String ps_id) {
        this.fir_no = fir_no;
        this.year = year;
        this.district_id = district_id;
        this.ps_id = ps_id;
    }

    public String getFir_no() {
        return fir_no;
    }

    public void setFir_no(String fir_no) {
        this.fir_no = fir_no;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getPs_id() {
        return ps_id;
    }

    public void setPs_id(String ps_id) {
        this.ps_id = ps_id;
    }
}
