package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.daizzyinfo.food18.adapter.AllItmAdapter;
import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.databinding.ActivityAllItemsBinding;
import com.daizzyinfo.food18.retrofit.ApiRequest;
import com.daizzyinfo.food18.retrofit.RetrofitRequest;
import com.daizzyinfo.food18.utils.Constant;
import com.daizzyinfo.food18.viewModel.AddtoCart.AddToCartResponse;
import com.daizzyinfo.food18.viewModel.ProductList.ProductListResponse;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllItems extends AppCompatActivity {

    ActivityAllItemsBinding binding;
    private final String TAG = AllItems.class.getSimpleName();
    List<ProductListResponse> model = new ArrayList<>();
     AllItmAdapter adapter;

     String product_id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_all_items);

        initOnClickListener();
        binding.spinKit.setVisibility(View.VISIBLE);
        getProductApi();

        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.spinKit.setVisibility(View.VISIBLE);
                getProductApi();

            }
        });

        AddToCartAPI();
        binding.swipeRefresh.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_red_light)
        );

    }



    public void getProductApi() {
        model.clear();
        model = new ArrayList<>();
        adapter = new AllItmAdapter(model,this);
        binding.rvAllItems.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.rvAllItems.setLayoutManager(manager);

        ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
        Call<ProductListResponse> call = apiRequest.getProductsApi(Constant.Token);
        call.enqueue(new Callback<ProductListResponse>() {
           @Override
           public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {

               if(response.body()!=null)
               {
               if(response.code()==200 || response.isSuccessful())
               {
                   for(int i = 0; i < response.body().getData().getNormal().size(); i++)
                   {
                       model.add(response.body());
                       product_id = response.body().getData().getNormal().get(i).getProductId();

                   }
                   adapter.notifyDataSetChanged();

               } else if (response.code()==401) {

                   Toast.makeText(AllItems.this, "Unauthorized", Toast.LENGTH_SHORT).show();

               }else if (response.code()==404) {

                   Toast.makeText(AllItems.this, " Data not found ", Toast.LENGTH_SHORT).show();

               } else if (response.code()==500) {

                   Toast.makeText(AllItems.this, "Internal Server Error", Toast.LENGTH_SHORT).show();

               }else {

                   Toast.makeText(AllItems.this, "Something Went wrong", Toast.LENGTH_SHORT).show();
               }

               }
               else {
                   Toast.makeText(AllItems.this, "Something Went wrong", Toast.LENGTH_SHORT).show();

               }
               binding.swipeRefresh.setRefreshing(false);
               binding.spinKit.setVisibility(View.GONE);
           }
           @Override
           public void onFailure(Call<ProductListResponse> call, Throwable t) {

               Toast.makeText(AllItems.this, "On Failure ", Toast.LENGTH_SHORT).show();
               Log.e(TAG,"on Failure All Items.  - " + t.getMessage());
               binding.swipeRefresh.setRefreshing(false);
               binding.spinKit.setVisibility(View.GONE);

           }
       });

    }
    private void AddToCartAPI() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("product_id",product_id);

        ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
        Call<AddToCartResponse> call = apiRequest.AddToCartAPI(jsonObject);
        call.enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {

                if(response.body() != null)
                {
                    Log.e(TAG, "on response: " + response.code());

                    if(response.code()==200)
                    {






                    }

                }

            }

            @Override
            public void onFailure(Call<AddToCartResponse> call, Throwable t) {

                Toast.makeText(AllItems.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"on Failure Add to cart  - " + t.getMessage());
            }
        });
    }


    private void initOnClickListener() {

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }


        });


    }


}





