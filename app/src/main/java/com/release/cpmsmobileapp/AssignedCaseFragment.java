package com.release.cpmsmobileapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.release.cpmsmobileapp.adapters.AssignedCaseAdapter;
import com.release.cpmsmobileapp.cases.CasesFilter;
import com.release.cpmsmobileapp.cases.CasesUtils;
import com.release.cpmsmobileapp.databinding.FragmentAssignedCaseBinding;
import com.release.cpmsmobileapp.requestbody.AssignedCaseRequestBody;
import com.release.cpmsmobileapp.responsebody.SearchCaseResponse;
import com.release.cpmsmobileapp.utils.HelperUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignedCaseFragment extends Fragment {

    String token, gpfNo;
    View view;
    private RecyclerView recyclerView;
    private SectionedRecyclerViewAdapter sectionAdapter;
    FragmentAssignedCaseBinding binding;
    private ApiInterface apiInterface;
    private HelperUtils utils;
    LinearLayout noCasesError;
    private FrameLayout progressBar;
    private HorizontalScrollView hsv;
    private List<SearchCaseResponse> list=new ArrayList<>();
    private List<String> header;
    private List<List<SearchCaseResponse>> dividedList;
    private CasesUtils casesUtils;
    private CasesFilter filterObj;

    private TextInputEditText startDate, endDate;
    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAssignedCaseBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        initializations();
        loadToken();
        setRecyclerView();
        getAssignedCase();
        filterList();
        pickDate(binding.startDateBox);
        pickDate(binding.endDateBox);
        setDate(binding.endDateBox);
        return view;
    }

    private void filterList() {

        binding.filterBtn.setOnClickListener(view1 -> {
            if (binding.filterCard.getVisibility() == View.VISIBLE)
                binding.filterCard.setVisibility(View.GONE);
            else
                binding.filterCard.setVisibility(View.VISIBLE);
        });

        binding.firNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterObj.setFIR_no(String.valueOf(charSequence));
                adapterWork(casesUtils.updateList(list, filterObj));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.caseDetailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterObj.setCase_detail_id(String.valueOf(charSequence));
                adapterWork(casesUtils.updateList(list, filterObj));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.actSection.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterObj.setAct_section(String.valueOf(charSequence));
                adapterWork(casesUtils.updateList(list, filterObj));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.psName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterObj.setPs_name(String.valueOf(charSequence));
                adapterWork(casesUtils.updateList(list, filterObj));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.startDateBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterObj.setStart_date(String.valueOf(charSequence));
                adapterWork(casesUtils.updateList(list, filterObj));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.endDateBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterObj.setEnd_date(String.valueOf(charSequence));
                adapterWork(casesUtils.updateList(list, filterObj));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.close.setOnClickListener(view1 -> {
            if (binding.filterCard.getVisibility() == View.VISIBLE)
                binding.filterCard.setVisibility(View.GONE);
            else
                binding.filterCard.setVisibility(View.VISIBLE);
        });
    }

    @SuppressLint("SetTextI18n")
    private void setDate(TextView tv) {
        String yyyy, mm, dd;
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        yyyy = String.valueOf(year);
        mm = month < 10 ? "0" + month : String.valueOf(month);
        dd = day < 10 ? "0" + day : String.valueOf(day);
        changeDate(yyyy, mm, dd, tv);
    }

    @SuppressLint("SetTextI18n")
    private void pickDate(TextView tv) {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        tv.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String y = String.valueOf(year1);
                        String m = (monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : String.valueOf(monthOfYear + 1);
                        String d = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                        changeDate(y, m, d, tv);
                    }, year, month, day);
            datePickerDialog.show();
        });
    }

    @SuppressLint("SetTextI18n")
    private void changeDate(String yyyy, String mm, String dd, TextView tv) {
        tv.setText(dd + "/" + mm + "/" + yyyy);
        filterObj.setEnd_date(dd + "/" + mm + "/" + yyyy);
    }


    private void initializations() {
        recyclerView = view.findViewById(R.id.assigned_case_rv);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        utils = new HelperUtils(requireContext());
        noCasesError = view.findViewById(R.id.noCasesContainer);
        progressBar = view.findViewById(R.id.progressBar);
        hsv = view.findViewById(R.id.hsv);
        filterObj = new CasesFilter("", "", "", "", "", "");
        casesUtils = new CasesUtils(getContext());
    }

    private void getAssignedCase() {
        gpfNo = "123";
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + token);
        Call<List<SearchCaseResponse>> call = apiInterface.getAssignedCases(map, new AssignedCaseRequestBody(gpfNo));
        call.enqueue(new Callback<List<SearchCaseResponse>>() {
            @Override
            public void onResponse(Call<List<SearchCaseResponse>> call, Response<List<SearchCaseResponse>> response) {
                list = response.body();
                if (list == null || response.body() == null) {
                    progressBar.setVisibility(View.GONE);
                    hsv.setVisibility(View.GONE);
                    toastShort("Could not get cases\n\terror code : " + response.code(), getContext());
                    noCasesError.setVisibility(View.VISIBLE);
                    return;
                }
                progressBar.setVisibility(View.GONE);
                if (list.size() != 0) {
//                    list = casesUtils.populateList(list);
                    adapterWork(list);
                    noCasesError.setVisibility(View.GONE);
                    hsv.setVisibility(View.VISIBLE);
                } else {
                    adapterWork(list);
                    toastShort("No cases assigned", getContext());
                    noCasesError.setVisibility(View.VISIBLE);
                    hsv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<SearchCaseResponse>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                toastShort(t.getMessage(), getContext());
                Log.wtf("cases error", t.getMessage());
                noCasesError.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sectionAdapter = new SectionedRecyclerViewAdapter();
//        adapter = new AssignedCaseAdapter();
//        adapter.setContext(getContext());
        recyclerView.setAdapter(sectionAdapter);
    }

    private void adapterWork(List<SearchCaseResponse> assignedCaseList) {
        header=new ArrayList<>();
        dividedList=new ArrayList<>();
        for(SearchCaseResponse caseResponse : assignedCaseList){
            String status = caseResponse.getCaseStatus_name();
            List<SearchCaseResponse> tempList=new ArrayList<>();
            if(header.contains(status)){
                int idx = header.indexOf(status);
                tempList=dividedList.get(idx);
                tempList.add(caseResponse);
                dividedList.set(idx,tempList);
            }else {
                header.add(status);
                tempList.add(caseResponse);
                dividedList.add(tempList);
            }
        }
        sectionAdapter.removeAllSections();
        sectionAdapter.notifyDataSetChanged();
        for(List<SearchCaseResponse> responses : dividedList){
            addSection(responses,header.get(dividedList.indexOf(responses)));
        }
    }

    private void addSection(List<SearchCaseResponse> assignedCaseList, String section) {
        sectionAdapter.addSection(new AssignedCaseAdapter(assignedCaseList, section, getContext()));
    }

    private void loadToken() {
        token = utils.getToken();
        if (token.length() == 0) {
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
        } else {
            gpfNo = utils.getGpfNo();
        }
    }

    private void toastShort(String string, Context context) {
        if (context != null) {
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
        }
    }
}