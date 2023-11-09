package com.release.cpmsmobileapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.release.cpmsmobileapp.responsebody.DistrictResponse;
import com.release.cpmsmobileapp.R;

import java.util.List;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.DistViewHolder> {

    private Context context;
    private List<DistrictResponse> distModel;

    public DistrictAdapter(Context context, List<DistrictResponse> distModel) {
        this.context = context;
        this.distModel = distModel;
    }
    @NonNull
    @Override
    public DistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.district_item_layout,parent,false);
        return new DistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DistViewHolder holder, int position) {
        DistrictResponse model = distModel.get(position);
        holder.id.setText(Integer.toString(model.getId()));
        holder.distName.setText(model.getDist_name());
        holder.name.setText(model.getName());
        holder.nameHi.setText(model.getName_hi());

    }

    @Override
    public int getItemCount() {
        return distModel.size();
    }

    public class DistViewHolder extends RecyclerView.ViewHolder{
        TextView id,distName,name,nameHi;
        public DistViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_value);
            distName =itemView.findViewById(R.id.district_name);
            name = itemView.findViewById(R.id.name_value);
            nameHi = itemView.findViewById(R.id.name_hindi_value);
        }
    }
}
