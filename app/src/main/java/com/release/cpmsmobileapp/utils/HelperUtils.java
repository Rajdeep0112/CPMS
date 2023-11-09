package com.release.cpmsmobileapp.utils;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.release.cpmsmobileapp.CPMSUser;
import com.release.cpmsmobileapp.LoginActivity;
import com.release.cpmsmobileapp.R;
import com.release.cpmsmobileapp.responsebody.GpfResponse;
import com.release.cpmsmobileapp.responsebody.LoginResponse;

public class HelperUtils {
    SharedPreferences sharedPreferences;
    Context context;

    public HelperUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.user_preferences), MODE_PRIVATE);
        this.context = context;
    }

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

    public void setAllUserDetails(LoginResponse model){
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.user_preferences), MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(String.valueOf(R.string.token), model.getToken());
        editor.putString(String.valueOf(R.string.refresh_token), model.getRefreshToken());
        editor.putString(String.valueOf(R.string.id), String.valueOf(model.getUser().getId()));
        editor.putString(String.valueOf(R.string.name), model.getUser().getName());
        editor.putString(String.valueOf(R.string.range_id), String.valueOf(model.getUser().getRange_id()));
        editor.putString(String.valueOf(R.string.district_id), String.valueOf(model.getUser().getDistrict_id()));
        editor.putString(String.valueOf(R.string.ps_id), String.valueOf(model.getUser().getPs_id()));
        editor.putString(String.valueOf(R.string.pin), String.valueOf(model.getUser().getPin()));
        editor.putString(String.valueOf(R.string.created_on), model.getUser().getCreated_on());
        editor.putString(String.valueOf(R.string.gpf_no), model.getUser().getUsername());
        editor.putString(String.valueOf(R.string.role_id), String.valueOf(model.getUser().getRoles().getId()));
        editor.putString(String.valueOf(R.string.role_name), model.getUser().getRoles().getName());
        editor.apply();
    }

    public void setGpfDetails(GpfResponse model){
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.user_preferences), MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(String.valueOf(R.string.ecode),""+model.getResult().get(0).getEcode());
        editor.putString(String.valueOf(R.string.mobile_no),""+model.getResult().get(0).getMobileNo());
        editor.putString(String.valueOf(R.string.father_name),""+model.getResult().get(0).getFatherName());
        editor.putString(String.valueOf(R.string.brass_no),""+model.getResult().get(0).getBrassNo());
        editor.putString(String.valueOf(R.string.officer_rank),""+model.getResult().get(0).getOfficerRank());
        editor.apply();
    }

    public void logout() {
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.user_preferences), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(String.valueOf(R.string.token), "");
        editor.putString(String.valueOf(R.string.refresh_token), "");
        editor.putString(String.valueOf(R.string.id), "");
        editor.putString(String.valueOf(R.string.name), "");
        editor.putString(String.valueOf(R.string.range_id), "");
        editor.putString(String.valueOf(R.string.district_id), "");
        editor.putString(String.valueOf(R.string.ps_id), "");
        editor.putString(String.valueOf(R.string.pin), "");
        editor.putString(String.valueOf(R.string.created_on), "");
        editor.putString(String.valueOf(R.string.gpf_no), "");
        editor.putString(String.valueOf(R.string.role_id), "");
        editor.putString(String.valueOf(R.string.role_name), "");

        editor.putString(String.valueOf(R.string.ecode),"");
        editor.putString(String.valueOf(R.string.mobile_no),"");
        editor.putString(String.valueOf(R.string.father_name),"");
        editor.putString(String.valueOf(R.string.brass_no),"");
        editor.putString(String.valueOf(R.string.officer_rank),"");

        editor.apply();
        Toast.makeText(context, "User logged out.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        ((Activity) (context)).finish();
    }
}
