package com.daizzyinfo.food18.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.daizzyinfo.food18.R;

public class Constant {
    public static final SharedPreferences sharedPreference = MyApplication.getContext().getSharedPreferences(Constant.MY_PREF_NAME, Context.MODE_PRIVATE);

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static final String Token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.NA.CFR8OrGMhT19A1umzYr6yKoQAVg_-9BfbPdSoOy_DRs";

    public static final String Path = "https://test-daizzyinfosys.com/food/api/";

    public static final String MY_PREF_NAME = "Foods_18";

    public static String getToken(){

        return sharedPreference.getString("Token","");

    }

    public static void putToken(String Token){

        SharedPreferences.Editor editor =sharedPreference.edit();
        editor.putString("Token",Token);

    }

    public static String getSetPasscode(){
        return sharedPreference.getString("SetPasscode","");
    }
    public static void clearAllData(){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.remove("Token");


        editor.commit();
        editor.clear();
        editor.apply();

    }


    }




