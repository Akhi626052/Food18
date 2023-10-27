package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.databinding.ActivityVerificationBinding;
import com.daizzyinfo.food18.retrofit.ApiRequest;
import com.daizzyinfo.food18.retrofit.RetrofitRequest;
import com.daizzyinfo.food18.utils.Constant;
import com.daizzyinfo.food18.utils.VerifyOTPCountdownTimer;
import com.daizzyinfo.food18.viewModel.SendOtp.SendOTPResponse;
import com.goodiebag.pinview.Pinview;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Verification extends AppCompatActivity {
    ActivityVerificationBinding binding;
    String  Mobile , OTP, ComingFrom;
    String InputOTP;
    private static final String TAG = Verification.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_verification);
        setContentView(binding.getRoot());

         initOnClickListener();

         openToolbar();

         binding.pinView.requestPinEntryFocus();


         getIntentData();

        VerifyOTPCountdownTimer timer = new VerifyOTPCountdownTimer(6000*10,1000, binding.timer);
        timer.start();





        if(binding.timer.getText().toString().equals("I don’t receive a code! Please resend")){
            Toast.makeText(this, "resented", Toast.LENGTH_SHORT).show();
            binding.timer.setClickable(true);
            binding.timer.setEnabled(true);
            OTP = "";


        }else{

        }

    }
    public void getIntentData(){
       Intent intent = getIntent();
       if(intent!=null){
           Mobile = intent.getStringExtra("Mobile");
           OTP =  intent.getStringExtra("OTP");
           ComingFrom = intent.getStringExtra("ComingFrom");

           Log.e(TAG, "Mobile - " + Mobile);
           Log.e(TAG, "OTP - " + OTP);
           Log.e(TAG, "ComingFrom - " + ComingFrom);


           binding.txtNumber.setText(Mobile);

       }else {

           Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
           finish();

       }

    }

    protected void initOnClickListener(){

        binding.SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputOTP = binding.pinView.getValue();

                if(InputOTP.length() !=6){
                    Toast.makeText(Verification.this, "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
                }else{
                    if(InputOTP.equals(OTP)){

                        Intent intent = new Intent(Verification.this, SetPasscodeScreen.class);
                        intent.putExtra("Mobile",Mobile);
                        startActivity(intent);
                        finish();

                    }else{

                        Toast.makeText(Verification.this, "OTP is incorrect", Toast.LENGTH_SHORT).show();

                    }


                }


            }
        });

        binding.timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendOTPApi();
            }
        });

    }

    protected void openToolbar() {
        View view = findViewById(R.id.VerifyHeader);
        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        TxtHeader.setText("Verification");
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
                            OTP = String.valueOf(sendOTPResponse.getOtp());
                            VerifyOTPCountdownTimer timer = new VerifyOTPCountdownTimer(6000*10,1000, binding.timer);
                            timer.start();
                        }else{

                        }
                        Toast.makeText(Verification.this, message, Toast.LENGTH_SHORT).show();

                      }else{

                        Toast.makeText(Verification.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }



                }else{

                       Toast.makeText(Verification.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                      }
            }

            @Override
            public void onFailure(Call<SendOTPResponse> call, Throwable t) {
                Toast.makeText(Verification.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure Message - " + t.getMessage());
            }
        });

    }

}


