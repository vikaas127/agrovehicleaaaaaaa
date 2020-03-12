package com.jaats.agrovehicle.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jaats.agrovehicle.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AddListAdapter extends RecyclerView.Adapter<AddListAdapter.ViewHolder> {

    private Context context;
    private List<FatchAddonproduct> list;
    private onItemClickListener mListener;
    public  interface onItemClickListener{
        void onItemClicke(int position);
    }
    public void setonItemClickListener(onItemClickListener listener){
        mListener=listener;
    }
    public AddListAdapter(Context context, List<FatchAddonproduct> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.addontractor, parent, false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       FatchAddonproduct fatchAddonproduct = list.get(position);

        // holder.textTitle.setText(product.getId());

        holder.name.setText(String.valueOf(fatchAddonproduct.getAddName()));
        holder.id.setText(String.valueOf(fatchAddonproduct.getServiceType()));
        // Picasso.get().load(dataModelArrayList.get(position).getImgURL()).into(holder.iv);
//        holder.textTitle.setText(String.valueOf(product.getDiscount()));
        // holder.textYear.setText(String.valueOf(product.getProvider_id()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,id;


        public ViewHolder(View itemView) {
            super(itemView);

            // textTitle = itemView.findViewById(R.id.discount);
            name=itemView.findViewById(R.id.text_view1);
            id=itemView.findViewById(R.id.text_view2);

            itemView.setOnClickListener(new View.OnClickListener() {
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
