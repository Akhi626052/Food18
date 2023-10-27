package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.daizzyinfo.food18.R;

public class GetStarted2 extends AppCompatActivity {

     TextView TxtGet2;
    AppCompatButton SkipBtn,ContinueBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get2);

        SkipBtn = findViewById(R.id.SkipBtn);
        ContinueBtn = findViewById(R.id.ContinueBtn);




        SkipBtn=findViewById(R.id.SkipBtn);
        SkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetStarted2.this, GetStarted3.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        ContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetStarted2.this, GetStarted3.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        TxtGet2 = findViewById(R.id.TxtGet2);
        String first = "Good";
        String second = " Food'S";
        TxtGet2.setText(first + second, TextView.BufferType.SPANNABLE);
        Spannable s = (Spannable) TxtGet2.getText();
        int start = first.length();
        int end = start + second.length();
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.red)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);




    }


}