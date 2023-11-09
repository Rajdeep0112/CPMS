package com.release.cpmsmobileapp;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    static Retrofit getClient() {

        return new Retrofit.Builder()
                .baseUrl("https://jharpolapi.jhpolice.gov.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
