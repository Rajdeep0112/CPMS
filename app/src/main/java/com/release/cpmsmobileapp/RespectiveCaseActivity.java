package com.release.cpmsmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.release.cpmsmobileapp.adapters.AssignedCaseAdapter;
import com.release.cpmsmobileapp.adapters.RespectiveCaseAccusedAdapter;
import com.release.cpmsmobileapp.adapters.RespectiveCaseBailerAdapter;
import com.release.cpmsmobileapp.adapters.RespectiveCaseHearingDetailsAdapter;
import com.release.cpmsmobileapp.adapters.RespectiveCaseProsecutorAdapter;
import com.release.cpmsmobileapp.adapters.RespectiveCaseWitnessAdapter;
import com.release.cpmsmobileapp.databinding.ActivityRespectiveCaseBinding;
import com.release.cpmsmobileapp.databinding.RespectiveCaseDetailsItemLayoutBinding;
import com.release.cpmsmobileapp.requestbody.AssignedCaseRequestBody;
import com.release.cpmsmobileapp.requestbody.ByCaseDetailID;
import com.release.cpmsmobileapp.responsebody.AccusedOfRespectiveCase;
import com.release.cpmsmobileapp.responsebody.BailersOfRespectiveCase;
import com.release.cpmsmobileapp.responsebody.HearingListResponse;
import com.release.cpmsmobileapp.responsebody.HearingsOfRespectiveCase;
import com.release.cpmsmobileapp.responsebody.PublicProsecutorOfRespectiveCase;
import com.release.cpmsmobileapp.responsebody.SearchCaseResponse;
import com.release.cpmsmobileapp.responsebody.WitnessOfRespectiveCase;
import com.release.cpmsmobileapp.utils.HelperUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RespectiveCaseActivity extends AppCompatActivity {

    private String token, gpfNo;

    private String caseDetailID;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private SearchCaseResponse caseDetails;

    private RecyclerView hearingRecyclerView, accusedRecyclerView, witnessRecyclerView, prosecutorRecyclerView, bailerRecyclerView;

    private RespectiveCaseHearingDetailsAdapter hearingAdapter;

    private RespectiveCaseAccusedAdapter accusedAdapter;

    private RespectiveCaseWitnessAdapter witnessAdapter;

    private RespectiveCaseBailerAdapter bailerAdapter;

    private RespectiveCaseProsecutorAdapter prosecutorAdapter;
    private Button caseDetailBtn, hearingBtn, accusedBtn, witnessBtn, prosecutorBtn, bailerBtn;

    private LinearLayout respectiveCaseDataLv, caseDetailLayout, hearingLayout, accusedLayout, witnessLayout, prosecutorLayout, bailerLayout;


    private ApiInterface apiInterface;

    private HelperUtils utils;
    private ActivityRespectiveCaseBinding binding;
    private View view;
    private HorizontalScrollView hsv1,hsv2,hsv3,hsv4,hsv5;

    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRespectiveCaseBinding.inflate(getLayoutInflater());
        view=binding.getRoot();
        setContentView(view);

        getDetails();
        initialisation();
        loadToken();
        setCaseDetails();
        setRecyclerView();
        getRespectiveCaseDetails();
        buttonFunction();
//        configureToolbar();
    }




//    private void configureToolbar() {
//        toolbar.setNavigationIcon(R.drawable.baseline_menu_24);
//        toolbar.setNavigationOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
//    }

//    private void adapterWork(List<HearingsOfRespectiveCase> hearings){
//        hearingAdapter.setRespectiveCaseHearingListAdpater(hearings,RespectiveCaseActivity.this);
//    }



    private void initialisation() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        utils = new HelperUtils(RespectiveCaseActivity.this);
        drawerLayout = findViewById(R.id.drawable_layout_respective_case);
        progressBar = findViewById(R.id.respective_case_progressbar);
        respectiveCaseDataLv = findViewById(R.id.respective_case_data_lv);
