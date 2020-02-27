package com.andrewlaurien.madmetweet.service;

import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebClient {

    private static String BASE_URL = "https://api.twitter.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(httpLoggingInterceptor);


        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient.build()).addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit;
    }


}
