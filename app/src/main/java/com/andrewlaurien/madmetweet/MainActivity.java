package com.andrewlaurien.madmetweet;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.andrewlaurien.madmetweet.adapter.TweetAdapter;
import com.andrewlaurien.madmetweet.model.OAuthToken;
import com.andrewlaurien.madmetweet.model.Tweet;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {


    TwitterViewModel twitterViewModel;

    RecyclerView mrecyclerview;
    TextView tvresponse;

    TweetAdapter tweetAdapter;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        twitterViewModel = ViewModelProviders.of(this).get(TwitterViewModel.class);

        twitterViewModel.getToken().observe(this, new Observer<OAuthToken>() {
            @Override
            public void onChanged(OAuthToken oAuthToken) {

                initList(oAuthToken);

            }
        });



        context = this;
        tweetAdapter = new TweetAdapter(context);

        tvresponse = findViewById(R.id.response);
        mrecyclerview = findViewById(R.id.mrecyclerview);


        mrecyclerview.setLayoutManager(new LinearLayoutManager(context));
        mrecyclerview.setAdapter(tweetAdapter);


    }

    public void initList(OAuthToken oAuthToken) {
        twitterViewModel.getTimeLineTweets(oAuthToken.getTokenType() + " " + oAuthToken.getAccessToken()).observe(this, new Observer<List<Tweet>>() {
            @Override
            public void onChanged(List<Tweet> tweets) {
                tweetAdapter.setItemList(tweets);
            }
        });


        twitterViewModel.getHomeTweet(oAuthToken.getTokenType() + " " + oAuthToken.getAccessToken()).observe(this, new Observer<List<Tweet>>() {
            @Override
            public void onChanged(List<Tweet> tweets) {
                tweetAdapter.setItemList(tweets);
            }
        });

    }


}
