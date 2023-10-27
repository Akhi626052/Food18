package com.daizzyinfo.food18.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.model.NotifyModels;


import java.util.List;

public class NotifyAdapter extends  RecyclerView.Adapter<NotifyAdapter.NotificationVH> {

    Context mCtx;
    List<NotifyModels> model;

    public NotifyAdapter(Context mCtx, List<NotifyModels> model) {
        this.mCtx = mCtx;
        this.model = model;
    }

    @NonNull
    @Override
    public NotificationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(mCtx).inflate(R.layout.custom_notification_layout,parent,false);
        return new NotificationVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationVH holder, int position) {

        holder.txtNotifyHead.setText(model.get(position).getTitle());
        holder.txtNotifyMess.setText(model.get(position).getMessage());


    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class NotificationVH extends RecyclerView.ViewHolder {


        TextView txtNotifyHead,txtNotifyMess;

        public NotificationVH(@NonNull View itemView) {
            super(itemView);

            txtNotifyMess=itemView.findViewById(R.id.txtNotifyMess);
            txtNotifyHead=itemView.findViewById(R.id.txtNotifyHead);

        }
    }

}
