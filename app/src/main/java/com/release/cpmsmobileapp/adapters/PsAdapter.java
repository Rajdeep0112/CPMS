package com.release.cpmsmobileapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.release.cpmsmobileapp.responsebody.PsResponse;
import com.release.cpmsmobileapp.R;

import java.util.List;

public class PsAdapter extends RecyclerView.Adapter<PsAdapter.PsViewHolder> {
    private Context context;
    private List<PsResponse> psModel;

    public PsAdapter(Context context, List<PsResponse> psModel) {
        this.context = context;
        this.psModel = psModel;
    }

    @NonNull
    @Override
    public PsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ps_item_layout,parent,false);
        return new PsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PsViewHolder holder, int position) {
        PsResponse model=psModel.get(position);
        holder.id.setText(Integer.toString(model.getId()));
        holder.districtId.setText(Integer.toString(model.getDistrictId()));
        holder.name.setText(model.getName());
        holder.nameHi.setText(model.getName_hi());
    }

    @Override
    public int getItemCount() {
        return psModel.size();
    }

    public class PsViewHolder extends RecyclerView.ViewHolder{
        TextView id,districtId,name,nameHi;
        public PsViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id_value);
            districtId=itemView.findViewById(R.id.district_id_value);
            name=itemView.findViewById(R.id.name_value);
            nameHi=itemView.findViewById(R.id.name_hindi_value);
        }
    }
}
