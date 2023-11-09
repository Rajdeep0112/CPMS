package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;

public class HearingsByDateResponse {
    @SerializedName("FIR_no")
    private Integer firNo;

    @SerializedName("year")
    private String year;

    @SerializedName("reg_date")
    private String regDate;

    @SerializedName("ps_name")
    private String policeStationName;

    @SerializedName("district_name")
    private String districtName;

    @SerializedName("hearing_date")
    private String hearingDate;

    @SerializedName("case_detail_id")
    private Integer caseDetailId;

    @SerializedName("court_name")
    private String courtName;

    @SerializedName("witnesses")
    private String witnesses;

    @SerializedName("bailer")
    private String bailer;

    @SerializedName("accused")
    private String accused;

    @SerializedName("io")
    private String investigatingOfficer;

    public HearingsByDateResponse(Integer firNo, String year, String regDate, String policeStationName, String districtName, String hearingDate, Integer caseDetailId, String courtName, String witnesses, String bailer, String accused, String investigatingOfficer) {
        this.firNo = firNo;
        this.year = year;
        this.regDate = regDate;
        this.policeStationName = policeStationName;
        this.districtName = districtName;
        this.hearingDate = hearingDate;
        this.caseDetailId = caseDetailId;
        this.courtName = courtName;
        this.witnesses = witnesses;
        this.bailer = bailer;
        this.accused = accused;
        this.investigatingOfficer = investigatingOfficer;
    }

    public Integer getFirNo() {
        return firNo;
    }

    public String getYear() {
        return year;
    }

    public String getRegDate() {
        return regDate;
    }

    public String getPoliceStationName() {
        return policeStationName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public String getHearingDate() {
        return hearingDate;
    }

    public Integer getCaseDetailId() {
        return caseDetailId;
    }

    public String getCourtName() {
        return courtName;
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

    public String getInvestigatingOfficer() {
        return investigatingOfficer;
    }
}
