package com.daizzyinfo.food18.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.activities.OrderDetails;
import com.daizzyinfo.food18.activities.RateUs;
import com.daizzyinfo.food18.activities.TrackOrder;
import com.daizzyinfo.food18.model.OrderUpcomingModel;

import java.util.List;

public class OrderUpcomingAdapter extends RecyclerView.Adapter<OrderUpcomingAdapter.OrderUpcomingVH>{

  Context context;
  List<OrderUpcomingModel> model;

    public OrderUpcomingAdapter(Context context, List<OrderUpcomingModel> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public OrderUpcomingVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.custom_order_upcoming_items,parent,false);
        return new OrderUpcomingVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderUpcomingVH holder, int position) {

        holder.txtOrderId.setText(model.get(position).getOrderId());
        holder.txtOrderDate.setText(model.get(position).getOrderDate());
        holder.TxtOrderPrice.setText(model.get(position).getOrderPrice());
        holder.TxtOrderAddress.setText(model.get(position).getOrderAddress());
        holder.TxtOrderQty.setText(model.get(position).getOrderItmCount());
        holder.TxtOrderStatus.setText(model.get(position).getOrderStatus());

         holder.TxtViewDetails.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(holder.TxtOrderStatus.getText().toString().trim().equals("Upcoming"))
                 {
                     Intent intent = new Intent(context, OrderDetails.class);
                     intent.putExtra("OrderId",holder.txtOrderId.getText().toString().trim());
                     intent.putExtra("OrderDate",holder.txtOrderDate.getText().toString().trim());
                     intent.putExtra("OrderPrice",holder.TxtOrderPrice.getText().toString().trim());
                     intent.putExtra("OrderQty",holder.TxtOrderQty.getText().toString().trim());
                     intent.putExtra("OrderStatus",holder.TxtOrderStatus.getText().toString().trim());
                     intent.putExtra("OrderAddress",holder.TxtOrderAddress.getText().toString().trim());
                     context.startActivity(intent);
                 }
             }
         });

         holder.txtTrackOrder.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(context, TrackOrder.class);
                 context.startActivity(intent);
             }
         });

       holder.TxtRating.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context, RateUs.class);
               context.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class OrderUpcomingVH extends RecyclerView.ViewHolder {

      TextView txtOrderId, txtOrderDate,TxtOrderPrice,TxtRating, TxtOrderAddress,TxtOrderQty,TxtOrderStatus,txtTrackOrder,TxtViewDetails;


        public OrderUpcomingVH(@NonNull View itemView) {
            super(itemView);
            txtOrderId=itemView.findViewById(R.id.txtOrderId);
            txtOrderDate=itemView.findViewById(R.id.txtOrderDate);
            TxtOrderPrice=itemView.findViewById(R.id.TxtOrderPrice);
            TxtOrderAddress=itemView.findViewById(R.id.TxtOrderAddress);
            TxtOrderQty=itemView.findViewById(R.id.TxtOrderQty);
            TxtOrderStatus=itemView.findViewById(R.id.TxtOrderStatus);

            TxtRating=itemView.findViewById(R.id.TxtRating);
            txtTrackOrder=itemView.findViewById(R.id.txtTrackOrder);
            TxtViewDetails=itemView.findViewById(R.id.TxtViewDetails);



        }
    }
}
