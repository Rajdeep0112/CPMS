package com.release.cpmsmobileapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.release.cpmsmobileapp.R;
import com.release.cpmsmobileapp.responsebody.HearingsOfRespectiveCase;

import java.util.ArrayList;
import java.util.List;

public class RespectiveCaseHearingDetailsAdapter extends RecyclerView.Adapter<RespectiveCaseHearingDetailsAdapter.RespectiveCaseHearingViewHolder> {


    private List<HearingsOfRespectiveCase> respectiveCaseHearingList = new ArrayList<>();
    private Context context;


    public void setRespectiveCaseHearingListAdpater(List<HearingsOfRespectiveCase> respectiveCaseHearingList, Context context)
    {
        this.respectiveCaseHearingList = respectiveCaseHearingList;
        this.context = context;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RespectiveCaseHearingDetailsAdapter.RespectiveCaseHearingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.respective_hearing_item_layout, parent, false);
        return new RespectiveCaseHearingDetailsAdapter.RespectiveCaseHearingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RespectiveCaseHearingDetailsAdapter.RespectiveCaseHearingViewHolder holder, int position) {
        HearingsOfRespectiveCase response = respectiveCaseHearingList.get(position);

        holder.srNo.setText("" + (position + 1));
        holder.hearing_date.setText(response.getHearing_date());
        holder.case_detail_id.setText(""+response.getCase_detail_id());
        holder.court_name.setText(response.getCourt_name());
        holder.witnesses.setText(response.getWitnesses());
        holder.bailer.setText(response.getBailer());
        holder.accused.setText(response.getAccused());
        holder.remarks.setText(response.getRemarks());
        holder.testified.setText(response.getTestified());

    }

    @Override
    public int getItemCount() {
        return respectiveCaseHearingList.size();
    }

    public static class RespectiveCaseHearingViewHolder extends RecyclerView.ViewHolder {

        TextView srNo,hearing_date, case_detail_id, court_name, witnesses, bailer, accused, remarks, testified;
        public RespectiveCaseHearingViewHolder(@NonNull View itemView) {
            super(itemView);

            srNo=itemView.findViewById(R.id.sr_no);
            hearing_date = itemView.findViewById(R.id.h_hearing_date);
            case_detail_id = itemView.findViewById(R.id.h_case_detail_id);
            court_name = itemView.findViewById(R.id.h_court_name);
            witnesses = itemView.findViewById(R.id.h_witnesses);
            bailer = itemView.findViewById(R.id.h_bailer);
            accused = itemView.findViewById(R.id.h_accused);
            remarks = itemView.findViewById(R.id.h_remarks);
            testified = itemView.findViewById(R.id.h_testified);
        }
    }
}
