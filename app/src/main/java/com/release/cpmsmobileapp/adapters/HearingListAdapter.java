package com.release.cpmsmobileapp.adapters;

import android.animation.LayoutTransition;
import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.release.cpmsmobileapp.responsebody.HearingListResponse;
import com.release.cpmsmobileapp.databinding.HearingItemLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class HearingListAdapter extends RecyclerView.Adapter<HearingListAdapter.HearingListViewHolder>{

    private List<HearingListResponse> hearingListResponses = new ArrayList<>();
    private Context context;


    public void setHearingListAdapter(List<HearingListResponse> hearingListResponse,Context context){
        this.hearingListResponses = hearingListResponse;
        this.context = context;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HearingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HearingItemLayoutBinding itemLayoutBinding = HearingItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HearingListAdapter.HearingListViewHolder(itemLayoutBinding);

    }



    @Override
    public void onBindViewHolder(@NonNull HearingListAdapter.HearingListViewHolder holder, int position) {

        HearingListResponse hearingItem = hearingListResponses.get(position);

        holder.srNo.setText(""+(position+1));
        holder.fir_no.setText(""+hearingItem.getFIR_no());
        holder.year.setText(hearingItem.getYear());
        holder.reg_date.setText(hearingItem.getReg_date());
        holder.ps_name.setText(hearingItem.getPs_name());
        holder.district_name.setText(hearingItem.getDistrict_name());
        holder.case_detail_date.setText(""+hearingItem.getCase_detail_id());
        holder.court_name.setText(hearingItem.getCourt_name());
        holder.witnessess.setText(hearingItem.getWitnesses());
        holder.accused.setText(hearingItem.getAccused());
        holder.io.setText(hearingItem.getIo());

    }

    @Override
    public int getItemCount() {
        return hearingListResponses.size();
    }

    public class HearingListViewHolder extends RecyclerView.ViewHolder{
        TextView srNo,fir_no, year, reg_date, ps_name, district_name, hearing_date, case_detail_date, court_name, witnessess, bailer, accused, io;
        LinearLayout hearingLayout;
        public HearingListViewHolder(HearingItemLayoutBinding itemBinding) {
            super(itemBinding.getRoot());

            srNo=itemBinding.srNo;
            fir_no = itemBinding.firNo;
            year = itemBinding.year;
            reg_date = itemBinding.regDate;
            ps_name = itemBinding.psName;
            district_name = itemBinding.districtName;
            hearing_date = itemBinding.hearingDate;
            case_detail_date = itemBinding.caseDetailId;
            court_name = itemBinding.courtName;
            witnessess = itemBinding.witnesses;
            bailer = itemBinding.bailer;
            accused = itemBinding.accused;
            io = itemBinding.io;
            hearingLayout = itemBinding.layout;

        }
    }


}
