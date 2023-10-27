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

public class GetStarted3 extends AppCompatActivity {

    AppCompatButton ContinueBtn;
    TextView TxtGet3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get3);


        ContinueBtn = findViewById(R.id.ContinueBtn);

        String first = "Good";
        String second = " Food'S";

        ContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetStarted3.this, SignUpLogin.class);
                startActivity(intent);
                finishAffinity();
            }
        });


        TxtGet3 = findViewById(R.id.TxtGet3);
        TxtGet3.setText(first + second, TextView.BufferType.SPANNABLE);
        Spannable s = (Spannable) TxtGet3.getText();
        int start = first.length();
        int end = start + second.length();
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.red)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


    }
}