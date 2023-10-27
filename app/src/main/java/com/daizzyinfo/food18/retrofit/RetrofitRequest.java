package com.daizzyinfo.food18.retrofit;

import static com.daizzyinfo.food18.utils.Constant.Token;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitRequest {

    public static Retrofit retrofit = null;
    public static Retrofit getRetrofit() {


        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + Token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();


        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://test-daizzyinfosys.com/food/api/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
