package com.daizzyinfo.food18.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.model.SearchModel;

import java.util.List;

public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.VHSearch>{

     List<SearchModel> searchModel;
     Context context;

    public SearchAdapter(List<SearchModel> searchModel, Context context) {
        this.searchModel = searchModel;
        this.context = context;
    }

    @NonNull
    @Override
    public VHSearch onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_search_item,parent,false);
        return new VHSearch(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHSearch holder, int position) {
        holder.SImg.setImageResource(searchModel.get(position).getsImg());
        holder.SFName.setText(searchModel.get(position).getsFName());
        holder.SExtraF.setText(searchModel.get(position).getsExtra());
        holder.SRating.setText(searchModel.get(position).getsRating());
        holder.SFPrice.setText(searchModel.get(position).getsPrice());


    }

    @Override
    public int getItemCount() {
        return searchModel.size();
    }

    public class VHSearch extends RecyclerView.ViewHolder {

         ImageView SImg;
         TextView SFName,SExtraF,SRating,SFPrice;

        public VHSearch(@NonNull View itemView) {
            super(itemView);

              SImg=itemView.findViewById(R.id.SImg);
            SFName=itemView.findViewById(R.id.SFName);
            SExtraF=itemView.findViewById(R.id.SExtraF);
            SRating=itemView.findViewById(R.id.SRating);
            SFPrice=itemView.findViewById(R.id.SFPrice);



        }
    }
}
