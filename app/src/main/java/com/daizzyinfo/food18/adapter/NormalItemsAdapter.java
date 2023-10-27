package com.daizzyinfo.food18.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.viewModel.ProductList.ProductListResponse;

import java.util.List;

public class NormalItemsAdapter  extends RecyclerView.Adapter<NormalItemsAdapter.NormalItemsVH>{
    List<ProductListResponse> model;
    Context context;

    public NormalItemsAdapter(List<ProductListResponse> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public NormalItemsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_normal_items,parent,false);
        return new NormalItemsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NormalItemsVH holder, int position) {

        holder.txtNameNormalItm.setText(String.valueOf(model.get(position).getData().getNormal().get(position).getProductName()));
        holder.txtNormalItmRs.setText(String.valueOf(" ₹ "+model.get(position).getData().getNormal().get(position).getPrice()));
        holder.txtDiscounts.setText(String.valueOf(model.get(position).getData().getNormal().get(position).getDiscount()+"%"));
//        holder.txtExtraFoodName.setText(String.valueOf(model.get(position).getData().getPopular().get(position).getSubcategoryName()));
        holder.txtExtraFNameNormalItm.setText(String.valueOf("Extra "+model.get(position).getData().getNormal().get(position).getProductName()));


        String url = model.get(position).getData().getNormal().get(position).getProductImage().toString();
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.food3)
                .into(holder.imgNormalItm);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class NormalItemsVH extends RecyclerView.ViewHolder{
        ImageView imgNormalItm;
        TextView txtNameNormalItm,txtReview,txtNormalItmRs,txtDiscounts,txtExtraFNameNormalItm,txtRatings;
        public NormalItemsVH(@NonNull View itemView) {
            super(itemView);
            imgNormalItm = itemView.findViewById(R.id.imgNormalItm);
            txtNameNormalItm =  itemView.findViewById(R.id.txtNameNormalItm);
            txtNormalItmRs =  itemView.findViewById(R.id.txtNormalItmRs);
            txtDiscounts =  itemView.findViewById(R.id.txtDiscounts);
            txtExtraFNameNormalItm =  itemView.findViewById(R.id.txtExtraFNameNormalItm);
            txtRatings =  itemView.findViewById(R.id.txtRatings);
            txtReview = itemView.findViewById(R.id.txtReview);
        }
    }

}