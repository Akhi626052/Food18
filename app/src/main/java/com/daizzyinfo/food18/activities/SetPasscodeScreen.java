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
import com.daizzyinfo.food18.databinding.ActivitySetPasscodeScreenBinding;
import com.daizzyinfo.food18.retrofit.ApiRequest;
import com.daizzyinfo.food18.retrofit.RetrofitRequest;
import com.daizzyinfo.food18.utils.Constant;
import com.daizzyinfo.food18.viewModel.setPasscode.SetPasscodeResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetPasscodeScreen extends AppCompatActivity {

    ActivitySetPasscodeScreenBinding binding;

    String FullName = "";
    String Email = "";

    String Mobile,Passcode ;
    String State = "";
    String City = "";
    String Address = "";
    String UserImage = "";

    private static final String TAG = "SetPasscodeScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_set_passcode_screen);
        binding.pinViewSetPass.requestPinEntryFocus();

         openToolBar();
         initOnClickListener();
          getIntentData();

        binding.pinViewSetPass.requestPinEntryFocus();



    }

    private void getIntentData() {
        Intent intent = getIntent();

        if(intent!=null)
        {
           Mobile = intent.getStringExtra("Mobile");

        }

    }


    private void initOnClickListener(){

        binding.SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Passcode = binding.pinViewSetPass.getValue().toString();

                if(Passcode.length()<4)
                {
                    Toast.makeText(SetPasscodeScreen.this, "Please Enter the valid Passcode", Toast.LENGTH_SHORT).show();

                }
               else {

                    SharedPreferences sharedPreferences = getSharedPreferences(Constant.MY_PREF_NAME,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("SetPasscode",Passcode).apply();
                    editor.commit();
                    setPasscodeAPI();
                }


            }
        });


    }

    private void setPasscodeAPI() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("full_name",FullName);
        jsonObject.addProperty("email",Email);
        jsonObject.addProperty("mobile",Mobile);
        jsonObject.addProperty("password",Passcode);
        jsonObject.addProperty("state",State);
        jsonObject.addProperty("city",City);
        jsonObject.addProperty("address",FullName);
        jsonObject.addProperty("user_image",UserImage);

        ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
        Call<SetPasscodeResponse> call = apiRequest.setPasscodeAPI(jsonObject);
        call.enqueue(new Callback<SetPasscodeResponse>() {
            @Override
            public void onResponse(Call<SetPasscodeResponse> call, Response<SetPasscodeResponse> response) {
               Log.e(TAG, "On Response code - " + response.code());
                Log.e(TAG, "On Response message - " + response.body().getMessage());

                if(response.body() != null)
                {
                    
                    if(response.code()==200) {
                        SetPasscodeResponse response1 = response.body();



                           if(response1.getStatus())
                           {
                               String Token = response1.getData().getToken();
                               Log.e(TAG, "Token: " + Token);
                               Constant.putToken(Token);
                               Toast.makeText(SetPasscodeScreen.this, "" + response1.getMessage(), Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(getApplicationContext(), BottomNavigation.class);
                               startActivity(intent);
                               finishAffinity();

                           }



                    } else {




                    }





                }else
                {
                    Toast.makeText(SetPasscodeScreen.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<SetPasscodeResponse> call, Throwable t) {




            }
        });

    }


    private void openToolBar(){
        View view = findViewById(R.id.txtSetPasscode);

        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        TxtHeader.setText("Set Passcode");
        ImageView ImgBack=view.findViewById(R.id.ImgBack);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

      }




}