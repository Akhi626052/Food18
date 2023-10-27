package com.daizzyinfo.food18.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daizzyinfo.food18.activities.AllCategories;
import com.daizzyinfo.food18.activities.AllItems;
import com.daizzyinfo.food18.activities.Notification;
import com.daizzyinfo.food18.adapter.CtgAdapter;
import com.daizzyinfo.food18.adapter.NormalItemsAdapter;
import com.daizzyinfo.food18.databinding.FragmentHome2Binding;
import com.daizzyinfo.food18.retrofit.ApiRequest;
import com.daizzyinfo.food18.retrofit.RetrofitRequest;
import com.daizzyinfo.food18.utils.Constant;
import com.daizzyinfo.food18.activities.MyAddress;
import com.daizzyinfo.food18.adapter.PopularAdapter;
import com.daizzyinfo.food18.model.PopularModel;
import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.activities.SearchScreen;
import com.daizzyinfo.food18.viewModel.Categories.CatResponse;
import com.daizzyinfo.food18.viewModel.ProductList.ProductListResponse;
import com.daizzyinfo.food18.viewModel.Sliders.HomeSlidersResponse;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import taimoor.sultani.sweetalert2.Sweetalert;


public class FragmentHome extends Fragment {
    FragmentHome2Binding binding;
    //For Categories
    CtgAdapter ctgAdapter;
    List<CatResponse> ctgModels = new ArrayList<>();
    // For sliders
    List<HomeSlidersResponse> homeSlidersModel = new ArrayList<>();
    // For Popular Items
    Sweetalert pDialog;
    List<ProductListResponse> popularProductModel;
    List<ProductListResponse> NormalModel ;
    private static final String TAG =  FragmentHome.class.getSimpleName();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHome2Binding.inflate(inflater,container,false);
        View view = binding.getRoot();
       binding.PopularRecyclerview.suppressLayout(true);

//        pDialog = new Sweetalert(getContext(), Sweetalert.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        pDialog.setTitleText("Loading");
//        pDialog.setCancelable(false);
//        pDialog.show();
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//             pDialog.dismiss();
//
//
//            }
//        },2500);

       ImageSlider();
       ImgSlider2();
       initOnClickListener();
       getCategoryApi();
       getSlidersAPI();
       getPopularItemsAPI();

