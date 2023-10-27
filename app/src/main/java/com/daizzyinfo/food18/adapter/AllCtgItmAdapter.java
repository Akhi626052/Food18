package com.daizzyinfo.food18.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.activities.SubCategoriesActivity;
import com.daizzyinfo.food18.viewModel.Categories.CatResponse;
import com.daizzyinfo.food18.viewModel.Categories.SubCategoryModel;

import java.util.List;

public class AllCtgItmAdapter extends RecyclerView.Adapter<AllCtgItmAdapter.VHAllCtgItm> {

    List<CatResponse> model;
    Context context;

    public AllCtgItmAdapter(List<CatResponse> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public AllCtgItmAdapter.VHAllCtgItm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_all_categories_item,parent,false);
        return new VHAllCtgItm(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHAllCtgItm holder, int position) {


        holder.txtFNameCtg.setText(model.get(position).getData().get(position).getName());
        holder.txtTotalProduct.setText("("+String.valueOf(model.get(position).getData().get(position).getTotalProduct())+")");

        String url = String.valueOf(model.get(position).getData().get(position).getImage());

        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.food4)
                .into(holder.imgFoodCtg);


        String ID = model.get(position).getData().get(position).getId();


        holder.cardCatItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubCategoriesActivity.class);
                intent.putExtra("ID",ID);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class VHAllCtgItm extends RecyclerView.ViewHolder {

        ImageView imgFoodCtg;
        TextView txtFNameCtg,txtTotalProduct;

        CardView cardCatItem;

        public VHAllCtgItm(@NonNull View itemView) {
            super(itemView);
            imgFoodCtg=itemView.findViewById(R.id. imgFoodCtg);
            txtFNameCtg=itemView.findViewById(R.id.txtFNameCtg);
            txtTotalProduct=itemView.findViewById(R.id.txtTotalProduct);
            cardCatItem=itemView.findViewById(R.id.cardCatItem);
        }
    }

}
