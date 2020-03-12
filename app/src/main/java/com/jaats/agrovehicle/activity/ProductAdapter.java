package com.jaats.agrovehicle.activity;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jaats.agrovehicle.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<TripsProduct> list;

    public ProductAdapter(Context context, List<TripsProduct> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TripsProduct product=list.get(position);
        holder.payment_mode.setText(String.valueOf(product.getPayment_mode()));
       // holder.textTitle.setText(product.getId());
        holder.textTitle.setText(String.valueOf(product.getBooking_id()));
       // holder.textYear.setText(String.valueOf(product.getProvider_id()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle,payment_mode , textYear;

        public ViewHolder(View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.booking_id);
            payment_mode=itemView.findViewById(R.id.payment_mode);
           // textRating = itemView.findViewById(R.id.car_name);
           // textYear = itemView.findViewById(R.id.main_year);
        }
    }

}