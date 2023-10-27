package com.daizzyinfo.food18.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.activities.MyCart;
import com.daizzyinfo.food18.fragments.AllItemsBottomSheet;
import com.daizzyinfo.food18.viewModel.ProductList.ProductListResponse;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;
import java.util.Objects;

public class AllItmAdapter extends RecyclerView.Adapter<AllItmAdapter.VHAllPopItm>{
    List<ProductListResponse> model;
    Context context;

    int count ;

    public AllItmAdapter(List<ProductListResponse> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public VHAllPopItm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_all_popular_item,parent,false);
        return new VHAllPopItm(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHAllPopItm holder, @SuppressLint("RecyclerView") int position) {
              holder.txtFName.setText(String.valueOf(model.get(position).getData().getNormal().get(position).getProductName()));
              holder.txtRs.setText(String.valueOf(" ₹ "+model.get(position).getData().getNormal().get(position).getPrice()));
              holder.txtExtraFName.setText(String.valueOf(model.get(position).getData().getNormal().get(position).getCategoryName()));

              String url=  String.valueOf(model.get(position).getData().getNormal().get(position).getProductImage());
              Glide
                      .with(context)
                      .load(url)
                      .centerCrop()
                      .placeholder(R.drawable.food4)
                      .into(holder.imgFood);





        holder.lnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String ProductId = String.valueOf(model.get(position).getData().getNormal().get(position).getProductId());
                bundle.putString("ProductId", ProductId );
                AllItemsBottomSheet fm = new AllItemsBottomSheet();
                fm.setArguments(bundle);
                fm.show(((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction(), fm.getTag());
            }
        });


        count = Integer.parseInt(holder.txtOrdQty.getText().toString());

        holder.txtBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("productId",model.get(position).getData().getNormal().get(position).getProductId());

                 if(holder.txtOrdQty.equals("0"))
                 {
                     holder.rlPlusMinusBtn.setVisibility(View.GONE);
                     holder.txtBtnAdd.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     holder.rlPlusMinusBtn.setVisibility(View.VISIBLE);
                     holder.txtBtnAdd.setVisibility(View.GONE);
                 }

            }
        });


        holder.imgPlusItm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                holder.txtOrdQty.setText(String.valueOf(count));

                if (count > 0) {
                    holder.rlPlusMinusBtn.setVisibility(View.VISIBLE);
                    holder.txtBtnAdd.setVisibility(View.GONE);
                }
            }
        });

        holder.imgMinusItm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 0) {
                    count--;
                    holder.txtOrdQty.setText(String.valueOf(count));

                    if (count == 0) {
                        holder.rlPlusMinusBtn.setVisibility(View.GONE);
                        holder.txtBtnAdd.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return model.size();
    }
    public class  VHAllPopItm extends RecyclerView.ViewHolder {
        ShapeableImageView imgFood;
        ImageView imgMinusItm,imgPlusItm;
        TextView txtFName,txtExtraFName,txtRate,txtRs,txtBtnAdd,txtOrdQty;
        LinearLayout lnProduct;
        RelativeLayout rlPlusMinusBtn;
        public VHAllPopItm(@NonNull View itemView) {
            super(itemView);
            imgFood=itemView.findViewById(R.id.imgFood);
            txtFName=itemView.findViewById(R.id.txtFName);
            txtExtraFName=itemView.findViewById(R.id.txtExtraFName);
            txtRate=itemView.findViewById(R.id.txtRate);
            txtRs=itemView.findViewById(R.id.txtRs);
            txtBtnAdd=itemView.findViewById(R.id.txtBtnAdd);
            lnProduct=itemView.findViewById(R.id.lnProduct);
            txtOrdQty=itemView.findViewById(R.id.txtOrdQty);
            imgMinusItm=itemView.findViewById(R.id.imgMinusItm);
            imgPlusItm=itemView.findViewById(R.id.imgPlusItm);
            rlPlusMinusBtn=itemView.findViewById(R.id.rlPlusMinusBtn);


        }
    }
}
