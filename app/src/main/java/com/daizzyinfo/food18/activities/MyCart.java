package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.adapter.MyCartAdapter;
import com.daizzyinfo.food18.model.MyCartModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyCart extends AppCompatActivity {

    AppCompatButton BtnCheckOut,BtnApply;
    RecyclerView recyclerMyCart;

    EditText EdtPromoCode;
    List<MyCartModel> models = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        recyclerMyCart = findViewById(R.id.recyclerMyCart);
        BtnCheckOut = findViewById(R.id.BtnCheckOut);
        BtnApply = findViewById(R.id.BtnApply);
        EdtPromoCode = findViewById(R.id.EdtPromoCode);

        initOnClickListener();
        openToolbar();

        models.add(new MyCartModel(R.drawable.food,"Pizza","CheeseCornExtra","4.5","Rs. 199"));
        models.add(new MyCartModel(R.drawable.food1,"Pizza","CheeseCornExtra","4.5","Rs. 199"));
        models.add(new MyCartModel(R.drawable.food2,"Pizza","CheeseCornExtra","4.5","Rs. 199"));
        models.add(new MyCartModel(R.drawable.food4,"Pizza","CheeseCornExtra","4.5","Rs. 199"));

        MyCartAdapter adapter = new MyCartAdapter(models,MyCart.this);
        recyclerMyCart.setAdapter(adapter);


        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerMyCart.setLayoutManager(manager);


    }

    private void openToolbar() {

        View view = findViewById(R.id.headerMyCart);
        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        TxtHeader.setText("My Cart");
        ImageView ImgBack=view.findViewById(R.id.ImgBack);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    protected void initOnClickListener() {
        BtnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCart.this,CheckOut.class);
                startActivity(intent);
            }
        });

    }






}