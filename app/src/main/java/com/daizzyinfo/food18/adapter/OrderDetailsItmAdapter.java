package com.daizzyinfo.food18.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.activities.OrderDetails;
import com.daizzyinfo.food18.model.OrderDetailsItmModel;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class OrderDetailsItmAdapter extends RecyclerView.Adapter<OrderDetailsItmAdapter.OrderDetailsVH> {

  Context context;
  List<OrderDetailsItmModel> model;

    public OrderDetailsItmAdapter(Context context, List<OrderDetailsItmModel> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public OrderDetailsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_order_details_itm,parent,false);
        return new OrderDetailsVH(view);
    }



    @Override
    public void onBindViewHolder(@NonNull OrderDetailsVH holder, int position) {

     holder.imgFd.setImageResource(model.get(position).getFimg());
     holder.txtFName.setText(model.get(position).getFname());
        holder.txtExtraF_Name.setText(model.get(position).getExtraFname());
        holder.txtOrdQty.setText(model.get(position).getQuantity());
        holder.txtPrice.setText(model.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class OrderDetailsVH extends RecyclerView.ViewHolder {

   ShapeableImageView imgFd;
   TextView txtFName,txtExtraF_Name,txtPrice,txtOrdQty;



        public OrderDetailsVH(@NonNull View itemView) {
            super(itemView);
            imgFd=itemView.findViewById(R.id.imgFd);
            txtFName=itemView.findViewById(R.id.txtFName);
            txtExtraF_Name=itemView.findViewById(R.id.txtExtraF_Name);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtOrdQty=itemView.findViewById(R.id.txtOrdQty);

        }
    }
}
