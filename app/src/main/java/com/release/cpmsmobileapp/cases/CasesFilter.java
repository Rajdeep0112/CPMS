package com.release.cpmsmobileapp.cases;

public class CasesFilter {
    private String FIR_no;
    private String case_detail_id;
    private String act_section;
    private String ps_name;
    private String start_date;
    private String end_date;

    public CasesFilter(String FIR_no, String case_detail_id, String act_section, String ps_name, String start_date, String end_date) {
        this.FIR_no = FIR_no;
        this.case_detail_id = case_detail_id;
        this.act_section = act_section;
        this.ps_name = ps_name;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public void setFIR_no(String FIR_no) {
        this.FIR_no = FIR_no;
    }

    public void setCase_detail_id(String case_detail_id) {
        this.case_detail_id = case_detail_id;
    }

    public void setAct_section(String act_section) {
        this.act_section = act_section;
    }

    public void setPs_name(String ps_name) {
        this.ps_name = ps_name;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getFIR_no() {
        return FIR_no;
    }

    public String getCase_detail_id() {
        return case_detail_id;
    }

    public String getAct_section() {
        return act_section;
    }

    public String getPs_name() {
        return ps_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }
}
