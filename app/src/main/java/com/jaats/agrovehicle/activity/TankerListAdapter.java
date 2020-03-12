package com.jaats.agrovehicle.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jaats.agrovehicle.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TankerListAdapter extends RecyclerView.Adapter<TankerListAdapter.ViewHolder> {

    private Context context;
    private List<TankerProduct> list;
    List<TankerProduct>tankerProducts=new ArrayList<>();


    public TankerListAdapter(Context context, List<TankerProduct> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.addtankertype, parent, false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TankerProduct tankerProduct = list.get(position);



        // holder.textTitle.setText(product.getId());
        // Picasso.get().load(subcategoryProduct.getImage()).into(holder.iv);
        if ((list.get(position).getTankerCategoryserviceType().trim().equals("4")&&list.get(position).getTypeTanker().trim().equals("2"))) {
            holder.txt_name.setText(String.valueOf(tankerProduct.getTankerSubCategoryName()));
            holder.Price.setText(String.valueOf(tankerProduct.getTankerSubcategoryPrice()));
            Picasso.get().load(tankerProduct.getTankerimage()).into(holder.iv);

        }
        holder.checkbox.setChecked(tankerProduct.isSelected());
        holder.setItemClickListener(new ViewHolder.ItemClickListener() {

            @Override
            public void onItemClick(View v, int pos) {
                CheckBox checkbox = (CheckBox) v;
                TankerProduct tankerProduct = list.get(pos);

                if (checkbox.isChecked()) {
                    tankerProduct.setSelected(true);
                    tankerProducts.add(tankerProduct);

                    // Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();


                } else if (!checkbox.isChecked()) {


                }
            }
        });
    }

    // Picasso.get().load(dataModelArrayList.get(position).getImgURL()).into(holder.iv);
//        holder.textTitle.setText(String.valueOf(product.getDiscount()));
    // holder.textYear.setText(String.valueOf(product.getProvider_id()));



    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CheckBox checkbox;
        public TextView textTitle, Price,txt_name,ServiceType;
        public ImageView iv;
        ItemClickListener itemClickListener;


        public ViewHolder(View itemView) {
            super(itemView);

            // textTitle = itemView.findViewById(R.id.discount);
            txt_name=itemView.findViewById(R.id.tankerName2);
            Price= itemView.findViewById(R.id.tankerPrise2);
            iv=itemView.findViewById(R.id.tankerImage);

            checkbox=itemView.findViewById(R.id.checkbox_meat);

            checkbox.setOnClickListener(this);

            // textYear = itemView.findViewById(R.id.main_year);
        }
        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener=ic;
        }


        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
        interface ItemClickListener{
            void onItemClick(View v,int pos);

        }
    }





}


