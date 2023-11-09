package com.release.cpmsmobileapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.release.cpmsmobileapp.databinding.FragmentSearchCaseFragmetBinding;
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

public class SearchCaseFragment extends Fragment {

    String year, districtID, stationID, token;
    String firNo;
    EditText fir_No, station_ID, year_;
    CardView searchBtn;
    View view;
    private HashMap<String,String> distNameId,psNameId;

    private ArrayList<String> distDropdown,psDropdown;

    FragmentSearchCaseFragmetBinding binding;

    private Spinner distSpinner,psSpinner;

    private List<DistrictResponse> distList;
    private List<PsResponse> psList;
    private ApiInterface apiInterface;

    private HelperUtils utils;



    public SearchCaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchCaseFragmetBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        initialisations();
        loadToken();
        setDistSpinnerAdapter();
        setPsSpinnerAdapter();
        getDistrictList();
        searchBtn.setOnClickListener(v -> searchFunc());

        return view;
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
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
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
                    Toast.makeText(requireContext(), "Could not get details for this case", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(model.size()!=0) {
                    SearchCaseResponse caseResponse = response.body().get(0);
                    Log.wtf("response", String.valueOf(response.code()));
                    binding.resultCard.setVisibility(View.VISIBLE);
                    binding.firNo.setText(caseResponse.getFIR_no());
                    binding.year.setText(caseResponse.getYear());
                    binding.regDate.setText(caseResponse.getReg_date());
                    binding.actSection.setText(caseResponse.getAct_section());
                    binding.caseDetail.setText(caseResponse.getCase_detail());
                    binding.remarks.setText(caseResponse.getRemarks());
                    binding.nodalOfficer.setText(caseResponse.getNodal_officer());
                    binding.caseStatusName.setText(caseResponse.getCaseStatus_name());
                    binding.PsName.setText(caseResponse.getPs_name());
                    binding.PsNameHi.setText(caseResponse.getPs_name_hi());
                    binding.DistrictName.setText(caseResponse.getDistrict_name());
                    binding.DistrictNameHi.setText(caseResponse.getDistrict_name_hi());
                    binding.MajorHeadName.setText(caseResponse.getMajorHead_name());
                    binding.MajorHeadNameHi.setText(caseResponse.getMajorHead_name_hi());
                    binding.MinorHeadName.setText(caseResponse.getMinorHead_name());
                    binding.MinorHeadNameHi.setText(caseResponse.getMinorHead_name_hi());
                }else {
                    binding.resultCard.setVisibility(View.GONE);
                    Toast.makeText(requireContext(),"No details found",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SearchCaseResponse>> call, Throwable t) {
                Log.wtf("response", t.getMessage());
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
                        Toast.makeText(requireContext(), "District list is null", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e) {
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }


            }
            @Override
            public void onFailure(Call<List<DistrictResponse>> call, Throwable t) {
                Toast.makeText(requireContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDistSpinnerAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item_layout,distDropdown);
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
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PsResponse>> call, Throwable t) {
                Toast.makeText(requireContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setPsSpinnerAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_item_layout,psDropdown);
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
        distNameId = new HashMap<>();
        distDropdown = new ArrayList<>();
        distDropdown.add("Select district");
        distNameId.put("Select district","");
        psNameId = new HashMap<>();
        psDropdown = new ArrayList<>();
        psDropdown.add("Select police station");
        psNameId.put("Select police station","");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        utils = new HelperUtils(requireContext());
        token = utils.getToken();

    }

    private void loadToken() {
        if (token.length() == 0) {
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
        }
    }


}