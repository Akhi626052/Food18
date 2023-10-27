package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.adapter.MyAddAdapter;
import com.daizzyinfo.food18.model.MyAddModel;

import java.util.ArrayList;
import java.util.List;

public class MyAddress extends AppCompatActivity {
     AppCompatButton Btn_Add_New_Address;
     RecyclerView RVMyAdd;

      ArrayList<String> arr ;
     LinearLayout LnBgMyAddress;
     List<MyAddModel> model;

    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        RVMyAdd=findViewById(R.id.RvMyAdd);
        Btn_Add_New_Address=findViewById(R.id.Btn_Add_New_Address);
        LnBgMyAddress=findViewById(R.id.LnBgMyAddress);

        openToolBar();
        initOnClickListener();

        MyAddAdapter myAddAdapter = new MyAddAdapter(model,this);
        RVMyAdd.setAdapter(myAddAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RVMyAdd.setLayoutManager(manager);

        getData();


    }

    private void getData() {

        Intent intent = getIntent();

        String name = intent.getStringExtra("Name");
        String state = intent.getStringExtra("State");
        String address = intent.getStringExtra("Address");
        String city = intent.getStringExtra("City");
        String mobile = intent.getStringExtra("Mobile");



              model = new ArrayList<>();
//
//        arr = new ArrayList<>();
//
//       model.add(new MyAddModel(name.trim()+"",
//                          mobile.trim()+"",address.trim()+" ",
//                                  state.trim()+",",city.trim()+","));




        Log.e("MyAddress","on Good   "+intent.getStringExtra("Name"));
        Log.e("MyAddress","on Good   "+ intent.getStringExtra("State"));
        Log.e("MyAddress","on Good   "+intent.getStringExtra("Address"));
        Log.e("MyAddress","on Good   "+ intent.getStringExtra("City"));
        Log.e("MyAddress","on Good   "+intent.getStringExtra("Mobile"));


    }


    protected void initOnClickListener()
    {
        Btn_Add_New_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddress.this, AddNewAddress.class);
                startActivity(intent);
            }
        });
    }


    protected void openToolBar(){

        View view = findViewById(R.id.Header);
        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        TxtHeader.setText("My Address");
        ImageView ImgBack=view.findViewById(R.id.ImgBack);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),BottomNavigation.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyAddress.this, BottomNavigation.class);
        startActivity(intent);
        super.onBackPressed();

    }
}


//        SharedPreferences sharedPreferences = getSharedPreferences("myPreference", 0);
//        String userName =sharedPreferences.getString("Name","");
//        String address  =sharedPreferences.getString("Address","");
//        String city  = sharedPreferences.getString("City","");
//        String state = sharedPreferences.getString("State","");
//        String number = sharedPreferences.getString("Number","");
//         if(state.equals("State")||state.isEmpty()||address.isEmpty()||city.isEmpty()||city.equals("City")||userName.isEmpty()||number.isEmpty())
//         {
//             LnBgMyAddress.setVisibility(View.GONE);
//         }
//         else
//         {
//                   arr = new ArrayList<>();
//
//                     arr.add(String.valueOf(myAddModel.add(new MyAddModel(userName.trim(),
//                     address.trim()+"\n"+city.trim()+" "+
//                             state.trim()+"\n"+number.trim()))));
//                          LnBgMyAddress.setVisibility(View.VISIBLE);
//         }