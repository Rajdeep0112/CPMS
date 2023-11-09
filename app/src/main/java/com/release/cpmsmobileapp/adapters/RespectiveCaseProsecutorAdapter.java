package com.release.cpmsmobileapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.release.cpmsmobileapp.R;
import com.release.cpmsmobileapp.responsebody.PublicProsecutorOfRespectiveCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RespectiveCaseProsecutorAdapter extends RecyclerView.Adapter<RespectiveCaseProsecutorAdapter.RespectiveCaseProsecutorViewHolder> {

    private List<PublicProsecutorOfRespectiveCase> prosecutorOfRespectiveCases = new ArrayList<>();
    private Context context;

    public void setProsecutorOfRespectiveCases(Context context, List<PublicProsecutorOfRespectiveCase> prosecutorOfRespectiveCases)
    {
        this.context = context;
        this.prosecutorOfRespectiveCases = prosecutorOfRespectiveCases;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RespectiveCaseProsecutorAdapter.RespectiveCaseProsecutorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.respective_case_prosecutor_item_layout, parent, false);
        return new RespectiveCaseProsecutorAdapter.RespectiveCaseProsecutorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RespectiveCaseProsecutorAdapter.RespectiveCaseProsecutorViewHolder holder, int position) {
        PublicProsecutorOfRespectiveCase response = prosecutorOfRespectiveCases.get(position);

        holder.srNo.setText("" + (position + 1));
        holder.status.setText(response.getStatus());
        holder.id.setText(""+response.getId());
        holder.name.setText(response.getName());
        holder.fatherName.setText(response.getFathers_name());
        holder.address.setText(response.getAddress());
        holder.phoneNo.setText(response.getPhone_no());
        holder.email.setText(response.getEmail());
        holder.caseDetailId.setText(response.getCase_detail_id());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class RespectiveCaseProsecutorViewHolder extends RecyclerView.ViewHolder {

        TextView srNo,status, id, name, fatherName, address, phoneNo, email, caseDetailId;

        public RespectiveCaseProsecutorViewHolder(@NonNull View itemView) {
            super(itemView);

            srNo=itemView.findViewById(R.id.sr_no);
            status = itemView.findViewById(R.id.p_status);
            id = itemView.findViewById(R.id.p_id);
            name = itemView.findViewById(R.id.p_name);
            fatherName = itemView.findViewById(R.id.p_fathers_name);
            address = itemView.findViewById(R.id.p_address);
            phoneNo = itemView.findViewById(R.id.a_phone_no);
            email = itemView.findViewById(R.id.p_email);
            caseDetailId = itemView.findViewById(R.id.p_case_detail_id);
        }
    }
}
