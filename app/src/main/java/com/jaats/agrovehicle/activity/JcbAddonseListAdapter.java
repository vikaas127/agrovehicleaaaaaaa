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

public class JcbAddonseListAdapter extends RecyclerView.Adapter<JcbAddonseListAdapter.ViewHolder> {

    private Context context;
    private List<JcbProductAddonse> list;
    List<TractorProduct>tractor =new ArrayList<>();



    public JcbAddonseListAdapter(Context context, List<JcbProductAddonse> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.jcbaddonselist, parent, false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final JcbProductAddonse jcbAddonseListAdapter = list.get(position);

        // holder.textTitle.setText(product.getId());
        // Picasso.get().load(subcategoryProduct.getImage()).into(holder.iv);
        if((list.get(position).getTypeJcb().trim().equals("2"))&& list.get(position).getjcbCategoryserviceType().trim().equals("4")) {

            holder.txt_name.setText(String.valueOf(jcbAddonseListAdapter.getJcbSubCategoryName()));
            holder.Price.setText(String.valueOf(jcbAddonseListAdapter.getJcbSubcategoryPrice()));
            Picasso.get().load(jcbAddonseListAdapter.getTypeimage()).into(holder.iv);

        }



        holder.checkbox.setChecked(jcbAddonseListAdapter.isSelected());
        holder.setItemClickListener(new ViewHolder.ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox checkbox=(CheckBox)v;
               JcbProductAddonse jcbProduct1=list.get(pos);


                if (checkbox.isChecked()){
                    jcbProduct1.setSelected(true);
                    //tractor.add(jcbProduct1);

                    // Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();


                }else if(!checkbox.isChecked()){


                }
            }
        });


        // Picasso.get().load(dataModelArrayList.get(position).getImgURL()).into(holder.iv);
//        holder.textTitle.setText(String.valueOf(product.getDiscount()));
        // holder.textYear.setText(String.valueOf(product.getProvider_id()));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textTitle, Price,txt_name,ServiceType;
        public CheckBox checkbox;
        public ImageView iv;

        ItemClickListener itemClickListener;
        public ViewHolder(View itemView) {
            super(itemView);

            // textTitle = itemView.findViewById(R.id.discount);
            txt_name=itemView.findViewById(R.id.JCBaaddonsename);
            iv=itemView.findViewById(R.id.JcbImageView);
            Price= itemView.findViewById(R.id.JCBaddonsePrise);
            checkbox=itemView.findViewById(R.id.JCBaddonsecheckbox);

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

