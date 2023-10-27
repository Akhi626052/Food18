package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.adapter.NotifyAdapter;
import com.daizzyinfo.food18.model.NotifyModels;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {


    RecyclerView RVNotify;

    List<NotifyModels> model= new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        RVNotify=findViewById(R.id.RVNotify);


        openToolBar();


          model.add(new NotifyModels("Publishing and graphic design","Lorem Ipsum is simply dummy text of the print and typesetting industry."));
           model.add(new NotifyModels("Publishing and graphic design","Lorem Ipsum is simply dummy text of the print and typesetting industry."));


            NotifyAdapter adapter = new NotifyAdapter(this,model);
           RVNotify.setAdapter(adapter);

            LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RVNotify.setLayoutManager(manager);







        }

    protected void openToolBar(){

        View view = findViewById(R.id.Header);

        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        TxtHeader.setText("Notifications");
        ImageView ImgBack=view.findViewById(R.id.ImgBack);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}