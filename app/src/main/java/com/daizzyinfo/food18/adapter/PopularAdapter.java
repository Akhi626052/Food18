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
import com.daizzyinfo.food18.model.PopularModel;
import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.viewModel.ProductList.ProductListResponse;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.VHpopular>{
    List<ProductListResponse> model;
    Context context;

    public PopularAdapter(List<ProductListResponse> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public VHpopular onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_popular_item,parent,false);
        return new VHpopular(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHpopular holder, int position) {

        holder.txtNameProduct.setText(String.valueOf(model.get(position).getData().getPopular().get(position).getProductName()));
        holder.txtProductRs.setText(String.valueOf(" ₹ "+model.get(position).getData().getPopular().get(position).getPrice()));
        holder.txtDiscount.setText(String.valueOf(model.get(position).getData().getPopular().get(position).getDiscount()+"%"));
//        holder.txtExtraFoodName.setText(String.valueOf(model.get(position).getData().getPopular().get(position).getSubcategoryName()));
        holder.txtExtraFoodName.setText(String.valueOf("Extra "+model.get(position).getData().getPopular().get(position).getProductName()));


        String url = model.get(position).getData().getPopular().get(position).getProductImage().toString();
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.food3)
                .into(holder.imgProduct);



    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class VHpopular extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtNameProduct,txtReviews,txtProductRs,txtDiscount,txtExtraFoodName,txtRating;

        public VHpopular(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtNameProduct =  itemView.findViewById(R.id.txtNameProduct);
            txtProductRs =  itemView.findViewById(R.id.txtProductRs);
            txtDiscount =  itemView.findViewById(R.id.txtDiscount);
            txtExtraFoodName =  itemView.findViewById(R.id.txtExtraFoodName);
            txtRating =  itemView.findViewById(R.id.txtRating);
            txtReviews = itemView.findViewById(R.id.txtReviews);


        }
    }
}
