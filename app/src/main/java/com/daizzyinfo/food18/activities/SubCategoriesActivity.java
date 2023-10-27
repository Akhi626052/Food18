package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.adapter.SubCategoriesAdapter;
import com.daizzyinfo.food18.databinding.ActivitySubCategoriesBinding;
import com.daizzyinfo.food18.retrofit.ApiRequest;
import com.daizzyinfo.food18.retrofit.RetrofitRequest;
import com.daizzyinfo.food18.utils.Constant;
import com.daizzyinfo.food18.viewModel.Categories.CatResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoriesActivity extends AppCompatActivity {

    ActivitySubCategoriesBinding binding;

    private static final String TAG = SubCategoriesActivity.class.getSimpleName();

    List<CatResponse> model ;

    String ID = "";

    SubCategoriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sub_categories);


           getIntentData();

//         getCategoriesAPI();

    }

    private void getIntentData() {
        Intent intent = getIntent();

        if(intent!=null)
        {
            ID = intent.getStringExtra("ID");

            getCategoriesAPI();
        }
    }

    private void getCategoriesAPI() {

        model = new ArrayList<>();
        adapter = new SubCategoriesAdapter(this,model);
        binding.rvSubCategories.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        binding.rvSubCategories.setLayoutManager(manager);


        ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
        Call<CatResponse> call = apiRequest.getCatApi(Constant.Token);
        call.enqueue(new Callback<CatResponse>() {
            @Override
            public void onResponse(Call<CatResponse> call, Response<CatResponse> response) {

                if(response.body()!=null)
                {

                    Log.e(TAG, "on response code : " + response.code());

                    if(response.code()==200)
                    {

                        for(int i = 0 ; i<response.body().getData().get(i).getChild().size() ; i++)
                        {



                        }
                    }

                }
                else {

                    Toast.makeText(SubCategoriesActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<CatResponse> call, Throwable t) {

                Log.e(TAG,"onFailure -"+t.getMessage());
                Toast.makeText(SubCategoriesActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();

            }
        });

    }


}