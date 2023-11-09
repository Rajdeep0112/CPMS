package com.release.cpmsmobileapp;

import com.release.cpmsmobileapp.requestbody.AssignedCaseRequestBody;
import com.release.cpmsmobileapp.requestbody.ByCaseDetailID;
import com.release.cpmsmobileapp.requestbody.ByDateRequestBody;
import com.release.cpmsmobileapp.requestbody.GpfRequestBody;
import com.release.cpmsmobileapp.requestbody.LoginRequestBody;
import com.release.cpmsmobileapp.requestbody.OtpRequestBody;
import com.release.cpmsmobileapp.requestbody.RefreshTokenRequestBody;
import com.release.cpmsmobileapp.requestbody.SearchCaseRequestBody;
import com.release.cpmsmobileapp.requestbody.UserRequestBody;
import com.release.cpmsmobileapp.responsebody.AccusedOfRespectiveCase;
import com.release.cpmsmobileapp.responsebody.BailersOfRespectiveCase;
import com.release.cpmsmobileapp.responsebody.DistrictResponse;
import com.release.cpmsmobileapp.responsebody.GpfResponse;
import com.release.cpmsmobileapp.responsebody.HearingListResponse;
import com.release.cpmsmobileapp.responsebody.HearingsOfRespectiveCase;
import com.release.cpmsmobileapp.responsebody.LoginResponse;
import com.release.cpmsmobileapp.responsebody.NoticesResponse;
import com.release.cpmsmobileapp.responsebody.OtpResponse;
import com.release.cpmsmobileapp.responsebody.PsResponse;
import com.release.cpmsmobileapp.responsebody.PublicProsecutorOfRespectiveCase;
import com.release.cpmsmobileapp.responsebody.SearchCaseResponse;
import com.release.cpmsmobileapp.responsebody.UserResponse;
import com.release.cpmsmobileapp.responsebody.WitnessOfRespectiveCase;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/login")
    Call<LoginResponse> login(@Body LoginRequestBody requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/send_sandesh_otp")
    Call<OtpResponse> sendOtp(@Body OtpRequestBody requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/otp_valid_register")
    Call<UserResponse> generatePassword(@Body UserRequestBody requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/gpf_check")
    Call<GpfResponse> checkGPFNumber(@Body GpfRequestBody requestBody);

    @Headers("Content-Type: application/json")
    @GET("api/ps/{districtId}")
    Call<List<PsResponse>> getPsList(@HeaderMap Map<String, String> map, @Path("districtId") String districtId);

    @Headers("Content-Type: application/json")
    @GET("api/districts/")
    Call<List<DistrictResponse>> getDistrictList(@HeaderMap Map<String, String> map);

    @Headers("Content-Type: application/json")
    @POST("api/get_case")
    Call<List<SearchCaseResponse>> getCaseDetails(@HeaderMap Map<String, String> map, @Body SearchCaseRequestBody requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/get_io_case")
    Call<List<SearchCaseResponse>> getAssignedCases(@HeaderMap Map<String, String> map, @Body AssignedCaseRequestBody requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/get_hearing")
    Call<List<HearingListResponse>> getHearingList(@HeaderMap Map<String, String> map, @Body AssignedCaseRequestBody requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/get_notice")
    Call<List<NoticesResponse>> getAllNotices(@HeaderMap Map<String, String> map, @Body AssignedCaseRequestBody requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/get_hearing_by_date")
    Call<List<HearingListResponse>> getHearingsByDate(@HeaderMap Map<String, String> map, @Body ByDateRequestBody requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/get_notice_by_date")
    Call<List<NoticesResponse>> getNoticesByDate(@HeaderMap Map<String, String> map, @Body ByDateRequestBody requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/refresh_token")
    Call<LoginResponse> getRefreshToken(@Body RefreshTokenRequestBody requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/get_case_hearing")
    Call<List<HearingsOfRespectiveCase>> getHearingOfRespectiveCase (@HeaderMap Map<String, String> map, @Body ByCaseDetailID requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/get_case_accused")
    Call<List<AccusedOfRespectiveCase>> getAccusedOfRespectiveCase(@HeaderMap Map<String, String> map, @Body ByCaseDetailID requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/get_case_witness")
    Call<List<WitnessOfRespectiveCase>> getWitnessOfRespectiveCase(@HeaderMap Map<String, String> map, @Body ByCaseDetailID requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/get_case_public_prosecutor")
    Call<List<PublicProsecutorOfRespectiveCase>> getPublicProsecutorOfRespectiveCase(@HeaderMap Map<String, String> map, @Body ByCaseDetailID requestBody);

    @Headers("Content-Type: application/json")
    @POST("api/get_case_bailer")
    Call<List<BailersOfRespectiveCase>> getBailerOfRespectiveCase(@HeaderMap Map<String, String> map, @Body ByCaseDetailID requestBody);



}
