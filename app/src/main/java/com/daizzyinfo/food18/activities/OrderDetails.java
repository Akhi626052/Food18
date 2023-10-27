package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.adapter.OrderDetailsItmAdapter;
import com.daizzyinfo.food18.model.OrderDetailsItmModel;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;
import com.thecode.aestheticdialogs.OnDialogClickListener;

import java.util.ArrayList;
import java.util.List;


public class OrderDetails extends AppCompatActivity {

     AppCompatButton RatingUs, Btn_Reference;
     TextView order_Id,order_Date, Coming_From,order_qty,Order_Address,order_Add;
     LinearLayout Ln_Status;

        RecyclerView RvOrderDetailsItm;

        List<OrderDetailsItmModel> model;
         OrderDetailsItmAdapter adapter;

    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);


        Coming_From = findViewById(R.id.ComingFrom);

        RatingUs= findViewById(R.id.RatingUs);
        Btn_Reference=findViewById(R.id.Btn_Reference);
        order_Id = findViewById(R.id.order_Id);
        order_Date=findViewById(R.id.order_Date);
        Ln_Status  = findViewById(R.id.Ln_Status);
        Coming_From = findViewById(R.id.Coming_From);
        Order_Address=findViewById(R.id.Order_Address);
        order_Add=findViewById(R.id.order_Add);
        RvOrderDetailsItm=findViewById(R.id.RvOrderDetailsItm);



        //Order Details
           model = new ArrayList<>();
        adapter = new OrderDetailsItmAdapter(this,model);
        RvOrderDetailsItm.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RvOrderDetailsItm.setLayoutManager(manager);

        model.add(new OrderDetailsItmModel(R.drawable.food3,"Pizza","Extra Cheese Pizza","Rs. 299","Qty 01"));
        model.add(new OrderDetailsItmModel(R.drawable.food4,"Pizza","Extra Cheese Pizza","Rs. 299","Qty 01"));








        initOnClickListener();
        openToolBar();

    }

    protected void initOnClickListener(){

        Intent intent = getIntent();
        String ComingFrom1 =   intent.getStringExtra("OrderStatus");
        String  order_id = intent.getStringExtra("OrderId");
        String  order_date = intent.getStringExtra("OrderDate");

        String  Order_address = intent.getStringExtra("OrderAddress");

        order_Id.setText(order_id);
        order_Date.setText(order_date);

        Order_Address.setText(Order_address);
        order_Add.setText(Order_address);



        if(ComingFrom1.equals("Upcoming"))
        {
            Coming_From.setTextColor(getResources().getColor(R.color.black));
            Coming_From.setText("Upcoming");
            Ln_Status.getBackground().setTint(getResources().getColor(R.color.yellow));
            order_Id.setTextColor(getResources().getColor(R.color.black));
            order_Date.setTextColor(getResources().getColor(R.color.black));
            Btn_Reference.setText("Cancel order");
            RatingUs.setVisibility(View.INVISIBLE);

        }
        else if(ComingFrom1.equals("Delivery Failed"))
        {
            Coming_From.setTextColor(getResources().getColor(R.color.white));
            Coming_From.setText("Delivery failed");
            order_Id.setTextColor(getResources().getColor(R.color.white));
            order_Date.setTextColor(getResources().getColor(R.color.white));
            Ln_Status.getBackground().setTint(getResources().getColor(R.color.red));
            Btn_Reference.setText("Re Order");
            RatingUs.setVisibility(View.GONE);

        }
        else if(ComingFrom1.equals("Delivery Completed"))
        {
            Coming_From.setTextColor(getResources().getColor(R.color.white));
            Coming_From.setText("Delivery Success");
            Ln_Status.getBackground().setTint(getResources().getColor(R.color.green));
            order_Id.setTextColor(getResources().getColor(R.color.white));
            order_Date.setTextColor(getResources().getColor(R.color.white));
            Btn_Reference.setText("View invoice");
        }




        Btn_Reference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText =
                        Btn_Reference.getText().toString();

                if(btnText.equals("Cancel order"))
                {

                    new AestheticDialog.Builder(OrderDetails.this, DialogStyle.FLAT, DialogType.SUCCESS)
                            .setTitle("Are you sure you want\n to cancel the order?")
                            .setMessage("sure !")
                            .setCancelable(false)
                            .setDarkMode(false)
                            .setGravity(Gravity.CENTER)
                            .setAnimation(DialogAnimation.SLIDE_UP)
                            .setOnClickListener(new OnDialogClickListener() {
                                @Override
                                public void onClick(AestheticDialog.Builder dialog) {
                                    dialog.dismiss();

                                }
                            })
                            .show();

                }
                else if(btnText.equals("Re Order"))
                {
                    Intent intent = new Intent(OrderDetails.this,MyCart.class);
                    startActivity(intent);

                }
                else if(btnText.equals("View invoice"))
                {


                }
            }
        });

    }

     protected void openToolBar(){

        View view = findViewById(R.id.Header);

         TextView TxtHeader =view.findViewById(R.id.TxtHeader);
         TxtHeader.setText("Order Details");
         ImageView ImgBack=view.findViewById(R.id.ImgBack);
         ImgBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });




     }





}