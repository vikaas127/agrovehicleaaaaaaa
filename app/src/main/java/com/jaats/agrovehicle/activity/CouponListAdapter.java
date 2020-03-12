package com.jaats.agrovehicle.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jaats.agrovehicle.R;

import java.util.List;

public class CouponListAdapter extends RecyclerView.Adapter<CouponListAdapter.ViewHolder> {

    private Context context;
    private List<CouponProduct> list;

    public CouponListAdapter(Context context, List<CouponProduct> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.coupon_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CouponProduct product = list.get(position);
holder.discount.setText(String.valueOf(product.getDiscount()));
        holder.expiration.setText(String.valueOf(product.getExpiration()));
        holder.textRating.setText(String.valueOf(product.getId()));
//        holder.textTitle.setText(String.valueOf(product.getDiscount()));
        // holder.textYear.setText(String.valueOf(product.getProvider_id()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle, textRating, discount,expiration;

        public ViewHolder(View itemView) {
            super(itemView);
            expiration=itemView.findViewById(R.id.expiration);
           // textTitle = itemView.findViewById(R.id.discount);
            textRating = itemView.findViewById(R.id.promo_code);
            discount=itemView.findViewById(R.id.discount);
            // textYear = itemView.findViewById(R.id.main_year);
        }
    }

}