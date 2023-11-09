package com.release.cpmsmobileapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.release.cpmsmobileapp.R;
import com.release.cpmsmobileapp.databinding.RespectiveCaseAccusedItemLayoutBinding;
import com.release.cpmsmobileapp.responsebody.AccusedOfRespectiveCase;

import java.util.ArrayList;
import java.util.List;

public class RespectiveCaseAccusedAdapter extends RecyclerView.Adapter<RespectiveCaseAccusedAdapter.RespectiveCaseAccusedViewHolder> {

    private List<AccusedOfRespectiveCase> accusedOfRespectiveCase = new ArrayList<>();
    private Context context;


    public void setAccusedOfRespectiveCase(Context context, List<AccusedOfRespectiveCase> accusedOfRespectiveCase) {
        this.accusedOfRespectiveCase = accusedOfRespectiveCase;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RespectiveCaseAccusedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RespectiveCaseAccusedItemLayoutBinding itemBinding = RespectiveCaseAccusedItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new RespectiveCaseAccusedViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RespectiveCaseAccusedViewHolder holder, int position) {
        AccusedOfRespectiveCase response = accusedOfRespectiveCase.get(position);

        holder.srNo.setText("" + (position + 1));
        holder.id.setText("" + response.getId());
        holder.caseDetailId.setText("" + response.getCase_detail_id());
        holder.name.setText(response.getName());
        holder.father_name.setText(response.getFathers_name());
        holder.address.setText(response.getAddress());
        holder.phone_no.setText(response.getPhone_no());
        holder.email.setText(response.getEmail());
        holder.aadhar.setText(response.getAadhar());
        holder.remarks.setText(response.getRemarks());
        holder.accusedStatus.setText(response.getAccused_status());
        holder.bailerName.setText(response.getBailer_name());

    }

    @Override
    public int getItemCount() {
        return accusedOfRespectiveCase.size();
    }

    public class RespectiveCaseAccusedViewHolder extends RecyclerView.ViewHolder {
        TextView srNo, id, caseDetailId, name, father_name, address, phone_no, email, aadhar, remarks, accusedStatus, bailerName;

        public RespectiveCaseAccusedViewHolder(RespectiveCaseAccusedItemLayoutBinding itemBinding) {
            super(itemBinding.getRoot());
            srNo = itemBinding.srNo;
            id = itemBinding.aId;
            caseDetailId = itemBinding.aCaseDetailId;
            name = itemBinding.aName;
            father_name = itemBinding.aFathersName;
            address = itemBinding.aAddress;
            phone_no = itemBinding.aPhoneNo;
            email = itemBinding.aEmail;
            aadhar = itemBinding.aAadhar;
            remarks = itemBinding.aRemarks;
            accusedStatus = itemBinding.aAccusedStatus;
            bailerName = itemBinding.aBailerName;
        }
    }


}
