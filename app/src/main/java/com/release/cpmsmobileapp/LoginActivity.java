package com.release.cpmsmobileapp;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.release.cpmsmobileapp.requestbody.GpfRequestBody;
import com.release.cpmsmobileapp.requestbody.LoginRequestBody;
import com.release.cpmsmobileapp.responsebody.GpfResponse;
import com.release.cpmsmobileapp.responsebody.LoginResponse;
import com.release.cpmsmobileapp.utils.HelperUtils;

import java.lang.reflect.Method;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView generateNewPass, loginBtnText;
    private EditText username, pass;
    ApiInterface apiInterface;
    CardView loginBtn;
    ProgressBar progressBar;
    RelativeLayout relativeLayout;
    private long mBackPressed;
    private final long TIME_INTERVAL = 2000;
    HelperUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializations();
        loginBtn();
        customBackButton();

        generateNewPass.setOnClickListener(view -> {
            startActivity(new Intent(this, GeneratePasswordActivity.class));
        });
    }

    private void loginBtn() {
        loginBtn.setOnClickListener(v -> {
            loginBtnText.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            String gpf_number = Objects.requireNonNull(username.getText()).toString().trim();
            GpfRequestBody requestBody = new GpfRequestBody(gpf_number);

            Call<GpfResponse> call = apiInterface.checkGPFNumber(requestBody);
            call.enqueue(new Callback<GpfResponse>() {
                @Override
                public void onResponse(@NonNull Call<GpfResponse> call, @NonNull Response<GpfResponse> response) {
                    GpfResponse model = response.body();
                    if (model != null) {
                        if (model.isSuccess()) {
                            utils.setGpfDetails(model);
                            Toast.makeText(LoginActivity.this, "GPF number verified", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid GPF.", Toast.LENGTH_SHORT).show();
                        }
                        loginFunc(gpf_number);
                    } else
                        Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(@NonNull Call<GpfResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    Log.wtf("gpf_response", t.toString());
                    loginBtnText.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        });

    }

    private void loginFunc(String gpf_number) {
        String password = pass.getText().toString().trim();
        Call<LoginResponse> call = apiInterface.login(new LoginRequestBody(gpf_number, password));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse model = response.body();
                if (model != null) {
                    Log.i("lolo", String.valueOf(model.getUser().getRange_id()));
                    utils.setAllUserDetails(model);
                    loginBtnText.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Username or Password.", Toast.LENGTH_SHORT).show();
                    loginBtnText.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                loginBtnText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void initializations() {
        generateNewPass = findViewById(R.id.textView2);
        username = findViewById(R.id.gpf_no);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        loginBtn = findViewById(R.id.login_btn);
        pass = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        loginBtnText = findViewById(R.id.loginBtnText);
        relativeLayout = findViewById(R.id.relativeLayout);
        utils = new HelperUtils(this);
    }

    private void customBackButton() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
                }

                mBackPressed = System.currentTimeMillis();
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
    }

}