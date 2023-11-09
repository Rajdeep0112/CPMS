package com.release.cpmsmobileapp.responsebody;

import com.google.gson.annotations.SerializedName;

public class WitnessOfRespectiveCase {

    @SerializedName("witness_type")
    private String witness_type;

    @SerializedName("witness_side")
    private String witness_side;

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("fathers_name")
    private String fathers_name;

    @SerializedName("case_detail_id")
    private Integer case_detail_id;

    @SerializedName("address")
    private String address;

    @SerializedName("mobile_no")
    private String mobile_no;

    @SerializedName("email")
    private String email;

    @SerializedName("relation_type")
    private String relation_type;

    @SerializedName("id_type")
    private String id_type;

    @SerializedName("id_type_value")
    private String id_type_value;

    public WitnessOfRespectiveCase(String witness_type, String witness_side, Integer id, String name, String fathers_name, Integer case_detail_id, String address, String mobile_no, String email, String relation_type, String id_type, String id_type_value) {
        this.witness_type = witness_type;
        this.witness_side = witness_side;
        this.id = id;
        this.name = name;
        this.fathers_name = fathers_name;
        this.case_detail_id = case_detail_id;
        this.address = address;
        this.mobile_no = mobile_no;
        this.email = email;
        this.relation_type = relation_type;
        this.id_type = id_type;
        this.id_type_value = id_type_value;
    }

    public String getWitness_type() {
        return witness_type;
    }

    public void setWitness_type(String witness_type) {
        this.witness_type = witness_type;
    }

    public String getWitness_side() {
        return witness_side;
    }

    public void setWitness_side(String witness_side) {
        this.witness_side = witness_side;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathers_name() {
        return fathers_name;
    }

    public void setFathers_name(String fathers_name) {
        this.fathers_name = fathers_name;
    }

    public Integer getCase_detail_id() {
        return case_detail_id;
    }

    public void setCase_detail_id(Integer case_detail_id) {
        this.case_detail_id = case_detail_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelation_type() {
        return relation_type;
    }

    public void setRelation_type(String relation_type) {
        this.relation_type = relation_type;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getId_type_value() {
        return id_type_value;
    }

    public void setId_type_value(String id_type_value) {
        this.id_type_value = id_type_value;
    }
}
