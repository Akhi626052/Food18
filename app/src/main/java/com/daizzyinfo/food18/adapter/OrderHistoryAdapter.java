package com.daizzyinfo.food18.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.activities.MyCart;
import com.daizzyinfo.food18.activities.OrderDetails;
import com.daizzyinfo.food18.activities.RateUs;
import com.daizzyinfo.food18.model.OrderHistoryModel;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryVH>{

    Context context;
    List<OrderHistoryModel> model;

    public OrderHistoryAdapter(Context context, List<OrderHistoryModel> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public OrderHistoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_order_history_items,parent,false);
        return new OrderHistoryVH(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull OrderHistoryVH holder, int position) {

        holder.txtOrderId.setText(model.get(position).getOrderId());
        holder.txtOrderDate.setText(model.get(position).getOrderDate());
        holder.TxtOrderPrice.setText(model.get(position).getOrderPrice());
        holder.TxtOrderAddress.setText(model.get(position).getOrderAddress());
        holder.TxtOrderQty.setText(model.get(position).getOrderItmCount());
        holder.TxtOrderStatus.setText(model.get(position).getOrderStatus());

        if(holder.TxtOrderStatus.getText().toString().trim().equals("Delivery Completed"))
        {
            holder.LnBgHistory.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.green));
            holder.TxtOrderStatus.setText("Delivery Completed");
            holder.TxtOrderStatus.setTextColor(ContextCompat.getColor(context, R.color.green));
            holder.TxtReOrder.setText("Feedback");
        }

        else if (holder.TxtOrderStatus.getText().toString().trim().equals("Delivery Failed")) {

            holder.LnBgHistory.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.red));
            holder.TxtOrderStatus.setText("Delivery Failed");
            holder.TxtOrderStatus.setTextColor(ContextCompat.getColor(context, R.color.red));
            holder.TxtReOrder.setText("Re-Order");

        }




        holder.TxtViewDetails.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
              if(holder.TxtOrderStatus.getText().toString().trim().equals("Delivery Completed"))
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
              else if (holder.TxtOrderStatus.getText().toString().trim().equals("Delivery Failed")) {
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

        if(holder.TxtReOrder.getText().toString().equals("Re-Order"))
        {
            holder.TxtReOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MyCart.class);
                    context.startActivity(intent);
                }
            });

        }else if(holder.TxtReOrder.getText().toString().equals("Feedback")) {
            holder.TxtReOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RateUs.class);
                    context.startActivity(intent);
                }
            });

        }


    }


    @Override
    public int getItemCount() {
        return model.size();
    }

    public class OrderHistoryVH extends RecyclerView.ViewHolder {

        TextView txtOrderId, txtOrderDate,TxtOrderPrice,TxtRating, TxtOrderAddress,TxtOrderQty,TxtOrderStatus,TxtReOrder,TxtViewDetails;

         LinearLayout LnBgHistory;


        public OrderHistoryVH(@NonNull View itemView) {
            super(itemView);

            txtOrderId=itemView.findViewById(R.id.txtOrderId);
            txtOrderDate=itemView.findViewById(R.id.txtOrderDate);
            TxtOrderPrice=itemView.findViewById(R.id.TxtOrderPrice);
            TxtOrderAddress=itemView.findViewById(R.id.TxtOrderAddress);
            TxtOrderQty=itemView.findViewById(R.id.TxtOrderQty);
            TxtOrderStatus=itemView.findViewById(R.id.TxtOrderStatus);
            LnBgHistory=itemView.findViewById(R.id.LnBgHistory);
            TxtRating=itemView.findViewById(R.id.TxtRating);
            TxtReOrder=itemView.findViewById(R.id.TxtReOrder);
            TxtViewDetails=itemView.findViewById(R.id.TxtViewDetails);






        }
    }


}