//        toolbar = findViewById(R.id.toolbar_respective_case);
    }

    private void getDetails(){
        Intent intent = getIntent();
        caseDetails = (SearchCaseResponse) intent.getSerializableExtra("CaseDetails");
        caseDetailID = caseDetails.getCase_detail_id().toString();
    }

    private void getRespectiveCaseDetails(){
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + token);

        getRespectiveHearingDetails(map);
        getRespectiveAccusedDetails(map);
        getRespectiveWitnessesDetails(map);
        getRespectiveProsecutorsDetails(map);
        getRespectiveBailersDetails(map);
    }

    private void getRespectiveHearingDetails(Map<String,String> map){
        Call<List<HearingsOfRespectiveCase>> call = apiInterface.getHearingOfRespectiveCase(map, new ByCaseDetailID(caseDetailID));
        call.enqueue(new Callback<List<HearingsOfRespectiveCase>>() {
            @Override
            public void onResponse(Call<List<HearingsOfRespectiveCase>> call, Response<List<HearingsOfRespectiveCase>> response) {
                List<HearingsOfRespectiveCase> list = response.body();

                binding.hearingBtn.setOnClickListener(view1 -> {
                    if((response.body() == null || list == null)) {
                        Toast.makeText(RespectiveCaseActivity.this, "No Hearings", Toast.LENGTH_SHORT).show();
                    }
                    else if(list.size()==0){
                        Toast.makeText(RespectiveCaseActivity.this, "No Hearings", Toast.LENGTH_SHORT).show();
                    }
                    if(binding.hearingLayout.getVisibility() == View.GONE){
                        binding.hearingLayout.setVisibility(View.VISIBLE);
                    }else {
                        binding.hearingLayout.setVisibility(View.GONE);
                    }
                });

                if(response.body() == null || list == null) {
                    binding.hsv1.setVisibility(View.GONE);
                    return;

                }
                if(list.size()!=0) {
                    hearingAdapter.setRespectiveCaseHearingListAdpater(list,RespectiveCaseActivity.this);
                    binding.hsv1.setVisibility(View.VISIBLE);
                }else {
                    hearingAdapter.setRespectiveCaseHearingListAdpater(list,RespectiveCaseActivity.this);
                    binding.hsv1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<HearingsOfRespectiveCase>> call, Throwable t) {

            }
        });
    }
    private void getRespectiveAccusedDetails(Map<String,String> map){
        Call<List<AccusedOfRespectiveCase>> call = apiInterface.getAccusedOfRespectiveCase(map, new ByCaseDetailID(caseDetailID));
        call.enqueue(new Callback<List<AccusedOfRespectiveCase>>() {
            @Override
            public void onResponse(Call<List<AccusedOfRespectiveCase>> call, Response<List<AccusedOfRespectiveCase>> response) {
                List<AccusedOfRespectiveCase> list = response.body();

                binding.accusedBtn.setOnClickListener(view1 -> {
                    if((response.body() == null || list == null)) {
                        Toast.makeText(RespectiveCaseActivity.this, "No Accused", Toast.LENGTH_SHORT).show();
                    }
                    else if(list.size()==0){
                        Toast.makeText(RespectiveCaseActivity.this, "No Accused", Toast.LENGTH_SHORT).show();
                    }
                    if(binding.accusedLayout.getVisibility() == View.GONE){
                        binding.accusedLayout.setVisibility(View.VISIBLE);
                    }else {
                        binding.accusedLayout.setVisibility(View.GONE);
                    }
                });

                if(response.body() == null || list == null) {
                    binding.hsv2.setVisibility(View.GONE);
                    return;

                }
                if(list.size()!=0) {
                    accusedAdapter.setAccusedOfRespectiveCase(RespectiveCaseActivity.this, list);
                    binding.hsv2.setVisibility(View.VISIBLE);
                }else {
                    accusedAdapter.setAccusedOfRespectiveCase(RespectiveCaseActivity.this, list);
                    binding.hsv2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<AccusedOfRespectiveCase>> call, Throwable t) {

            }
        });
    }
    private void getRespectiveWitnessesDetails(Map<String,String> map){
        Call<List<WitnessOfRespectiveCase>> call = apiInterface.getWitnessOfRespectiveCase(map, new ByCaseDetailID(caseDetailID));
        call.enqueue(new Callback<List<WitnessOfRespectiveCase>>() {
            @Override
            public void onResponse(Call<List<WitnessOfRespectiveCase>> call, Response<List<WitnessOfRespectiveCase>> response) {
                List<WitnessOfRespectiveCase> list = response.body();

                binding.witnessBtn.setOnClickListener(view1 -> {
                    if((response.body() == null || list == null)) {
                        Toast.makeText(RespectiveCaseActivity.this, "No Witnesses", Toast.LENGTH_SHORT).show();
                    }
                    else if(list.size()==0){
                        Toast.makeText(RespectiveCaseActivity.this, "No Witnesses", Toast.LENGTH_SHORT).show();
                    }
                    if(binding.witnessesLayout.getVisibility() == View.GONE){
                        binding.witnessesLayout.setVisibility(View.VISIBLE);
                    }else {
                        binding.witnessesLayout.setVisibility(View.GONE);
                    }
                });

                if(response.body() == null || list == null) {
                    binding.hsv3.setVisibility(View.GONE);

                    return;

                }
                if(list.size()!=0) {
                    witnessAdapter.setRespectiveCaseWitness(RespectiveCaseActivity.this, list);
                    binding.hsv3.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    respectiveCaseDataLv.setVisibility(View.VISIBLE);
                }else {
                    witnessAdapter.setRespectiveCaseWitness(RespectiveCaseActivity.this, list);
                    binding.hsv3.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<WitnessOfRespectiveCase>> call, Throwable t) {

            }
        });
    }
    private void getRespectiveProsecutorsDetails(Map<String,String> map){
        Call<List<PublicProsecutorOfRespectiveCase>> call = apiInterface.getPublicProsecutorOfRespectiveCase(map, new ByCaseDetailID(caseDetailID));
        call.enqueue(new Callback<List<PublicProsecutorOfRespectiveCase>>() {
            @Override
            public void onResponse(Call<List<PublicProsecutorOfRespectiveCase>> call, Response<List<PublicProsecutorOfRespectiveCase>> response) {
                List<PublicProsecutorOfRespectiveCase> list = response.body();

                binding.prosecutorBtn.setOnClickListener(view1 -> {
                    if((response.body() == null || list == null)) {
                        Toast.makeText(RespectiveCaseActivity.this, "No Prosecutors", Toast.LENGTH_SHORT).show();
                    }
                    else if(list.size()==0){
                        Toast.makeText(RespectiveCaseActivity.this, "No Prosecutors", Toast.LENGTH_SHORT).show();
                    }
                    if(binding.prosecutorLayout.getVisibility() == View.GONE){
                        binding.prosecutorLayout.setVisibility(View.VISIBLE);
                    }else {
                        binding.prosecutorLayout.setVisibility(View.GONE);
                    }
                });

                if(response.body() == null || list == null) {
                    binding.hsv4.setVisibility(View.GONE);
                    return;

                }
                if(list.size()!=0) {
                    prosecutorAdapter.setProsecutorOfRespectiveCases(RespectiveCaseActivity.this, list);
                    binding.hsv4.setVisibility(View.VISIBLE);
                }else {
                    prosecutorAdapter.setProsecutorOfRespectiveCases(RespectiveCaseActivity.this, list);
                    binding.hsv4.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<PublicProsecutorOfRespectiveCase>> call, Throwable t) {

            }
        });
    }
    private void getRespectiveBailersDetails(Map<String,String> map){
        Call<List<BailersOfRespectiveCase>> call = apiInterface.getBailerOfRespectiveCase(map, new ByCaseDetailID(caseDetailID));
        call.enqueue(new Callback<List<BailersOfRespectiveCase>>() {
            @Override
            public void onResponse(Call<List<BailersOfRespectiveCase>> call, Response<List<BailersOfRespectiveCase>> response) {
                List<BailersOfRespectiveCase> list = response.body();

                binding.bailerBtn.setOnClickListener(view1 -> {
                    if((response.body() == null || list == null)) {
                        Toast.makeText(RespectiveCaseActivity.this, "No Bailers", Toast.LENGTH_SHORT).show();
                    }
                    else if(list.size()==0){
                        Toast.makeText(RespectiveCaseActivity.this, "No Bailers", Toast.LENGTH_SHORT).show();
                    }
                    if(binding.bailerLayout.getVisibility() == View.GONE){
                        binding.bailerLayout.setVisibility(View.VISIBLE);
                    }else {
                        binding.bailerLayout.setVisibility(View.GONE);
                    }
                });

                if(response.body() == null || list == null) {
                    binding.hsv5.setVisibility(View.GONE);
                    return;

                }
                if(list.size()!=0) {
                    bailerAdapter.setRespectiveCaseBailer(RespectiveCaseActivity.this, list);
                    binding.hsv5.setVisibility(View.VISIBLE);
                }else {
                    bailerAdapter.setRespectiveCaseBailer(RespectiveCaseActivity.this, list);
                    binding.hsv5.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<BailersOfRespectiveCase>> call, Throwable t) {

            }
        });
    }

    private void btnClickFunc(Button button, LinearLayout layout){
        button.setOnClickListener(v -> {
            if(layout.getVisibility() == View.GONE){
                layout.setVisibility(View.VISIBLE);
            }else {
                layout.setVisibility(View.GONE);
            }
        });
    }
    private void buttonFunction()
    {
        btnClickFunc(binding.caseDetails.moreInfoBtn,binding.caseDetails.moreInfo);
        btnClickFunc(binding.caseDetailBtn, binding.caseDetails.getRoot());

    }

    private void loadToken() {
        token = utils.getToken();
        if (token.length() == 0) {
            Intent intent = new Intent(RespectiveCaseActivity.this, LoginActivity.class);
            startActivity(intent);
        }else {
            gpfNo = utils.getGpfNo();
        }
    }

    private void setRecyclerView ()
    {
        binding.hearingLayoutRv.setLayoutManager(new LinearLayoutManager(RespectiveCaseActivity.this));
        hearingAdapter = new RespectiveCaseHearingDetailsAdapter();
        binding.hearingLayoutRv.setAdapter(hearingAdapter);

        binding.accussedLayoutRv.setLayoutManager(new LinearLayoutManager(RespectiveCaseActivity.this));
        accusedAdapter = new RespectiveCaseAccusedAdapter();
        binding.accussedLayoutRv.setAdapter(accusedAdapter);

        binding.witnessesLayoutRv.setLayoutManager(new LinearLayoutManager(RespectiveCaseActivity.this));
        witnessAdapter = new RespectiveCaseWitnessAdapter();
        binding.witnessesLayoutRv.setAdapter(witnessAdapter);

        binding.prosecutorLayoutRv.setLayoutManager(new LinearLayoutManager(RespectiveCaseActivity.this));
        prosecutorAdapter = new RespectiveCaseProsecutorAdapter();
        binding.prosecutorLayoutRv.setAdapter(prosecutorAdapter);

        binding.bailerLayoutRv.setLayoutManager(new LinearLayoutManager(RespectiveCaseActivity.this));
        bailerAdapter = new RespectiveCaseBailerAdapter();
        binding.bailerLayoutRv.setAdapter(bailerAdapter);
    }

    private void setCaseDetails(){
        binding.caseDetails.firNo.setText(""+caseDetails.getFIR_no());
        binding.caseDetails.year.setText(caseDetails.getYear());
        binding.caseDetails.regDate.setText(caseDetails.getReg_date());
        binding.caseDetails.caseDetailId.setText(""+caseDetails.getCase_detail_id());
        binding.caseDetails.actSection.setText(caseDetails.getAct_section());
        binding.caseDetails.caseDetail.setText(caseDetails.getCase_detail());
        binding.caseDetails.remarks.setText(caseDetails.getRemarks());
        binding.caseDetails.nodalOfficer.setText(caseDetails.getNodal_officer());
        binding.caseDetails.caseStatusName.setText(caseDetails.getCaseStatus_name());
        binding.caseDetails.PsName.setText(caseDetails.getPs_name());
        binding.caseDetails.PsNameHi.setText(caseDetails.getPs_name_hi());
        binding.caseDetails.DistrictName.setText(caseDetails.getDistrict_name());
        binding.caseDetails.DistrictNameHi.setText(caseDetails.getDistrict_name_hi());
        binding.caseDetails.MajorHeadName.setText(caseDetails.getMajorHead_name());
        binding.caseDetails.MajorHeadNameHi.setText(caseDetails.getMajorHead_name_hi());
        binding.caseDetails.MinorHeadName.setText(caseDetails.getMinorHead_name());
        binding.caseDetails.MinorHeadNameHi.setText(caseDetails.getMinorHead_name_hi());
    }

}