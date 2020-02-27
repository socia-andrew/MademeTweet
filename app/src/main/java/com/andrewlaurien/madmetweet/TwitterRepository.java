package com.andrewlaurien.madmetweet;

import android.util.Base64;
import android.util.Log;

import com.andrewlaurien.madmetweet.model.OAuthToken;
import com.andrewlaurien.madmetweet.model.Tweet;
import com.andrewlaurien.madmetweet.service.TwitterService;
import com.andrewlaurien.madmetweet.service.WebClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.andrewlaurien.madmetweet.TwitterConstant.ACCESS_TOKEN;
import static com.andrewlaurien.madmetweet.TwitterConstant.API_KEY;
import static com.andrewlaurien.madmetweet.TwitterConstant.API_SECRET_KEY;
import static com.andrewlaurien.madmetweet.TwitterConstant.CONTENT_TYPE;

public class TwitterRepository {

    private TwitterService webClient;



    public TwitterRepository() {
        webClient = WebClient.getClient().create(TwitterService.class);
    }


    public LiveData<OAuthToken> getToken() {

        final MutableLiveData<OAuthToken> data = new MutableLiveData<>();

        String consumer = API_KEY + ":" + API_SECRET_KEY;
        String base64string = Base64.encodeToString(consumer.getBytes(), Base64.NO_WRAP);
        base64string = "Basic " + base64string;


        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("grant_type", "client_credentials");

        RequestBody formBody = formBuilder.build();

        TwitterService webClient = WebClient.getClient().create(TwitterService.class);

        Call<OAuthToken> tokenCall = webClient.getAuthenticationToken(CONTENT_TYPE, base64string, formBody);
        tokenCall.enqueue(new Callback<OAuthToken>() {
            @Override
            public void onResponse(Call<OAuthToken> call, Response<OAuthToken> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<OAuthToken> call, Throwable t) {
                Log.d("Response", "" + t.getMessage());
            }
        });

        return data;
    }


    public LiveData<List<Tweet>> getTweets(String token) {

        final MutableLiveData<List<Tweet>> data = new MutableLiveData<>();
        TwitterService webClient = WebClient.getClient().create(TwitterService.class);
        Call<List<Tweet>> tokenCall = webClient.getTimeLineTweets(CONTENT_TYPE, token);
        tokenCall.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
            }
        });

        return data;

    }

    public LiveData<List<Tweet>> getHomeTweets(String token) {


        String base64string = "oauth_token " + ACCESS_TOKEN;



        final MutableLiveData<List<Tweet>> data = new MutableLiveData<>();
        TwitterService webClient = WebClient.getClient().create(TwitterService.class);
        Call<List<Tweet>> tokenCall = webClient.getHomeTweets(CONTENT_TYPE, base64string);
        tokenCall.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                Log.d("onResponse", "" + response.body());
               // data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                Log.d("onResponse", "" + t.getMessage());
            }
        });

        return data;

    }

}
