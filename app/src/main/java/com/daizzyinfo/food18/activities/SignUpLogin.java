package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.databinding.ActivitySignUpLoginBinding;
import com.daizzyinfo.food18.retrofit.ApiRequest;
import com.daizzyinfo.food18.retrofit.RetrofitRequest;
import com.daizzyinfo.food18.viewModel.SendOtp.CheckMobileResponse;
import com.daizzyinfo.food18.viewModel.SendOtp.SendOTPResponse;
import com.github.ybq.android.spinkit.SpinKitView;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpLogin extends AppCompatActivity {

    ActivitySignUpLoginBinding binding;
    private final String TAG = SignUpLogin.class.getSimpleName();
    String Mobile;
    @Override
    protected void onPause() {
        binding.spinKit.setVisibility(View.GONE);
        binding.ContinueBtn.setVisibility(View.VISIBLE);
        super.onPause();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up_login);

        initOnClick();
        txtColor();

    }

    private void initOnClick() {
        binding.ContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Number = binding.edMobNumber.getText().toString().trim();

                if (Number.isEmpty() || Number.length()<10)
                {
                    binding.edMobNumber.requestFocus();
                    binding.edMobNumber.setError("Please Enter 10 Digit Valid Number");
                }
                else
                {
                    binding.edMobNumber.clearFocus();
                    binding.edMobNumber.setError(null);
                    binding.ContinueBtn.setVisibility(View.GONE);
                    binding.spinKit.setVisibility(View.VISIBLE);
                    Mobile  = binding.edMobNumber.getText().toString();
                   CheckMobileApi();



            }

           }
        });

    }
    @SuppressLint("ResourceAsColor")
    private void txtColor() {
        TextView textView = findViewById(R.id.TxtWlc);
        String first = "Welcome to";
        String second = " Foods18";
        textView.setText(first + second, TextView.BufferType.SPANNABLE);
        Spannable s = (Spannable) textView.getText();
        int start = first.length();
        int end = start + second.length();
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.red)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }


    public void CheckMobileApi(){

        ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
        Call<CheckMobileResponse> call = apiRequest.CheckMobileNumberApi(Mobile);
        call.enqueue(new Callback<CheckMobileResponse>() {
            @Override
            public void onResponse(Call<CheckMobileResponse> call, Response<CheckMobileResponse> response) {
                if(response.body()!=null){
                    if(response.code()==200){

                        String message  = response.body().getMsg();
                        Log.e(TAG, "message - " + message);
                        if(message.equals("Already used")){
                            Intent intent = new Intent(SignUpLogin.this, EnterPasscodeScreen.class);
                            intent.putExtra("Mobile",binding.edMobNumber.getText().toString());
                            startActivity(intent);
                        }else if (message.equals("Valid")){
                            SendOTPApi();
                        }

                    }else{
                        Toast.makeText(SignUpLogin.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(SignUpLogin.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<CheckMobileResponse> call, Throwable t) {

                Toast.makeText(SignUpLogin.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void SendOTPApi(){
        Mobile  = binding.edMobNumber.getText().toString();
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
                            Intent intent = new Intent(SignUpLogin.this, Verification.class);

                            intent.putExtra("ComingFrom",SignUpLogin.class.getSimpleName());
                            intent.putExtra("Mobile",Mobile);
                            intent.putExtra("OTP",OTP);


                            startActivity(intent);

                        }
                        Toast.makeText(SignUpLogin.this, message, Toast.LENGTH_SHORT).show();

                    }else{

                        Toast.makeText(SignUpLogin.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }

                 }else{
                    Toast.makeText(SignUpLogin.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SendOTPResponse> call, Throwable t) {

                Toast.makeText(SignUpLogin.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure Message - " + t.getMessage());

            }
        });



    }


    @Override
    public void onBackPressed() {

        FancyAlertDialog.Builder
                .with(this)
                .setTitle("Confirm Exit ... !!")
                .setBackgroundColor(Color.parseColor("#ff5c33"))
                .setMessage("Do you really want to Exit ?")
                .setNegativeBtnText("Cancel")
                .setPositiveBtnBackground(Color.parseColor("#ff5c33"))
                .setPositiveBtnText("Yes")
                .setNegativeBtnBackground(Color.parseColor("#ff5c33"))
                .isCancellable(false)
                .setIcon(R.drawable.baseline_bubble_chart_24,View.VISIBLE)
                .onPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void onClick(Dialog dialog) {
                        SignUpLogin.super.onBackPressed();
                    }
                })
                .onNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void onClick(Dialog dialog) {

                    }
                })
                .build()
                .show();

    }



}