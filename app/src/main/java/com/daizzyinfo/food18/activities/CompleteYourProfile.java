package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.databinding.ActivityCompleteYourProfileBinding;
import com.daizzyinfo.food18.retrofit.ApiRequest;
import com.daizzyinfo.food18.retrofit.RetrofitRequest;
import com.daizzyinfo.food18.utils.Constant;
import com.daizzyinfo.food18.viewModel.City.CityResponse;
import com.daizzyinfo.food18.viewModel.State.StateResponse;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompleteYourProfile extends AppCompatActivity {
    ActivityCompleteYourProfileBinding binding;
    public static String  TAG = CompleteYourProfile.class.getSimpleName();
    ArrayAdapter stateAdapter,cityAdapter;
    List<String> stateList = new ArrayList<String>();
    List<String> cityList = new ArrayList<String>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompleteYourProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        openToolBar();

   // City List
        cityList.add("Select City");
        cityAdapter = new ArrayAdapter(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,cityList);
        binding.SpnCity.setAdapter(cityAdapter);

    // State list
        stateList.add("Select State");
        stateAdapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,stateList);
        binding.SpnState.setAdapter(stateAdapter);

     //StateApiCalling
        stateApi();

        initOnClickListener();

        binding.SpnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedStatePosition = binding.SpnState.getSelectedItemPosition();
                String selectedState = stateList.get(selectedStatePosition);

                if (selectedState.equals("Select State")) {
                    Toast.makeText(CompleteYourProfile.this, "Please select a state first", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void cityApi(String state_id) {

        cityList.clear();
        cityList.add("Select City");

        ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
        Call<CityResponse> call = apiRequest.getCityApi(Constant.Token,state_id);
        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {

              Log.e("CompleteYourProfile","On Response  --"+response.body().getData().get(0));

              CityResponse response1 = response.body();

              if(response.code()==200){

                  for(int i = 0 ; i < response.body().getData().size(); i++)
                  {
                      cityList.add(response1.getData().get(i).getName());

                  }
                  cityAdapter.notifyDataSetChanged();


              } else if (response.code()== 401) {

                  Toast.makeText(CompleteYourProfile.this, "Not Found", Toast.LENGTH_SHORT).show();

              }else if (response.code()==400){

                  Toast.makeText(CompleteYourProfile.this, "Unauthorized", Toast.LENGTH_SHORT).show();

              } else if (response.code()==500) {

                  Toast.makeText(CompleteYourProfile.this, "Internal server Error", Toast.LENGTH_SHORT).show();
              }


            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {

                Toast.makeText(CompleteYourProfile.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"onFailure : " + t.getMessage().toString());

            }
        });

    }




        private void stateApi() {

            stateList.clear();
            stateList.add("Select State");

            ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
            Call<StateResponse> call = apiRequest.getStateApi(Constant.Token);
            call.enqueue(new Callback<StateResponse>() {
                @Override
                public void onResponse(Call<StateResponse> call, Response<StateResponse> response) {
                    if(response.body()!=null){

                    Log.e("CompleteYourProfile", "CompleteYourProfile onResponse " + response.body().getData().get(0).getName());
                    Log.e("CompleteYourProfile", "CompleteYourProfile onResponse " + response.body().getData().get(0).getId());

                    StateResponse response1 = response.body();

                    if (response.code() == 200) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            stateList.add(response.body().getData().get(i).getName());
                        }
                    } else if (response.code() == 401) {
                        Toast.makeText(CompleteYourProfile.this, "On Failure ", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 404) {
                        Toast.makeText(CompleteYourProfile.this, " Unauthorized", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 500) {
                        Toast.makeText(CompleteYourProfile.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CompleteYourProfile.this, "Data not found ", Toast.LENGTH_SHORT).show();
                    }

                    }else {

                        Toast.makeText(CompleteYourProfile.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }

                    stateAdapter.notifyDataSetChanged();

                    binding.SpnState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position > 0) {
                                String selectedStateId = String.valueOf(response.body().getData().get(position - 1).getId());
                                cityApi(selectedStateId);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }


                @Override
                public void onFailure(Call<StateResponse> call, Throwable t) {

                    Toast.makeText(CompleteYourProfile.this, "on Failure", Toast.LENGTH_SHORT).show();
                    Log.e("CompleteYourProfile", "onFailure" + t.getMessage().toString());

                }
            });



        }





    private void initOnClickListener(){

        binding.SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompleteYourProfile.this, BottomNavigation.class);
                startActivity(intent);
            }
        });


    }
    private void openToolBar(){

        View view = findViewById(R.id.txtComplete);
        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        TxtHeader.setText("Complete your profile");
        ImageView ImgBack=view.findViewById(R.id.ImgBack);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}