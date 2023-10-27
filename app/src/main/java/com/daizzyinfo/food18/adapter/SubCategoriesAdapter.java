package com.daizzyinfo.food18.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.viewModel.Categories.CatResponse;

import java.util.List;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.SubCatVH>{

    Context context;

    List<CatResponse> model ;

    public SubCategoriesAdapter(Context context, List<CatResponse> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public SubCatVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_sub_categories_item, parent, false);
        return new SubCatVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCatVH holder, int position) {

        holder.txtSCFoodName.setText(String.valueOf(model.get(position).getData().get(position).getChild().get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class SubCatVH extends RecyclerView.ViewHolder {

        TextView txtSCFoodName;
        public SubCatVH(@NonNull View itemView) {
            super(itemView);
            txtSCFoodName = itemView.findViewById(R.id.txtSCFoodName);
        }
    }

}