       Log.e(TAG, "Token on home - " + Constant.getToken());
       return  view ;


    }

    private void getPopularItemsAPI() {

        popularProductModel = new ArrayList<>();

        NormalModel = new ArrayList<>();
        //For Popular Products.
        PopularAdapter popularAdapter = new PopularAdapter(popularProductModel,getActivity());
        binding.PopularRecyclerview.setAdapter(popularAdapter);

        GridLayoutManager manager2 = new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false);
        binding.PopularRecyclerview.setLayoutManager(manager2);

        //For Normal Products.
         NormalItemsAdapter normalAdapter = new NormalItemsAdapter(NormalModel,getActivity());
         binding.rvNormalProducts.setAdapter(normalAdapter);

        GridLayoutManager manager = new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false);
        binding.rvNormalProducts.setLayoutManager(manager);

        ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
        Call<ProductListResponse> call = apiRequest.getProductsApi(Constant.Token);
        call.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {

                if (response.body() != null)
                {
                    Log.e(TAG," on Response Popular Products -"+response.code());

                    if(response.code()== 200)
                    {
                        ProductListResponse response1 = response.body();

                        if(response1.getStatus())
                        {

                            for(int i=0; i<response.body().getData().getPopular().size(); i++)
                            {
                                popularProductModel.add(response1);

                            }
                            popularAdapter.notifyDataSetChanged();

                            for(int i=0; i<response.body().getData().getNormal().size(); i++)
                            {
                                NormalModel.add(response1);
                            }
                            normalAdapter.notifyDataSetChanged();

                        }

                    } else if(response.code()==401){

                        Toast.makeText(getContext(), "UnAuthorized", Toast.LENGTH_SHORT).show();

                    } else if(response.code()==404) {

                        Toast.makeText(getContext(), "Data not found", Toast.LENGTH_SHORT).show();

                    }else if(response.code()==500)
                    {
                        Toast.makeText(getContext(), "Internal server error", Toast.LENGTH_SHORT).show();

                    }else {

                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {

                Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"On Failure popular products  -"+t.getMessage());

            }
        });

    }

    private void ImageSlider(){
        ArrayList<SlideModel> slideModelArrayList = new ArrayList<>();
        slideModelArrayList.add(new SlideModel(R.drawable.group, ScaleTypes.FIT));
        slideModelArrayList.add(new SlideModel(R.drawable.group, ScaleTypes.FIT));
        binding.imageSlider.setImageList(slideModelArrayList, ScaleTypes.FIT);
    }
    private void ImgSlider2() {
        ArrayList<SlideModel> slideModelArrayList = new ArrayList<>();
        slideModelArrayList.add(new SlideModel(R.drawable.tea_img,ScaleTypes.FIT));
        slideModelArrayList.add(new SlideModel(R.drawable.tea_img,ScaleTypes.FIT));
        binding.imageSlider2.setImageList(slideModelArrayList,ScaleTypes.FIT);
    }
    private void getSlidersAPI() {
        ArrayList<SlideModel> slideModelArrayList = new ArrayList<>();
        ArrayList<SlideModel> slideModelArrayList1 = new ArrayList<>();

        ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
        Call<HomeSlidersResponse> call = apiRequest.getHomeSliderApi(Constant.Token);
        call.enqueue(new Callback<HomeSlidersResponse>() {
            @Override
            public void onResponse(Call<HomeSlidersResponse> call, Response<HomeSlidersResponse> response) {

                if(response.body() != null)
                {
                    Log.e(TAG, "on response Home Slider - " + response.code());

                    if(response.code()==200)
                    {

                        HomeSlidersResponse homeSlidersResponse = response.body();
                        if(homeSlidersResponse.getStatus())
                        {

                        for(int i = 0 ; i< response.body().getData().getTopSlider().size();i++){

                            String url = response.body().getData().getTopSlider().get(i).getSliders();
                            slideModelArrayList.add(new SlideModel(url,ScaleTypes.FIT));
                            binding.imageSlider.setImageList(slideModelArrayList);

                            String btmUrl = response.body().getData().getBottomSlider().get(i).getSliders();
                            slideModelArrayList1.add(new SlideModel(btmUrl,ScaleTypes.FIT));
                            binding.imageSlider2.setImageList(slideModelArrayList1);

                           }
                        }

                    }
                    else if(response.code()==401){

                        Toast.makeText(getContext(), "UnAuthorized", Toast.LENGTH_SHORT).show();

                    } else if(response.code()==404) {

                        Toast.makeText(getContext(), "Data not found", Toast.LENGTH_SHORT).show();

                    }else if(response.code()==500)
                    {
                        Toast.makeText(getContext(), "Internal server error", Toast.LENGTH_SHORT).show();

                    }else {

                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                }
                else {

                    Toast.makeText(getContext(), "Something went wrong ", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<HomeSlidersResponse> call, Throwable t) {

                Toast.makeText(getContext(), "Something went wrong ", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"onFailure home slider error: " + t.toString());

            }
        });

    }
    private void getCategoryApi() {

        ctgModels = new ArrayList<>();
        ctgAdapter = new CtgAdapter(ctgModels,getActivity());
        binding.CtgRecyclerview.setAdapter(ctgAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        binding.CtgRecyclerview.setLayoutManager(manager);

        ApiRequest apiRequest = RetrofitRequest.getRetrofit().create(ApiRequest.class);
        Call<CatResponse> call = apiRequest.getCatApi(Constant.Token);
        call.enqueue(new Callback<CatResponse>() {
            @Override
            public void onResponse(Call<CatResponse> call, Response<CatResponse> response) {

                if(response.body() != null )
                {
                Log.e("FragmentHome","on Response - " + response.body().getData().get(0).getName());
                CatResponse response1 = response.body();

              if(response.code()==200)
              {
                  for(int i = 0; i < response.body().getData().size(); i++)
                  {
                      ctgModels.add(response1);
                      ctgAdapter.notifyDataSetChanged();


                  }

              } else if(response.code()==401){

                  Toast.makeText(getContext(), "UnAuthorized", Toast.LENGTH_SHORT).show();

              } else if(response.code()==404) {

                  Toast.makeText(getContext(), "Data not found", Toast.LENGTH_SHORT).show();

              }else if(response.code()==500)
              {
                  Toast.makeText(getContext(), "Internal server error", Toast.LENGTH_SHORT).show();

              }else {

                  Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
              }
          }
                else {

                    Toast.makeText(getContext(), "Something went wrong ", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<CatResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "On Failure ", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"on Failure Categories Item -"+t.getMessage());

            }
        });

    }

    private void initOnClickListener() {

        binding.CtgAllItm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllCategories.class);
                startActivity(intent);
            }
        });

        binding.PopAllItm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllItems.class);
                startActivity(intent);
            }
        });

        binding.Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchScreen.class);
                startActivity(intent);
            }
        });

        binding.myAddClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyAddress.class);
                startActivity(intent);
            }
        });

        binding.imgNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Notification.class);
                startActivity(intent);
            }
        });

        binding.txtNormalViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllItems.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}