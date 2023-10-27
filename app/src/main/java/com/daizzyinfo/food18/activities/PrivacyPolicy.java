package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daizzyinfo.food18.R;

public class PrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);



        openToolBar();


    }
    protected void openToolBar(){

        View view = findViewById(R.id.Header);

        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        TxtHeader.setText("Privacy Policy");
        ImageView ImgBack=view.findViewById(R.id.ImgBack);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




}