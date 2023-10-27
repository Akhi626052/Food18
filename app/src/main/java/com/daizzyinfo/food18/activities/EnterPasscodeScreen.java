package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.databinding.ActivityEnterPasscodeScreenBinding;
import com.daizzyinfo.food18.retrofit.ApiRequest;
import com.daizzyinfo.food18.retrofit.RetrofitRequest;
import com.daizzyinfo.food18.utils.Constant;
import com.daizzyinfo.food18.viewModel.SendOtp.SendOTPResponse;
import com.daizzyinfo.food18.viewModel.loginWithPasscode.SignInResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterPasscodeScreen extends AppCompatActivity {
    ActivityEnterPasscodeScreenBinding binding;

    private static final String TAG = EnterPasscodeScreen.class.getSimpleName();
    String Mobile,Passcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_enter_passcode_screen);

         openToolBar();
         initOnClickListener();
         getIntentData();
         binding.pinViewPAss.requestPinEntryFocus();

    }
    private void getIntentData() {
        Intent intent = getIntent();
        if(intent!=null)
        {
            Mobile = intent.getStringExtra("Mobile");
        }

    }

    private void initOnClickListener() {


        binding.txtForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "Mobile : " + Mobile );

                SendOTPApi();



            }
        });

        binding.SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Passcode = binding.pinViewPAss.getValue().trim().toString();
                if(binding.pinViewPAss.getValue().isEmpty()||binding.pinViewPAss.getValue().length()<4)
                {
                    Toast.makeText(EnterPasscodeScreen.this, "Please enter the valid passcode", Toast.LENGTH_SHORT).show();
                }
                else {

                    LoginWithPasscodeAPI();

                }
            }
        });



    }

    private void LoginWithPasscodeAPI() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mobile",Mobile);
        jsonObject.addProperty("password",Passcode);

        ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
        Call<SignInResponse> call = apiRequest.LoginWithPasscodeAPI(jsonObject);
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {

                if(response.body()!=null)
                {
                    Log.e(TAG, "onResponse: " + response.code());

                    if(response.code()==200)
                    {
                        SignInResponse signInResponse = response.body();
                       String message = signInResponse.getMessage();
                       boolean status = signInResponse.getStatus();

                       if(status){

                          String Token  = signInResponse.getData().getToken();
                          Log.e(TAG,"Token  - " + Token);
                           Toast.makeText(EnterPasscodeScreen.this, message, Toast.LENGTH_SHORT).show();
                           SharedPreferences preferences = getSharedPreferences(Constant.MY_PREF_NAME,MODE_PRIVATE);
                           SharedPreferences.Editor editor = preferences.edit();
                           editor.putString("Token",Token).apply();
                           editor.commit();

                           Intent intent = new Intent(getApplicationContext(), BottomNavigation.class);
                           startActivity(intent);

                       }



                    } else if (response.code()==401) {


                    }else if (response.code()==404) {


                    }else if (response.code()==500) {


                    }else {
                        Toast.makeText(EnterPasscodeScreen.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }


                }
                else {
                    Toast.makeText(EnterPasscodeScreen.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {

                Toast.makeText(EnterPasscodeScreen.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"On Failure -"+t.getMessage());



            }
        });


    }

    private void openToolBar(){
        View view = findViewById(R.id.TxtHeaderPasscode);
        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        TxtHeader.setText("Passcode");
        ImageView ImgBack=view.findViewById(R.id.ImgBack);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void SendOTPApi(){

        Log.e(TAG, " Mobile Number - " + Mobile);

        ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
        Call<SendOTPResponse> call = apiRequest.sendOtpAPI(Mobile);

        call.enqueue(new Callback<SendOTPResponse>() {
            @Override
            public void onResponse(Call<SendOTPResponse> call, Response<SendOTPResponse> response) {
                Log.e(TAG, "onResponse code - " + response.code());
                if(response.body() != null){

                    if(response.code()==200){

                        SendOTPResponse sendOTPResponse = response.body();
                        String message = sendOTPResponse.getMsg();
                        if(sendOTPResponse.getStatus()){
                            String OTP = String.valueOf(sendOTPResponse.getOtp());
                            Log.e(TAG, "OTP - " + OTP);
                            Intent intent = new Intent(EnterPasscodeScreen.this, Verification.class);
                            intent.putExtra("ComingFrom",EnterPasscodeScreen.class.getSimpleName());
                            intent.putExtra("Mobile",Mobile);
                            intent.putExtra("OTP",OTP);
                            startActivity(intent);
                        }
                        Toast.makeText(EnterPasscodeScreen.this, message, Toast.LENGTH_SHORT).show();

                    }else{

                        Toast.makeText(EnterPasscodeScreen.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }



                }else{
                    Toast.makeText(EnterPasscodeScreen.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SendOTPResponse> call, Throwable t) {

                Toast.makeText(EnterPasscodeScreen.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure Message - " + t.getMessage());

            }
        });



    }



}