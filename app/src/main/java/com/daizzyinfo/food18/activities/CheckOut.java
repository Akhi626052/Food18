package com.daizzyinfo.food18.activities;

import static com.daizzyinfo.food18.R.drawable.star_icon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.adapter.OrderDetailsItmAdapter;
import com.daizzyinfo.food18.model.OrderDetailsItmModel;

import java.util.ArrayList;
import java.util.List;

public class CheckOut extends AppCompatActivity {


     protected RadioButton RadioUPI,RadioCod;

     RecyclerView RvOCheckOutItm;
    List<OrderDetailsItmModel> model;
    OrderDetailsItmAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        openToolbar("Checkout");
        RadioCod=findViewById(R.id.RadioCod);
        RadioUPI=findViewById(R.id.RadioUPI);
        RvOCheckOutItm=findViewById(R.id.RvOCheckOutItm);

        initOnClickListener();

        //Order Details
        model = new ArrayList<>();
        adapter = new OrderDetailsItmAdapter(this,model);
        RvOCheckOutItm.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RvOCheckOutItm.setLayoutManager(manager);

        model.add(new OrderDetailsItmModel(R.drawable.food3,"Pizza","Extra Cheese Pizza","Rs. 299","Qty 01"));
        model.add(new OrderDetailsItmModel(R.drawable.food4,"Pizza","Extra Cheese Pizza","Rs. 299","Qty 01"));







    }


    protected void initOnClickListener() {

        RadioUPI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    CompoundButtonCompat.setButtonTintList(RadioUPI, ContextCompat.getColorStateList(CheckOut.this, R.color.red));
                }else {
                    CompoundButtonCompat.setButtonTintList(RadioUPI, ContextCompat.getColorStateList(CheckOut.this, R.color.black));
                }
            }
        });

        RadioCod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    CompoundButtonCompat.setButtonTintList(RadioCod, ContextCompat.getColorStateList(CheckOut.this, R.color.red));
                }else {
                    CompoundButtonCompat.setButtonTintList(RadioCod, ContextCompat.getColorStateList(CheckOut.this, R.color.black));
                }
            }
        });


    }







   public void openToolbar( String title ) {

        View view = findViewById(R.id.Header);
        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        ImageView ImgBack =view.findViewById(R.id.ImgBack);
        TxtHeader.setText(title);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }



}