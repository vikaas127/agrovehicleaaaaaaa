package com.jaats.agrovehicle.activity;

import android.content.Context;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.jaats.agrovehicle.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static io.fabric.sdk.android.Fabric.TAG;

public class TractorAddonseListAdapter extends RecyclerView.Adapter<TractorAddonseListAdapter.ViewHolder> {
    private int selectedPosition = 3;

    private Context context;
    private List<TractorProductAddons> list;
    List<TractorProductAddons>tractorproduct=new ArrayList<>();


    public TractorAddonseListAdapter(Context context, List<TractorProductAddons> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.tractoraddonselist, parent, false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final TractorProductAddons tractorProduct = list.get(position);
        // holder.textTitle.setText(product.getId());
        // Picasso.get().load(subcategoryProduct.getImage()).into(holder.iv);
        if ((list.get(position).getTypeTractor().trim().equals("1")) && list.get(position).getTractorCategoryserviceType().trim().equals("1")) {
            holder.txt_name.setText(String.valueOf(tractorProduct.getTractorSubCategoryName()));
            holder.Price.setText(String.valueOf(tractorProduct.getTractorSubcategoryPrice()));
            Picasso.get().load(tractorProduct.getTypeimage()).into(holder.iv);
        }

        holder.checkbox.setChecked(selectedPosition == position);
        holder.checkbox.setChecked(tractorProduct.isSelected());
        holder.setItemClickListener(new ViewHolder.ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                CheckBox checkbox = (CheckBox) v;
                TractorProductAddons tractorProduct = list.get(pos);
                   /* if(selectedPosition == pos){
                        holder.checkbox.setChecked(true);
                    }
                    else{
                        holder.checkbox.setChecked(false);
                    }
                    */
                   if (pos>3){
                       holder.checkbox.setChecked(false);
                   }else {
                       holder.checkbox.setChecked(true);
                   }

                if (checkbox.isChecked()) {
                    tractorproduct.add(tractorProduct);

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
        ItemClickListener itemClickListener;
        public ImageView iv;


        public ViewHolder(View itemView) {
            super(itemView);

            // textTitle = itemView.findViewById(R.id.discount);
            txt_name=itemView.findViewById(R.id.tractoraaddonsename);
            Price= itemView.findViewById(R.id.tractoraddonsePrise);
            iv=itemView.findViewById(R.id.tractorimageview);

            checkbox=itemView.findViewById(R.id.tractoraddonsecheckbox);

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



