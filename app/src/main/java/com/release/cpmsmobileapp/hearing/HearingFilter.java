package com.release.cpmsmobileapp.hearing;

public class HearingFilter {
    private String FIR_no;
    private String case_detail_id;
    private String ps_name;
    private String reg_date;

    public HearingFilter(String FIR_no, String case_detail_id, String ps_name, String reg_date) {
        this.FIR_no = FIR_no;
        this.case_detail_id = case_detail_id;
        this.ps_name = ps_name;
        this.reg_date = reg_date;
    }

    public void setFIR_no(String FIR_no) {
        this.FIR_no = FIR_no;
    }

    public void setCase_detail_id(String case_detail_id) {
        this.case_detail_id = case_detail_id;
    }

    public void setPs_name(String ps_name) {
        this.ps_name = ps_name;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getFIR_no() {
        return FIR_no;
    }

    public String getCase_detail_id() {
        return case_detail_id;
    }

    public String getPs_name() {
        return ps_name;
    }

    public String getReg_date() {
        return reg_date;
    }
}
