package com.release.cpmsmobileapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.release.cpmsmobileapp.R;
import com.release.cpmsmobileapp.responsebody.BailersOfRespectiveCase;

import java.util.ArrayList;
import java.util.List;

public class RespectiveCaseBailerAdapter extends RecyclerView.Adapter<RespectiveCaseBailerAdapter.RespectiveCaseBailerViewHolder> {

    private List<BailersOfRespectiveCase> respectiveCaseBailer = new ArrayList<>();
    private Context context;

    public void setRespectiveCaseBailer(Context context, List<BailersOfRespectiveCase> respectiveCaseBailer)
    {
        this.context = context;
        this.respectiveCaseBailer = respectiveCaseBailer;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RespectiveCaseBailerAdapter.RespectiveCaseBailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.respective_case_bailer_item_layout, parent, false);
        return new RespectiveCaseBailerAdapter.RespectiveCaseBailerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RespectiveCaseBailerAdapter.RespectiveCaseBailerViewHolder holder, int position) {
        BailersOfRespectiveCase response = respectiveCaseBailer.get(position);

        holder.srNo.setText("" + (position + 1));
        holder.id.setText(""+response.getId());
        holder.accusedName.setText(response.getAccused_name());
        holder.name.setText(response.getName());
        holder.fatherName.setText(response.getFathers_name());
        holder.address.setText(response.getAddress());
        holder.phoneNo.setText(response.getPhone_no());
        holder.email.setText(response.getEmail());
        holder.idNo.setText(response.getId_no());
        holder.caseDetailId.setText(""+response.getCase_detail_id());
        holder.bailDate.setText(response.getBail_date());
        holder.remarks.setText(response.getRemarks());
    }

    @Override
    public int getItemCount() {
        return respectiveCaseBailer.size();
    }

    public static class RespectiveCaseBailerViewHolder extends RecyclerView.ViewHolder {

        TextView srNo,id, accusedName, name, fatherName, address, phoneNo, email, idNo, caseDetailId, bailDate, remarks;

        public RespectiveCaseBailerViewHolder(@NonNull View itemView) {
            super(itemView);

            srNo=itemView.findViewById(R.id.sr_no);
            id = itemView.findViewById(R.id.b_id);
            accusedName = itemView.findViewById(R.id.b_accused_name);
            name = itemView.findViewById(R.id.b_name);
            fatherName = itemView.findViewById(R.id.b_fathers_name);
            address = itemView.findViewById(R.id.b_address);
            phoneNo = itemView.findViewById(R.id.b_phone_no);
            email = itemView.findViewById(R.id.b_email);
            idNo = itemView.findViewById(R.id.b_id_no);
            caseDetailId = itemView.findViewById(R.id.b_case_detail_id);
            bailDate = itemView.findViewById(R.id.b_bail_date);
            remarks = itemView.findViewById(R.id.b_remarks);
        }
    }
}
