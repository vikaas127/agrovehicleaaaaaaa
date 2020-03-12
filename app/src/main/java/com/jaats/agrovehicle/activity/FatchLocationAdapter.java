package com.jaats.agrovehicle.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jaats.agrovehicle.R;
import com.jaats.agrovehicle.config.Config;

import java.util.List;

public class FatchLocationAdapter extends RecyclerView.Adapter<FatchLocationAdapter.ViewHolder> {

    private Context context;
    private List<fatchLocationProduct> list;

    public FatchLocationAdapter(Context context, List<fatchLocationProduct> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_search_results, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        fatchLocationProduct product=list.get(position);



        holder.destination.setText(String.valueOf(product.getD_name()));
        // holder.textTitle.setText(product.getId());
        holder.source.setText(String.valueOf(product.getS_name()));
        // holder.textYear.setText(String.valueOf(product.getProvider_id()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView destination,source;

        public ViewHolder(View itemView) {
            super(itemView);

            destination= itemView.findViewById(R.id.txt_place);
            source=itemView.findViewById(R.id.txt_address);
            // textRating = itemView.findViewById(R.id.car_name);
            // textYear = itemView.findViewById(R.id.main_year);
        }
    }

}
