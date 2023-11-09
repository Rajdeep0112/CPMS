package com.release.cpmsmobileapp;


import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.release.cpmsmobileapp.adapters.PsAdapter;
import com.release.cpmsmobileapp.responsebody.PsResponse;
import com.release.cpmsmobileapp.utils.HelperUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PsFragment extends Fragment {

    String token,district_id;
    View view;
    private RecyclerView recyclerView;
    private List<PsResponse> psList;
    private PsAdapter adapter;
    private ApiInterface apiInterface;
    private SearchView searchDistrictId;

    private HelperUtils utils;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ps, container, false);
        loadToken();
        loadDistrict_id();
        initializations();
        getPsList(district_id);
        search();

        return view;
    }

    private void loadToken() {
        if (token.length() == 0) {
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
        }
    }


    private void initializations() {

        recyclerView = view.findViewById(R.id.ps_rv);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        searchDistrictId = view.findViewById(R.id.search_district_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        utils = new HelperUtils(requireContext());
        token = utils.getToken();

    }
    private void loadDistrict_id(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(String.valueOf(R.string.user_preferences), MODE_PRIVATE);
        district_id = sharedPreferences.getString(String.valueOf(R.string.district_id),"");
    }
    private void getPsList(String district_id){
        Map<String,String> map = new HashMap<>();
        map.put("Authorization","Bearer "+token);
        Call<List<PsResponse>> call = apiInterface.getPsList(map,district_id);
        call.enqueue(new Callback<List<PsResponse>>() {
            @Override
            public void onResponse(Call<List<PsResponse>> call, Response<List<PsResponse>> response) {
                psList = response.body();
                if(psList!=null) {
                    adapter = new PsAdapter(requireContext(), psList);
                    recyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PsResponse>> call, Throwable t) {
                Toast.makeText(requireContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void search(){

        searchDistrictId.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.trim().equals("")) getPsList("");
                else getPsList(s);
                return true;
            }
        });
    }
}