package com.release.cpmsmobileapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.release.cpmsmobileapp.adapters.DistrictAdapter;
import com.release.cpmsmobileapp.responsebody.DistrictResponse;
import com.release.cpmsmobileapp.utils.HelperUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DistrictFragment extends Fragment {


    String token;
    View view;
    private RecyclerView recyclerView;
    private List<DistrictResponse> distList;
    private DistrictAdapter adapter;
    private ApiInterface apiInterface;

    private HelperUtils utils;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_district, container, false);
        loadToken();


        recyclerView = view.findViewById(R.id.district_rv);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        utils = new HelperUtils(requireContext());
        token = utils.getToken();
        getDistrictList();

        return view;
    }

    private void loadToken() {
        if (token.length() == 0) {
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
        }
    }



    private void getDistrictList(){
        Map<String,String> map = new HashMap<>();
        map.put("Authorization","Bearer "+token);

        Call<List<DistrictResponse>> call = apiInterface.getDistrictList(map);

        call.enqueue(new Callback<List<DistrictResponse>>() {
            @Override
            public void onResponse(Call<List<DistrictResponse>> call, Response<List<DistrictResponse>> response) {
                distList = response.body();
                if(distList != null) {
                    adapter = new DistrictAdapter(requireContext(), distList);
                    recyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DistrictResponse>> call, Throwable t) {
                Toast.makeText(requireContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}