package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.databinding.ActivityAddNewAddressBinding;
import com.daizzyinfo.food18.retrofit.ApiRequest;
import com.daizzyinfo.food18.retrofit.RetrofitRequest;
import com.daizzyinfo.food18.utils.Constant;
import com.daizzyinfo.food18.viewModel.City.CityResponse;
import com.daizzyinfo.food18.viewModel.State.StateResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewAddress extends AppCompatActivity {
    ActivityAddNewAddressBinding binding;

    ArrayList<String> array =  new ArrayList<>();

    public  static final  String TAG = AddNewAddress.class.getSimpleName();
    ArrayAdapter stateAdapter,cityAdapter;
    List<String> stateList = new ArrayList<String>();
    List<String> cityList = new ArrayList<String>();

    String selectedStateId ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_address);

        openToolBar();

    //    City List
        cityList.add("Select City");
        cityAdapter = new ArrayAdapter(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,cityList);
        binding.SpnCity.setAdapter(cityAdapter);

     //  State list
        stateList.add("Select State");
        stateAdapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,stateList);
        binding.SpnState.setAdapter(stateAdapter);

        // StateApiCall
           stateApi();

       initOnClickListener();


    }

    private void cityApi(String state_id) {

        cityList.clear();
        cityList.add("Select City");

        ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
        Call<CityResponse> call = apiRequest.getCityApi(Constant.Token,state_id);
        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {

                if(response.body()!=null)
                {
                Log.e("CompleteYourProfile","On Response  --"+response.code());

                CityResponse response1 = response.body();

                if(response.code()==200){

                    for(int i = 0 ; i < response.body().getData().size(); i++)
                    {
                        cityList.add(response1.getData().get(i).getName());
                    }
                    cityAdapter.notifyDataSetChanged();


                } else if (response.code() == 401) {
                    Toast.makeText(AddNewAddress.this, "Unauthorized", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 404) {
                    Toast.makeText(AddNewAddress.this, " Data not found r", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {
                    Toast.makeText(AddNewAddress.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddNewAddress.this, "Data not found ", Toast.LENGTH_SHORT).show();
                }

                }
                else {
                    Toast.makeText(AddNewAddress.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    Log.e(TAG,"Response code: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {

                Toast.makeText(AddNewAddress.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"On Failure - " + t.getMessage());


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

                    if(response.body()!=null)
                    {
                        Log.e("CompleteYourProfile", "CompleteYourProfile onResponse " + response.code());

                    StateResponse response1 = response.body();

                if (response.code() == 200)
                {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        stateList.add(response.body().getData().get(i).getName());

                    } stateAdapter.notifyDataSetChanged();

                } else if (response.code() == 401) {
                    Toast.makeText(AddNewAddress.this, "On Failure ", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 404) {
                    Toast.makeText(AddNewAddress.this, " Unauthorized", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {
                    Toast.makeText(AddNewAddress.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddNewAddress.this, "Data not found ", Toast.LENGTH_SHORT).show();
                }

                }else {
                        Toast.makeText(AddNewAddress.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }


                binding.SpnState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position > 0) {
                             selectedStateId = String.valueOf(response.body().getData().get(position-1).getId());
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
                Toast.makeText(AddNewAddress.this, "on Failure", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure" + t.getMessage().toString());
            }
        });
    }

    private void initOnClickListener() {


//        binding.SpnCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(binding.SpnState.getSelectedItem().equals("Select State"))
//                {
//                    Toast.makeText(AddNewAddress.this, "Please select first a state", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });



            binding.BtnSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = binding.edName.getText().toString().trim();
                String Mobile = binding.edNumber.getText().toString().trim();
                String Address = binding.edAddress.getText().toString().trim();
                String City = binding.SpnCity.getSelectedItem().toString().trim();
                String State = binding.SpnState.getSelectedItem().toString().trim();

                if(State.isEmpty()|| State.equals("Select State"))
                {
                    Toast.makeText(AddNewAddress.this, "Please select the valid State", Toast.LENGTH_SHORT).show();

                } else if (City.isEmpty()|| City.equals("Select City")) {

                    Toast.makeText(AddNewAddress.this, "Please select the valid City", Toast.LENGTH_SHORT).show();

                } else if (Name.isEmpty()|| Name.length()<4) {

                    binding.edName.setError("Enter the Valid Name");
                    binding.edName.requestFocus();

                } else if (Mobile.isEmpty() || Mobile.length()<10) {

                    binding.edNumber.setError("Enter the Valid Mobile Number");
                    binding.edNumber.requestFocus();

                    // Clear Focus
                    binding.edName.setError(null);
                    binding.edName.clearFocus();

                }else if (Address.isEmpty() || Address.length()<5) {

                    binding.edAddress.setError("Enter the Valid Address");
                    binding.edAddress.requestFocus();

                    // Clear Focus
                    binding.edName.setError(null);
                    binding.edName.clearFocus();
                    binding.edNumber.setError(null);
                    binding.edNumber.clearFocus();

                }else
                {
                    binding.edName.setError(null);
                    binding.edName.clearFocus();
                    binding.edNumber.setError(null);
                    binding.edNumber.clearFocus();
                    binding.edAddress.setError(null);
                    binding.edAddress.clearFocus();

                    Intent i = new Intent(getApplicationContext(),MyAddress.class);
                    i.putExtra("State",State);
                    i.putExtra("Address",Address);
                    i.putExtra("City",City);
                    i.putExtra("Name",Name);
                    i.putExtra("Mobile",Mobile);
                    startActivity(i);

                }

//                SharedPreferences sharedPreferences = getSharedPreferences("myPreference", 0);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("Name",edName.getText().toString().trim()).apply();
//                editor.putString("Number",edNumber.getText().toString()).apply();
//                editor.putString("Address",edAddress.getText().toString()).apply();
//
//
//               if(SpnState.getSelectedItem().toString().equals("Select State"))
//               {
//
//               }else
//               {
//                   editor.putString("State",SpnState.getSelectedItem().toString()).apply();
//               }

//                if(SpnCity.getSelectedItem().toString().equals("Select City"))
//                {
//
//                }else {
//                    editor.putString("City", SpnCity.getSelectedItem().toString()).apply();
//                }

//                editor.apply();
//                editor.commit();

            }
        });

    }


    protected void openToolBar(){
        View view = findViewById(R.id.Header);
        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        TxtHeader.setText("Add New Address");
        ImageView ImgBack=view.findViewById(R.id.ImgBack);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MyAddress.class);
                startActivity(i);
            }
        });

    }

}

//                i.putExtra("Name",edName.getText().toString().trim());
//                i.putExtra("Number",edNumber.getText().toString().trim());
//                i.putExtra("City",SpnCity.getSelectedItem().toString().trim());
//                i.putExtra("State",SpnState.getSelectedItem().toString().trim());
//                i.putExtra("Address",edAddress.getText().toString().trim());