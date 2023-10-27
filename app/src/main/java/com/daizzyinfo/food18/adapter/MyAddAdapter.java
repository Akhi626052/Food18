package com.daizzyinfo.food18.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daizzyinfo.food18.model.MyAddModel;
import com.daizzyinfo.food18.R;

import java.util.List;

public class MyAddAdapter extends RecyclerView.Adapter<MyAddAdapter.VHMyAddress>{

    List<MyAddModel> model;
    Context context;
//    private int lastPosition = -1;

    public MyAddAdapter(List<MyAddModel> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public VHMyAddress onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_my_address_item,parent,false);
        return new VHMyAddress(view);
    }

    @Override
    @SuppressLint("RecyclerView")
    public void onBindViewHolder(@NonNull VHMyAddress holder,int position) {

        holder.txtUserName.setText(model.get(position).getUsername());
        holder.txtState.setText(model.get(position).getState());
        holder.txtDistrict.setText(model.get(position).getCity());
        holder.txtMobNum.setText(model.get(position).getNumber());
        holder.txtAddress.setText(model.get(position).getAddress());



        holder.Img_DeleteAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                model.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, model.size());
            }
        });

//           setAnimation(holder.itemView,position);
    }

//    private void setAnimation(View viewToAnimate, int position)
//    {
//        if (position > lastPosition)
//        {
//            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
//
//            viewToAnimate.startAnimation(animation);
//            lastPosition = position;
//        }
//    }

    @Override
    public int getItemCount() {
        if (model != null) {
            return model.size();
        } else {
            return 0;
        }
    }

    public class VHMyAddress extends RecyclerView.ViewHolder {

        TextView txtUserName,txtAddress,txtDistrict,txtState,txtMobNum;

        ImageView Img_DeleteAdd;

        public VHMyAddress(@NonNull View itemView) {
            super(itemView);
            txtUserName=itemView.findViewById(R.id.txtUserName);
            txtAddress=itemView.findViewById(R.id.txtAddress);
            txtDistrict=itemView.findViewById(R.id.txtDistrict);
            txtState=itemView.findViewById(R.id.txtState);
            txtMobNum=itemView.findViewById(R.id.txtMobNum);


            Img_DeleteAdd=itemView.findViewById(R.id.Img_DeleteAdd);

        }


    }

}
