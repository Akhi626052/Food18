package com.daizzyinfo.food18.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.viewModel.Categories.CatResponse;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CtgAdapter extends RecyclerView.Adapter<CtgAdapter.VHCtg> {

     List <CatResponse> model;
     Context context;

    public CtgAdapter(List<CatResponse> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public VHCtg onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_categories_item,parent,false);
        return new VHCtg(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull VHCtg holder, int position) {

        holder.CtgFName.setText(String.valueOf(model.get(position).getData().get(position).getName()));

        String url =String.valueOf(model.get(position).getData().get(position).getImage());

        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.burger)
                .into(holder.CtgImg);


        if(position==0)
        {
            holder.lnBg.setBackground(context.getResources().getDrawable(R.drawable.ctg_item_bg));
            holder.CtgFName.setTextColor(context.getResources().getColor(R.color.white));
        }
        else if(position>0)
        {
            holder.lnBg.setBackground(context.getResources().getDrawable(R.drawable.ctg_itm_bg_2));
            holder.CtgFName.setTextColor(context.getResources().getColor(R.color.black));
        }

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class VHCtg extends RecyclerView.ViewHolder {

        CircleImageView CtgImg;
        TextView CtgFName;
        LinearLayout lnBg;

        public VHCtg(@NonNull View itemView) {
            super(itemView);

            CtgImg = itemView.findViewById(R.id.CtgImg);
            CtgFName=itemView.findViewById(R.id.CtgFName);
            lnBg=itemView.findViewById(R.id.lnBg);

        }
    }
}
