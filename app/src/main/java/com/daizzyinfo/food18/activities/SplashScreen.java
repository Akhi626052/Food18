package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.utils.Constant;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "SplashScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        Log.e(TAG, "Token - " + Constant.getToken());
        if(!TextUtils.isEmpty(Constant.getToken())){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this, BottomNavigation.class);
                    startActivity(intent);
                    finish();
                }
            },2000);

        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this, SignUpLogin.class);
                    startActivity(intent);
                    finish();
                }
            },2000);

        }
    }
}