package com.release.cpmsmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.release.cpmsmobileapp.databinding.ActivitySearchCaseBinding;
import com.release.cpmsmobileapp.requestbody.SearchCaseRequestBody;
import com.release.cpmsmobileapp.responsebody.DistrictResponse;
import com.release.cpmsmobileapp.responsebody.PsResponse;
import com.release.cpmsmobileapp.responsebody.SearchCaseResponse;
import com.release.cpmsmobileapp.utils.HelperUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCaseActivity extends AppCompatActivity {

    String year, districtID, stationID, token, gpfNo;
    String firNo;
    EditText fir_No, station_ID, year_;
    Toolbar toolbar;
    CardView searchBtn;
    View view;
    TextView txtSearch;
    private HashMap<String,String> distNameId,psNameId;

    private ArrayList<String> distDropdown,psDropdown;
    ActivitySearchCaseBinding binding;

    private Spinner distSpinner,psSpinner;

    private List<DistrictResponse> distList;
    private List<PsResponse> psList;
    private ApiInterface apiInterface;

    private HelperUtils utils;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchCaseBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        initialisations();
        configureToolbar();
        loadToken();
        setDistSpinnerAdapter();
        setPsSpinnerAdapter();
        getDistrictList();
        searchBtn.setOnClickListener(v -> searchFunc());
    }

    private void configureToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Search Case Details");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    private void searchFunc() {
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + token);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        firNo = binding.firNoEdittext.getText().toString();
        year = binding.yearEdittext.getText().toString();

