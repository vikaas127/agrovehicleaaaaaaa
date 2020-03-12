package com.jaats.agrovehicle.activity;

import android.content.Context;
import android.media.MediaCodec;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaats.agrovehicle.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.ViewHolder> {


    private Context context;

    private List<VehiclFatchProduct> list;
    private onItemClickListener mListener;


    public  interface onItemClickListener{


        void onItemClicke(int position);

    }

    public void setonItemClickListener(onItemClickListener listener){

        mListener=listener;
}
    public VehicleListAdapter(Context context, List<VehiclFatchProduct> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_car_type, parent, false);

        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VehiclFatchProduct vehiclFatchProduct = list.get(position);

        // holder.textTitle.setText(product.getId());
        Picasso.get().load(vehiclFatchProduct.getImage()).into(holder.iv);

        holder.name.setText(String.valueOf(vehiclFatchProduct.getName()));
       // Picasso.get().load(dataModelArrayList.get(position).getImgURL()).into(holder.iv);
//        holder.textTitle.setText(String.valueOf(product.getDiscount()));
        // holder.textYear.setText(String.valueOf(product.getProvider_id()));

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textTitle, name,txt_time;
        public ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);

            // textTitle = itemView.findViewById(R.id.discount);
            txt_time=itemView.findViewById(R.id.txt_time);
            name= itemView.findViewById(R.id.txt_item_car_type_name);
            iv=itemView.findViewById(R.id.iv_item_car_type);

        itemView.setOnClickListener(
                new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if (mListener!=null){
            int position=getAdapterPosition();
            if (position!=RecyclerView.NO_POSITION){
                mListener.onItemClicke(position);
            }
        }
    }
});
            // textYear = itemView.findViewById(R.id.main_year);
        }
    }

}