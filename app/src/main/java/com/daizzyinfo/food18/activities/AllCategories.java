package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daizzyinfo.food18.adapter.AllCtgItmAdapter;
import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.databinding.ActivityAllCategoriesBinding;
import com.daizzyinfo.food18.retrofit.ApiRequest;
import com.daizzyinfo.food18.retrofit.RetrofitRequest;
import com.daizzyinfo.food18.utils.Constant;
import com.daizzyinfo.food18.viewModel.Categories.CatResponse;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategories extends AppCompatActivity {
    ActivityAllCategoriesBinding binding;
    private static final String TAG = AllCategories.class.getSimpleName();
    List<CatResponse> model = new ArrayList<>();
    AllCtgItmAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_categories);

              initOnClickListener();

              binding.spinKitCat.setVisibility(View.VISIBLE);

              new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {
             getCatApi();
         }
     },1000);

              openToolbar();

   }

    private void getCatApi() {

        model=new ArrayList<>();
        adapter= new AllCtgItmAdapter(model,this);
        binding.rvCtgItm.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.rvCtgItm.setLayoutManager(manager);

        ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
        Call<CatResponse> call = apiRequest.getCatApi(Constant.Token);
        call.enqueue(new Callback<CatResponse>() {
            @Override
            public void onResponse(Call<CatResponse> call, Response<CatResponse> response) {

                if(response.body()!=null)
                {
                Log.e("AllCategories","on Response    - " + response.code());

                if(response.code()==200)
                {
                    CatResponse responseCat =response.body();

                    if(responseCat.getStatus())
                    {
                        for(int i = 0; i < response.body().getData().size(); i++)
                        {
                            model.add(response.body());

                            binding.spinKitCat.setVisibility(View.GONE);
                            binding.shimmerViewContainer.stopShimmer();
                            binding.shimmerViewContainer.setVisibility(View.GONE);

                        }
                        adapter.notifyDataSetChanged();
                    }


                } else if (response.code()==401) {
                    binding.spinKitCat.setVisibility(View.GONE);
                    Toast.makeText(AllCategories.this, "Unauthorized", Toast.LENGTH_SHORT).show();

                } else if (response.code()==404) {
                    binding.spinKitCat.setVisibility(View.GONE);
                    Toast.makeText(AllCategories.this, "Data not found", Toast.LENGTH_SHORT).show();

                }else {
                    binding.spinKitCat.setVisibility(View.GONE);
                    Toast.makeText(AllCategories.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
                }
                }else {

                    Toast.makeText(AllCategories.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<CatResponse> call, Throwable t) {
                Toast.makeText(AllCategories.this, "on Failure ", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"on Failure - " + t.getMessage());
                binding.spinKitCat.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.shimmerViewContainer.stopShimmer();
    }

    @Override
    protected void onPause() {
        binding.shimmerViewContainer.stopShimmer();
        super.onPause();
    }
    private void openToolbar() {
        View view = findViewById(R.id.AllCategories);
        TextView TxtHeader = view.findViewById(R.id.TxtHeader);
        ImageView ImgBack = view.findViewById(R.id.ImgBack);
        TxtHeader.setText("All Categories");
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void initOnClickListener() {

    }


}