package com.daizzyinfo.food18.retrofit;

import com.daizzyinfo.food18.viewModel.AddtoCart.AddToCartResponse;
import com.daizzyinfo.food18.viewModel.Categories.CatResponse;
import com.daizzyinfo.food18.viewModel.City.CityResponse;
import com.daizzyinfo.food18.viewModel.ProductList.ProductListResponse;
import com.daizzyinfo.food18.viewModel.SendOtp.CheckMobileResponse;
import com.daizzyinfo.food18.viewModel.SendOtp.SendOTPResponse;
import com.daizzyinfo.food18.viewModel.Signup.SignupResponse;
import com.daizzyinfo.food18.viewModel.Sliders.HomeSlidersResponse;
import com.daizzyinfo.food18.viewModel.State.StateResponse;
import com.daizzyinfo.food18.viewModel.loginWithPasscode.SignInResponse;
import com.daizzyinfo.food18.viewModel.setPasscode.SetPasscodeResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiRequest {

    // send Otp API
    @GET("apiForOTP")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<SendOTPResponse> sendOtpAPI(@Query("mobile") String mobile);

    //SIgnIn(LoginWithPasscode) API
    @PUT("signin")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<SignInResponse> LoginWithPasscodeAPI(@Body JsonObject jsonObject);

    // check mobile
    @GET("validateMobile")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<CheckMobileResponse> CheckMobileNumberApi(@Query("mobile") String mobile);


    // for Set Password
    @POST("signup")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<SetPasscodeResponse> setPasscodeAPI(@Body JsonObject jsonObject);

    @PUT("addTocart")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<AddToCartResponse> AddToCartAPI(@Body JsonObject jsonObject);



    //Categories
    @GET("category")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<CatResponse> getCatApi(@Header("Token") String Token);




    @PUT("productslist")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<ProductListResponse> getProductsApi(@Header("Auth-Token") String Token);


    @GET("Statelist")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<StateResponse> getStateApi(@Header("Token") String Token);

    @GET("Citylist")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<CityResponse> getCityApi(@Header("Token")String cookie, @Query("state_id") String state_id);

    //Home Slider API
    @GET("homeSlider")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<HomeSlidersResponse> getHomeSliderApi(@Header("Auth-Token") String Token);




}
