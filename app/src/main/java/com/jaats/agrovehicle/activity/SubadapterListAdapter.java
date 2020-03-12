package com.jaats.agrovehicle.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.jaats.agrovehicle.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static io.fabric.sdk.android.Fabric.TAG;

public class SubadapterListAdapter extends RecyclerView.Adapter<SubadapterListAdapter.ViewHolder> {

    private Context context;
    private List<SubcategoryProduct> list;



    public SubadapterListAdapter(Context context, List<SubcategoryProduct> list) {
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
        final SubcategoryProduct subcategoryProduct = list.get(position);

        // holder.textTitle.setText(product.getId());
       // Picasso.get().load(subcategoryProduct.getImage()).into(holder.iv);
       // if(list.get(position).getType().equals("2")) {




            holder.txt_name.setText(String.valueOf(subcategoryProduct.getSubCategoryName()));
            holder.Price.setText(String.valueOf(subcategoryProduct.getSubcategoryPrice()));
            holder.ServiceType.setText(String.valueOf(subcategoryProduct.getCategoryserviceType()));
      //  }




        // Picasso.get().load(dataModelArrayList.get(position).getImgURL()).into(holder.iv);
//        holder.textTitle.setText(String.valueOf(product.getDiscount()));
        // holder.textYear.setText(String.valueOf(product.getProvider_id()));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle, Price,txt_name,ServiceType;


        public ViewHolder(View itemView) {
            super(itemView);

            // textTitle = itemView.findViewById(R.id.discount);
            txt_name=itemView.findViewById(R.id.NameLayout);
            Price= itemView.findViewById(R.id.PriseLayout);
            ServiceType=itemView.findViewById(R.id.ServiceTypeId);

            // textYear = itemView.findViewById(R.id.main_year);
        }
    }


}
