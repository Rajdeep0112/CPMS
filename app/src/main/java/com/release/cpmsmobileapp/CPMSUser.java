package com.release.cpmsmobileapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class CPMSUser {

    SharedPreferences sharedPreferences;
    Context context;
    private String token;
    private String refreshToken;
    private int id;
    private String name;
    private int rangeId;
    private int districtId;
    private int psId;
    private int pin;
    private String createdOn;
    private String username;
    private int roleId;
    private String roleName;
    private String ecode,mobileNo,fatherName,brassNo,officerRank;

    // Getters and Setters

    public String getGpfNo() {
        return sharedPreferences.getString(String.valueOf(R.string.gpf_no), "");
    }

    public String getToken() {
        return sharedPreferences.getString(String.valueOf(R.string.token), "");
    }

    public String getRefreshToken(){
        return sharedPreferences.getString(String.valueOf(R.string.refresh_token), "");
    }

    public String getName(){
        return sharedPreferences.getString(String.valueOf(R.string.name), "");
    }

    public String getEcode(){
        return sharedPreferences.getString(String.valueOf(R.string.ecode), "");
    }

    public String getMobileNo(){
        return sharedPreferences.getString(String.valueOf(R.string.mobile_no), "");
    }

    public String getFatherName(){
        return sharedPreferences.getString(String.valueOf(R.string.father_name), "");
    }

    public String getBrassNo(){
        return sharedPreferences.getString(String.valueOf(R.string.brass_no), "");
    }

    public String getOfficerRank(){
        return sharedPreferences.getString(String.valueOf(R.string.officer_rank), "");
    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public String getRefreshToken() {
//        return refreshToken;
//    }

    public int getId() {
        return id;
    }

//    public String getName() {
//        return name;
//    }

    public int getRangeId() {
        return rangeId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public int getPsId() {
        return psId;
    }

    public int getPin() {
        return pin;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getUsername() {
        return username;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRangeId(int rangeId) {
        this.rangeId = rangeId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public void setPsId(int psId) {
        this.psId = psId;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    // Default constructor
    public CPMSUser() {
    }

    public CPMSUser(Context context){
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.user_preferences), MODE_PRIVATE);
        this.context = context;
    }

    // Constructor with all fields
    public CPMSUser(String token, String refreshToken, int id, String name, int rangeId, int districtId, int psId,
                    int pin, String createdOn, String username, int roleId, String roleName) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = id;
        this.name = name;
        this.rangeId = rangeId;
        this.districtId = districtId;
        this.psId = psId;
        this.pin = pin;
        this.createdOn = createdOn;
        this.username = username;
        this.roleId = roleId;
        this.roleName = roleName;
    }
}

