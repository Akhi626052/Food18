package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daizzyinfo.food18.R;

public class TrackOrder extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);


       openToolbar("Track Order");

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