package com.release.cpmsmobileapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.release.cpmsmobileapp.R;
import com.release.cpmsmobileapp.responsebody.WitnessOfRespectiveCase;

import java.util.ArrayList;
import java.util.List;

public class RespectiveCaseWitnessAdapter extends RecyclerView.Adapter<RespectiveCaseWitnessAdapter.RespectiveCaseWitnessViewHolder> {

    private List<WitnessOfRespectiveCase> respectiveCaseWitness = new ArrayList<>();
    private Context context;


    public void setRespectiveCaseWitness(Context context, List<WitnessOfRespectiveCase> respectiveCaseWitness)
    {
        this.context = context;
        this.respectiveCaseWitness = respectiveCaseWitness;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RespectiveCaseWitnessAdapter.RespectiveCaseWitnessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.respective_case_witness_item_layout, parent, false);
        return new RespectiveCaseWitnessAdapter.RespectiveCaseWitnessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RespectiveCaseWitnessAdapter.RespectiveCaseWitnessViewHolder holder, int position) {
        WitnessOfRespectiveCase response = respectiveCaseWitness.get(position);

        holder.srNo.setText("" + (position + 1));
        holder.witnessType.setText(response.getWitness_type());
        holder.witnessSide.setText(response.getWitness_side());
        holder.id.setText(Integer.toString(response.getId()));
        holder.fatherName.setText(response.getFathers_name());
        holder.caseDetailId.setText(Integer.toString(response.getCase_detail_id()));
        holder.address.setText(response.getAddress());
//        holder.mobileNo.setText(response.getMobile_no());
        holder.email.setText(response.getEmail());
        holder.relationType.setText(response.getRelation_type());
        holder.idType.setText(response.getId_type());
        holder.idTypeValue.setText(response.getId_type_value());
    }

    @Override
    public int getItemCount() {
        return respectiveCaseWitness.size();
    }

    public static class RespectiveCaseWitnessViewHolder extends RecyclerView.ViewHolder {

        TextView srNo,witnessType, witnessSide, id, name, fatherName, caseDetailId, address, mobileNo, email, relationType, idType, idTypeValue;

        public RespectiveCaseWitnessViewHolder(@NonNull View itemView) {
            super(itemView);

            srNo=itemView.findViewById(R.id.sr_no);
            witnessType = itemView.findViewById(R.id.w_witness_type);
            witnessSide = itemView.findViewById(R.id.w_witness_side);
            id = itemView.findViewById(R.id.w_id);
            name = itemView.findViewById(R.id.w_name);
            fatherName = itemView.findViewById(R.id.w_fathers_name);
            caseDetailId = itemView.findViewById(R.id.w_case_detail_id);
            address = itemView.findViewById(R.id.w_address);
            mobileNo = itemView.findViewById(R.id.mobileNo);
            email = itemView.findViewById(R.id.w_email);
            relationType = itemView.findViewById(R.id.w_relation_type);
            idType = itemView.findViewById(R.id.w_id_type);
            idTypeValue = itemView.findViewById(R.id.w_id_type_value);
        }
    }
}
