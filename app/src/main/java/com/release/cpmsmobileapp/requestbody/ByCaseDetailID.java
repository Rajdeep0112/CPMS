package com.release.cpmsmobileapp.requestbody;

import com.google.gson.annotations.SerializedName;

public class ByCaseDetailID {

    @SerializedName("case_detail_id")
    private String case_detail_id;

    public ByCaseDetailID(String case_detail_id) {
        this.case_detail_id = case_detail_id;
    }

    public String getCase_detail_id() {
        return case_detail_id;
    }

    public void setCase_detail_id(String case_detail_id) {
        this.case_detail_id = case_detail_id;
    }
}
