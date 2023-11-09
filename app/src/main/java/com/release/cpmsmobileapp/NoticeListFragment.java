package com.release.cpmsmobileapp;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.release.cpmsmobileapp.adapters.NoticeListAdapter;
import com.release.cpmsmobileapp.databinding.FragmentNoticeListBinding;
import com.release.cpmsmobileapp.notices.NoticesFilter;
import com.release.cpmsmobileapp.requestbody.AssignedCaseRequestBody;
import com.release.cpmsmobileapp.responsebody.NoticesResponse;
import com.release.cpmsmobileapp.utils.HelperUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NoticeListFragment extends Fragment {


    String token, gpfNo;
    View view;
    private RecyclerView recyclerView;

    private NoticeListAdapter adapter;
    private ApiInterface apiInterface;

    private HelperUtils utils;
    LinearLayout noNoticeError;
    @NonNull
    FragmentNoticeListBinding binding;
    private FrameLayout progressBar;
    private HorizontalScrollView hsv;
    private List<NoticesResponse> list;
    private NoticesFilter filterObj;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNoticeListBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        initializations();
        loadToken();
        setRecyclerView();
        getNoticeList();
        filterList();

        return view;
    }

    private void filterList() {

        binding.filterBtn.setOnClickListener(view1 -> {
            if (binding.filterCard.getVisibility() == View.VISIBLE) {
                binding.filterCard.setVisibility(View.GONE);

            } else {
                binding.filterCard.setVisibility(View.VISIBLE);
            }

        });

        binding.firNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterObj.setFIR_no(String.valueOf(charSequence));
                Log.wtf("fir", String.valueOf(charSequence));
                updateList();
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
                updateList();
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
                updateList();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void updateList() {
        List<NoticesResponse> tempList = new ArrayList<>();

        if(filterObj.getFIR_no().equals("") && filterObj.getCase_detail_id().equals("")
                && filterObj.getPs_name().equals("") && filterObj.getReg_date().equals("")) {
            adapterWork(list);
            return;
        }

        for (NoticesResponse response : list) {
            if (!filterObj.getFIR_no().equals("") && String.valueOf(response.getFIR_no()).equalsIgnoreCase(filterObj.getFIR_no()))
                tempList.add(response);
            else if (!filterObj.getCase_detail_id().equals("") &&String.valueOf(response.getCase_detail_id()).equalsIgnoreCase(filterObj.getCase_detail_id())) {
                tempList.add(response);
            } else if (!filterObj.getPs_name().equals("") &&response.getPs_name().toLowerCase().contains(filterObj.getPs_name().toLowerCase())) {
                tempList.add(response);
            } else if (!filterObj.getReg_date().equals("") &&response.getReg_date().toLowerCase().contains(filterObj.getReg_date().toLowerCase())) {
                tempList.add(response);
            }
        }
        Log.wtf("response", String.valueOf(tempList.size()));
        adapterWork(tempList);
    }


    private void getNoticeList() {
        gpfNo = "123";
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + token);

        Call<List<NoticesResponse>> call = apiInterface.getAllNotices(map, new AssignedCaseRequestBody(gpfNo));

        call.enqueue(new Callback<List<NoticesResponse>>() {
            @Override
            public void onResponse(Call<List<NoticesResponse>> call, Response<List<NoticesResponse>> response) {
                list = response.body();
                if (response.body() == null || list==null) {
                    progressBar.setVisibility(View.GONE);
                    hsv.setVisibility(View.GONE);
                    toastShort("Could not get hearing cases\n\terror code : " + response.code(), getContext());
                    noNoticeError.setVisibility(View.VISIBLE);
                    return;
                }
                progressBar.setVisibility(View.GONE);
                hsv.setVisibility(View.VISIBLE);
                if (list.size() != 0) {
                    adapterWork(list);
                    noNoticeError.setVisibility(View.GONE);
                } else {
                    toastShort("No Notice available", getContext());
                    noNoticeError.setVisibility(View.VISIBLE);
                    hsv.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<NoticesResponse>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                toastShort(t.getMessage(), getContext());
                noNoticeError.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NoticeListAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void adapterWork(List<NoticesResponse> noticesResponses) {
        adapter.setNoticeListAdapter(noticesResponses, getContext());
    }

    private void initializations() {
        recyclerView = view.findViewById(R.id.notice_rv);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        utils = new HelperUtils(requireContext());
        token = utils.getToken();
        gpfNo = utils.getGpfNo();
        filterObj = new NoticesFilter("", "",  "", "");
        noNoticeError = view.findViewById(R.id.noNoticeContainer);
        progressBar = view.findViewById(R.id.progressBar);
        hsv=view.findViewById(R.id.hsv);
    }

    private void loadToken() {
        token = utils.getToken();
        if (token.length() == 0) {
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

//    private void loadGpfNo(SharedPreferences sharedPreferences) {
//        gpfNo = sharedPreferences.getString(String.valueOf(R.string.gpf_no), "");
//    }

    private void toastShort(String string, Context context) {
        if (context != null) {
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
        }
    }

}