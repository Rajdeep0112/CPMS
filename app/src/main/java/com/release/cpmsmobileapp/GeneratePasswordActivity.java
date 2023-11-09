package com.release.cpmsmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

//<<<<<<< HEAD
//=======
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

//>>>>>>> c9107e1d334d2af67633f531068dd80ae9734f66
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.release.cpmsmobileapp.requestbody.BadRequestBody;
import com.release.cpmsmobileapp.requestbody.GpfRequestBody;
import com.release.cpmsmobileapp.requestbody.OtpRequestBody;
import com.release.cpmsmobileapp.requestbody.UserRequestBody;
import com.release.cpmsmobileapp.responsebody.GpfResponse;
import com.release.cpmsmobileapp.responsebody.OtpResponse;
import com.release.cpmsmobileapp.responsebody.UserResponse;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneratePasswordActivity extends AppCompatActivity {

    private String MobileNo, Otp, PasswordReset, PasswordConfirm;
    private TextInputLayout mobileNo, otp, passwordReset, passwordConfirm;

    private EditText mobNo;
    private TextView resendOtp,txtSubmit,txtVerify;
    private ApiInterface apiInterface;
    private boolean status = false;
    private CardView submitBtn;

    private EditText gpfNo;
    private CardView verify, cancel;


    private EditText otp_code_1, otp_code_2, otp_code_3, otp_code_4;
    private ProgressBar progressBar,progressBar1,progressBar2;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_password);
        initializations();
        showDialog();
        setUpOtpInputs();


        resendOtp.setOnClickListener(view -> {
            send();
        });

        submitBtn.setOnClickListener(view -> {
            takeData();
            if (!validateOtp(Otp) | !validatePassword(PasswordReset) | !validatePassword(PasswordConfirm)) {
                if (!validateOtp(Otp)) {
                    Toast.makeText(this, "Please enter valid otp", Toast.LENGTH_SHORT).show();
                }
                if (!validatePassword(PasswordReset)) {
                    passwordReset.setError(null);
                    passwordReset.setErrorEnabled(true);
                    passwordReset.setCounterEnabled(true);

                    passwordReset.setError("Password should be at least 6 characters long");

                    passwordReset.setError("Password too short");

                } else {
                    passwordReset.setError(null);
                    passwordReset.setErrorEnabled(false);
                    passwordReset.setCounterEnabled(false);
                }
                if (!validatePassword(PasswordConfirm)) {
                    passwordConfirm.setError(null);
                    passwordConfirm.setErrorEnabled(true);
                    passwordConfirm.setCounterEnabled(true);

                    passwordConfirm.setError("Password should be at least 6 characters long");

                    passwordConfirm.setError("Password too short");

                } else {
                    passwordConfirm.setError(null);
                    passwordConfirm.setErrorEnabled(false);
                    passwordConfirm.setCounterEnabled(false);
                }
            } else {
                if (PasswordReset.equals(PasswordConfirm)) generatePassword();
                else Toast.makeText(this, "Password not matching", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializations() {
        mobileNo = findViewById(R.id.mobileNo);
        mobNo = findViewById(R.id.mobNo);
        resendOtp = findViewById(R.id.resend_otp);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        passwordReset = findViewById(R.id.password_reset);
        passwordConfirm = findViewById(R.id.password_confirm);
        submitBtn = findViewById(R.id.submit_btn);
        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        txtSubmit = findViewById(R.id.txt_submit);

        otp_code_1 = findViewById(R.id.otp_code_1);
        otp_code_2 = findViewById(R.id.otp_code_2);
        otp_code_3 = findViewById(R.id.otp_code_3);
        otp_code_4 = findViewById(R.id.otp_code_4);
    }

    private void takeData() {
        Otp = otp_code_1.getText().toString().trim() + otp_code_2.getText().toString().trim() + otp_code_3.getText().toString().trim() + otp_code_4.getText().toString().trim();
        PasswordReset = Objects.requireNonNull(passwordReset.getEditText()).getText().toString().trim();
        PasswordConfirm = Objects.requireNonNull(passwordConfirm.getEditText()).getText().toString().trim();
    }

//    private void setDialogBox() {
//        EditText editText = new EditText(this);
//        AlertDialog.Builder mobile = new AlertDialog.Builder(this);
//        mobile.setTitle("Mobile Number");
//        mobile.setMessage("Enter your mobile number to get otp.");
//        mobile.setView(editText);
//        editText.setText(MobileNo);
//
//        mobile.setPositiveButton("Send OTP", (dialogInterface, i) -> {
//                })
//                .setNegativeButton("Cancel", (dialogInterface, i) -> {
//                    MobileNo = Objects.requireNonNull(mobileNo.getEditText()).getText().toString().trim();
//                    if (MobileNo.length() == 0) {
//                        startActivity(new Intent(this, LoginActivity.class));
//                    }
//                    dialogInterface.dismiss();
//                });
//        mobile.setCancelable(false);
//        AlertDialog dialog = mobile.create();
//        dialog.show();
//        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(view -> {
//            String number = editText.getText().toString().trim();
//            if (validateMobNo(number)) {
//                MobileNo = number;
//                send(dialog);
//            } else Toast.makeText(this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
//        });
//    }

    private boolean validateOtp(String s) {
        return s.length() == 4;
    }

    private boolean validatePassword(String s) {
        return s.length() >= 6;
    }

    private boolean send() {
        resendOtp.setVisibility(View.GONE);
        progressBar1.setVisibility(View.VISIBLE);
        OtpRequestBody requestBody = new OtpRequestBody(MobileNo);
        Call<OtpResponse> call = apiInterface.sendOtp(requestBody);
        call.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                OtpResponse model = response.body();
                if (model != null) {
                    resendOtp.setVisibility(View.VISIBLE);
                    progressBar1.setVisibility(View.GONE);
                    status = model.isSuccess();
                    if (status) {
//                        dialog.dismiss();
                        mobNo.setText("******"+MobileNo.substring(6));
                    }
                    Toast.makeText(GeneratePasswordActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    resendOtp.setVisibility(View.VISIBLE);
                    progressBar1.setVisibility(View.GONE);
                    Toast.makeText(GeneratePasswordActivity.this, "Couldn't send otp", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {
                resendOtp.setVisibility(View.VISIBLE);
                progressBar1.setVisibility(View.GONE);
                Toast.makeText(GeneratePasswordActivity.this, "Couldn't get a response.", Toast.LENGTH_SHORT).show();
            }
        });
        return status;
    }

    private void generatePassword() {
        txtSubmit.setVisibility(View.INVISIBLE);
        progressBar2.setVisibility(View.VISIBLE);
        UserRequestBody requestBody = new UserRequestBody(MobileNo, Otp, PasswordConfirm);
        Call<UserResponse> call = apiInterface.generatePassword(requestBody);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse model = response.body();
                if (model != null) {
                    txtSubmit.setVisibility(View.VISIBLE);
                    progressBar2.setVisibility(View.INVISIBLE);
                    Toast.makeText(GeneratePasswordActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GeneratePasswordActivity.this, LoginActivity.class));
                } else {
                    txtSubmit.setVisibility(View.VISIBLE);
                    progressBar2.setVisibility(View.INVISIBLE);
                    badRequestMessage(response);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                txtSubmit.setVisibility(View.VISIBLE);
                progressBar2.setVisibility(View.INVISIBLE);
                Toast.makeText(GeneratePasswordActivity.this, "Couldn't get a response.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void badRequestMessage(Response response){
        Gson gson = new GsonBuilder().create();
        BadRequestBody mError;
        try {
            mError= gson.fromJson(response.errorBody().string(),BadRequestBody.class);
            Toast.makeText(GeneratePasswordActivity.this, mError.getMessage(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(GeneratePasswordActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void setUpOtpInputs() {
        otp_code_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    otp_code_2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp_code_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().isEmpty()) {
                    otp_code_1.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    otp_code_3.requestFocus();
                } else otp_code_1.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp_code_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    otp_code_4.requestFocus();
                } else otp_code_2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp_code_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().isEmpty()) {
                    otp_code_3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void dialogInitializations() {


        AlertDialog.Builder builder;
        AlertDialog dialog;
    }

    private void init(View view){
        gpfNo = view.findViewById(R.id.gpf_number);
        verify = view.findViewById(R.id.verify);
        cancel = view.findViewById(R.id.cancel);
        progressBar = view.findViewById(R.id.progressBar);
        txtVerify = view.findViewById(R.id.txt_verify);
    }
    private void showDialog() {
        dialogInitializations();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.send_otp_popup, null);
        init(view);
        builder.setView(view).setCancelable(false);
        AlertDialog dialog = builder.create();
        setSendOtp(dialog);
        setCancel(dialog);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }

    private void loadMobileNumber() {
//        mobileNo.setText(MobileNo);
//        setMobNo();
        MobileNo = gpfNo.getText().toString().trim();
//        Toast.makeText(this, MobileNo, Toast.LENGTH_SHORT).show();
    }

    private void setSendOtp(AlertDialog dialog) {
        verify.setOnClickListener(view -> {
//            loadMobileNumber();
//                loadMobNo();
            txtVerify.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            String gpf_number = Objects.requireNonNull(gpfNo.getText()).toString().trim();
            GpfRequestBody requestBody = new GpfRequestBody(gpf_number);

            Call<GpfResponse> call = apiInterface.checkGPFNumber(requestBody);
            call.enqueue(new Callback<GpfResponse>() {
                @Override
                public void onResponse(@NonNull Call<GpfResponse> call, @NonNull Response<GpfResponse> response) {
                    GpfResponse model = response.body();
                    if(model!=null){
                        txtVerify.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        if(model.isSuccess()){
                            Toast.makeText(GeneratePasswordActivity.this, "GPF number verified", Toast.LENGTH_SHORT).show();
                            String num = model.getResult().get(0).getMobileNo();
                            MobileNo = num;
                            send();
                            dialog.dismiss();
                        }
                        else {
                            txtVerify.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(GeneratePasswordActivity.this, "Invalid GPF.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        txtVerify.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(GeneratePasswordActivity.this, "Couldn't check GPF number.", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(@NonNull Call<GpfResponse> call, Throwable t) {
                    txtVerify.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(GeneratePasswordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.wtf("gpf_response", t.toString());
                }
            });
        });
    }

    private void setCancel(AlertDialog dialog) {
        cancel.setOnClickListener(view -> {
            MobileNo = Objects.requireNonNull(mobileNo.getEditText()).getText().toString().trim();
            if (MobileNo.length() == 0) {
                super.onBackPressed();
                finish();
            }
            dialog.dismiss();
            finish();
        });
    }
}