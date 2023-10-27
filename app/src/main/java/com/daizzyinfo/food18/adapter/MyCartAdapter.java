package com.daizzyinfo.food18.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daizzyinfo.food18.model.MyCartModel;
import com.daizzyinfo.food18.R;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.VHMYCart> {


    List<MyCartModel> myCartModel;
    Context context;

    int count = 0;

    public MyCartAdapter(List<MyCartModel> myCartModel, Context context) {
        this.myCartModel = myCartModel;
        this.context = context;
    }

    @NonNull
    @Override
    public VHMYCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_my_cart_items,parent,false);
        return new VHMYCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHMYCart holder, @SuppressLint("RecyclerView") int position) {
        holder.MyCartImg.setImageResource(myCartModel.get(position).getMyCartImg());
        holder.MyCartFName.setText(myCartModel.get(position).getMyCFName());
        holder.MyCartExtraF.setText(myCartModel.get(position).getMCFExtra());
        holder.MyCartFPrice.setText(myCartModel.get(position).getMCFPrice());

        holder.imgPlusItm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;

              holder.TxtNumberItm.setText(""+ count);
            }
        });

        holder.imgMinusItm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count<=0) count = 0;
                else  count--;



                holder.TxtNumberItm.setText(""+count);
            }
        });


        holder.imgRemoveItm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCartModel.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, myCartModel.size());
            }
        });



    }

    @Override
    public int getItemCount() {
        return myCartModel.size();
    }

    public class VHMYCart extends RecyclerView.ViewHolder {

        ImageView MyCartImg,imgPlusItm,imgMinusItm,imgRemoveItm;
        TextView MyCartFName,MyCartExtraF,MyCartFPrice,txt_Add_Itm,TxtNumberItm;


        public VHMYCart(@NonNull View itemView) {
            super(itemView);

            MyCartImg=itemView.findViewById(R.id.MyCartImg);
            MyCartFName=itemView.findViewById(R.id.MyCartFName);
            MyCartExtraF=itemView.findViewById(R.id.MyCartExtraF);
            MyCartFPrice=itemView.findViewById(R.id.MyCartFPrice);

            imgPlusItm=itemView.findViewById(R.id.imgPlusItm);
            imgMinusItm=itemView.findViewById(R.id.imgMinusItm);
            txt_Add_Itm=itemView.findViewById(R.id.txt_Add_Itm);
            TxtNumberItm=itemView.findViewById(R.id.TxtNumberItm);
            imgRemoveItm=itemView.findViewById(R.id.imgRemoveItm);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });



        }
    }
}
