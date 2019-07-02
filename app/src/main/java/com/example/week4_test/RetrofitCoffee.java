package com.example.week4_test;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitCoffee {
    //URL's
    public static final String BASE_URL = "https://demo6983184.mockable.io/coffees/";
    public static final String QUERY_RESULT = "coffee_id";

    public Retrofit getRetrofitInstance() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)).build();

        return new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RetrofitApiService getService(){
        return getRetrofitInstance().create(RetrofitApiService.class);
    }

    public interface RetrofitApiService{
        @GET
        Call<CoffeeItem> getCoffeeItem(@Query(QUERY_RESULT)String coffee);
    }
}
