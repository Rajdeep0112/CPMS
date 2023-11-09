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

import com.release.cpmsmobileapp.responsebody.NoticesResponse;
import com.release.cpmsmobileapp.databinding.NoticeItemLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class NoticeListAdapter extends RecyclerView.Adapter<NoticeListAdapter.NoticesViewHolder> {

    private List<NoticesResponse> noticesResponses = new ArrayList<>();
    private Context context;

    public void setNoticeListAdapter(List<NoticesResponse> noticesResponses, Context context)
    {
        this.noticesResponses = noticesResponses;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoticesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoticeItemLayoutBinding itemLayoutBinding = NoticeItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NoticeListAdapter.NoticesViewHolder(itemLayoutBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull NoticeListAdapter.NoticesViewHolder holder, int position) {
        NoticesResponse noticeItem = noticesResponses.get(position);

        holder.srNo.setText(""+(position+1));
        holder.name.setText(noticeItem.getName());
        holder.io_name.setText(noticeItem.getIo_name());
        holder.io_assign_date.setText(noticeItem.getIo_assign_date());
        holder.io_compliance_date.setText(noticeItem.getIo_compliance_date());
        holder.notice_completed_date.setText(noticeItem.getNotice_completed_date());
        holder.issue_date.setText(noticeItem.getIssue_date());
        holder.appearance_date.setText(noticeItem.getAppearance_date());
        holder.notice_compliance_date.setText(noticeItem.getNotice_compliance_date());
        holder.from_district_name.setText(noticeItem.getFrom_district_name());
        holder.district_name.setText(noticeItem.getDistrict_name());
        holder.ps_name.setText(noticeItem.getPs_name());
        holder.dist_refer_id.setText(noticeItem.getDist_refer_id());
        holder.court_name.setText(noticeItem.getCourt_name());
        holder.noticetypes_name.setText(noticeItem.getNoticetypes_name());
        holder.FIR_no.setText(""+noticeItem.getFIR_no());
        holder.year.setText(noticeItem.getYear());
        holder.reg_date.setText(noticeItem.getReg_date());
        holder.case_detail_id.setText(""+noticeItem.getCase_detail_id());

    }

    @Override
    public int getItemCount() {
        return noticesResponses.size();
    }

    public class NoticesViewHolder extends RecyclerView.ViewHolder {

        TextView srNo,name, io_name, io_assign_date, io_compliance_date, notice_completed_date, issue_date, appearance_date, notice_compliance_date, from_district_name, district_name, ps_name, dist_refer_id, court_name, noticetypes_name, FIR_no, year, reg_date, case_detail_id;
        LinearLayout noticeLayout;
        public NoticesViewHolder(NoticeItemLayoutBinding itemLayoutBinding)
        {
            super(itemLayoutBinding.getRoot());

            srNo=itemLayoutBinding.srNo;
            name = itemLayoutBinding.name;
            io_name = itemLayoutBinding.ioName;
            io_assign_date = itemLayoutBinding.ioAssignDate;
            io_compliance_date = itemLayoutBinding.ioComplianceDate;
            notice_completed_date = itemLayoutBinding.noticeCompletedDate;
            issue_date = itemLayoutBinding.issueDate;
            appearance_date = itemLayoutBinding.appearanceDate;
            notice_compliance_date = itemLayoutBinding.noticeComplianceDate;
            from_district_name = itemLayoutBinding.fromDistrictName;
            district_name = itemLayoutBinding.districtName;
            ps_name = itemLayoutBinding.psName;
            dist_refer_id = itemLayoutBinding.distReferId;
            court_name = itemLayoutBinding.courtName;
            noticetypes_name = itemLayoutBinding.noticeTypesName;
            FIR_no = itemLayoutBinding.firNo;
            year = itemLayoutBinding.year;
            reg_date = itemLayoutBinding.regDate;
            case_detail_id = itemLayoutBinding.caseDetailId;

            noticeLayout = itemLayoutBinding.layout;



        }
    }
}
