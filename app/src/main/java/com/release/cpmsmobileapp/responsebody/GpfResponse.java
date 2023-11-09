package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GpfResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("result")
    private List<Result> result;

    public boolean isSuccess() {
        return success;
    }

    public List<Result> getResult() {
        return result;
    }

    public static class Result {
        @SerializedName("ECODE")
        private int ecode;

        @SerializedName("NAME")
        private String name;

        @SerializedName("MOBILE_NO")
        private String mobileNo;

        @SerializedName("FATHER_NAME")
        private String fatherName;

        @SerializedName("GPF_NO")
        private String gpfNo;

        @SerializedName("BRASS_NO")
        private String brassNo;

        @SerializedName("OFFICER_RANK")
        private String officerRank;

        // Getters and setters for each field
        public int getEcode() {
            return ecode;
        }

        public String getName() {
            return name;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public String getFatherName() {
            return fatherName;
        }

        public String getGpfNo() {
            return gpfNo;
        }

        public String getBrassNo() {
            return brassNo;
        }

        public String getOfficerRank() {
            return officerRank;
        }
    }
}
