package com.andrewlaurien.madmetweet.service;

import android.util.JsonToken;

import com.andrewlaurien.madmetweet.model.OAuthToken;
import com.andrewlaurien.madmetweet.model.Tweet;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TwitterService {


    @POST("oauth2/token")
    Call<OAuthToken> getAuthenticationToken(
            @Header("Content-Type") String con_type,
            @Header("Authorization") String author,
            @Body RequestBody requestBody);


    @POST("oauth/request_token")
    Call<OAuthToken> requestToken(
            @Header("Content-Type") String con_type,
            @Header("Authorization") String author
          );



    @GET("1.1/statuses/user_timeline.json?screen_name=elonmusk&exclude_replies=true&include_rts=false")
    Call<List<Tweet>> getTimeLineTweets(@Header("Content-Type") String con_type, @Header("Authorization") String token);


    @GET("1.1/statuses/home_timeline.json?count=5&max_id=12&since_id=1&exclude_replies=true&include_rts=false\"")
    Call<List<Tweet>> getHomeTweets(@Header("Content_Type") String con_type, @Header("Authorization") String token);

}