//        districtID = binding.districtIdEdittext.getText().toString();
//        stationID = binding.psIdEdittext.getText().toString();

        if (firNo.length() == 0 | year.length() == 0 | districtID.length() == 0 | stationID.length() == 0) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }else {
            txtSearch.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }

        SearchCaseRequestBody requestBody = new SearchCaseRequestBody(firNo, year, districtID, stationID);

        Call<List<SearchCaseResponse>> call = apiInterface.getCaseDetails(map, requestBody);

        call.enqueue(new Callback<List<SearchCaseResponse>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<SearchCaseResponse>> call, Response<List<SearchCaseResponse>> response) {
                List<SearchCaseResponse> model = response.body();
                Log.wtf("response", String.valueOf(response));
                if (response.body() == null) {
                    txtSearch.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(SearchCaseActivity.this, "Could not get details for this case", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(model.size()!=0) {
                    txtSearch.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    SearchCaseResponse caseResponse = response.body().get(0);
                    Log.wtf("response", String.valueOf(response.code()));
//                    binding.resultCard.setVisibility(View.VISIBLE);
//                    binding.firNo.setText(Integer.toString(caseResponse.getFIR_no()));
//                    binding.year.setText(caseResponse.getYear());
//                    binding.regDate.setText(caseResponse.getReg_date());
//                    binding.caseDetailId.setText(Integer.toString(caseResponse.getCase_detail_id()));
//                    binding.actSection.setText(caseResponse.getAct_section());
//                    binding.caseDetail.setText(caseResponse.getCase_detail());
//                    binding.remarks.setText(caseResponse.getRemarks());
//                    binding.nodalOfficer.setText(caseResponse.getNodal_officer());
//                    binding.caseStatusName.setText(caseResponse.getCaseStatus_name());
//                    binding.PsName.setText(caseResponse.getPs_name());
//                    binding.PsNameHi.setText(caseResponse.getPs_name_hi());
//                    binding.DistrictName.setText(caseResponse.getDistrict_name());
//                    binding.DistrictNameHi.setText(caseResponse.getDistrict_name_hi());
//                    binding.MajorHeadName.setText(caseResponse.getMajorHead_name());
//                    binding.MajorHeadNameHi.setText(caseResponse.getMajorHead_name_hi());
//                    binding.MinorHeadName.setText(caseResponse.getMinorHead_name());
//                    binding.MinorHeadNameHi.setText(caseResponse.getMinorHead_name_hi());
                    Intent intent=new Intent(SearchCaseActivity.this,RespectiveCaseActivity.class);
                    intent.putExtra("CaseDetails",caseResponse);
                    startActivity(intent);
                }else {
                    txtSearch.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
//                    binding.resultCard.setVisibility(View.GONE);
                    Toast.makeText(SearchCaseActivity.this,"No details found",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SearchCaseResponse>> call, Throwable t) {
                Log.wtf("response", t.getMessage());
                txtSearch.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void getDistrictList(){
        distNameId = new HashMap<>();
        distDropdown = new ArrayList<>();
        distDropdown.add("Select district");
        distNameId.put("Select district","");
        setDistSpinnerAdapter();

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + token);
        Call<List<DistrictResponse>> call = apiInterface.getDistrictList(map);
        call.enqueue(new Callback<List<DistrictResponse>>() {
            @Override
            public void onResponse(Call<List<DistrictResponse>> call, Response<List<DistrictResponse>> response) {
                distList = response.body();
                try {
                    if(distList != null) {
                        for(DistrictResponse districtResponse : distList) {
                            String district = districtResponse.getName()+" ("+districtResponse.getName_hi()+")";
                            distNameId.put(district,districtResponse.getId().toString());
                            distDropdown.add(district);
                        }
                        setDistSpinnerAdapter();

                    }else {
                        Toast.makeText(SearchCaseActivity.this, "District list is null", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e) {
                    Toast.makeText(SearchCaseActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }


            }
            @Override
            public void onFailure(Call<List<DistrictResponse>> call, Throwable t) {
                Toast.makeText(SearchCaseActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDistSpinnerAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchCaseActivity.this, R.layout.drop_down_item_layout,distDropdown);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distSpinner.setAdapter(adapter);
        distSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String district = adapterView.getItemAtPosition(i).toString();
                districtID = distNameId.get(district);
                if(districtID.length()!=0) getPsList(districtID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                districtID = "";
            }
        });
    }

    private void getPsList(String district_id){
        psNameId = new HashMap<>();
        psDropdown = new ArrayList<>();
        psDropdown.add("Select police station");
        psNameId.put("Select police station","");
        setPsSpinnerAdapter();

        Map<String,String> map = new HashMap<>();
        map.put("Authorization","Bearer "+token);
        Call<List<PsResponse>> call = apiInterface.getPsList(map,district_id);
        call.enqueue(new Callback<List<PsResponse>>() {
            @Override
            public void onResponse(Call<List<PsResponse>> call, Response<List<PsResponse>> response) {
                psList = response.body();
                try {
                    if (psList != null) {
                        for(PsResponse psResponse : psList){
                            String ps = psResponse.getName()+" ("+psResponse.getName_hi()+")";
                            psNameId.put(ps,psResponse.getId().toString());
                            psDropdown.add(ps);
                        }
                        setPsSpinnerAdapter();

                    } else {
                        Toast.makeText(SearchCaseActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(SearchCaseActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PsResponse>> call, Throwable t) {
                Toast.makeText(SearchCaseActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setPsSpinnerAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchCaseActivity.this, R.layout.drop_down_item_layout,psDropdown);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        psSpinner.setAdapter(adapter);
        psSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String ps = adapterView.getItemAtPosition(i).toString();
                stationID = psNameId.get(ps);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                stationID = "";
            }
        });
    }


    private void initialisations() {
        fir_No = view.findViewById(R.id.fir_no_edittext);
//        district_ID = view.findViewById(R.id.district_id_edittext);
//        station_ID = view.findViewById(R.id.ps_id_edittext);
        year_ = view.findViewById(R.id.year_edittext);
        searchBtn = view.findViewById(R.id.search_case_btn);

        distSpinner = view.findViewById(R.id.dist_spinner);
        psSpinner = view.findViewById(R.id.ps_spinner);
        txtSearch = view.findViewById(R.id.txt_search);
        progressBar = view.findViewById(R.id.progressBar);
        distNameId = new HashMap<>();
        distDropdown = new ArrayList<>();
        distDropdown.add("Select district");
        distNameId.put("Select district","");
        psNameId = new HashMap<>();
        psDropdown = new ArrayList<>();
        psDropdown.add("Select police station");
        psNameId.put("Select police station","");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        utils = new HelperUtils(SearchCaseActivity.this);
    }

    private void loadToken() {
        token = utils.getToken();
        if (token.length() == 0) {
            Intent intent = new Intent(SearchCaseActivity.this, LoginActivity.class);
            startActivity(intent);
        }else {
            gpfNo = utils.getGpfNo();
        }
    }
}