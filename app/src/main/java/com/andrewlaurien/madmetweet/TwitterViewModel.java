package com.andrewlaurien.madmetweet;

import com.andrewlaurien.madmetweet.model.OAuthToken;
import com.andrewlaurien.madmetweet.model.Tweet;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class TwitterViewModel extends ViewModel {

    private TwitterRepository repository;
    private LiveData<OAuthToken> token;
    private LiveData<List<Tweet>> liveData;
    private LiveData<List<Tweet>> homeData;

    public TwitterViewModel() {
        this.repository = new TwitterRepository();
    }


    public LiveData<OAuthToken> getToken() {
        if (token == null) {
            token = repository.getToken();
        }
        return token;
    }


    public LiveData<List<Tweet>> getTimeLineTweets(String token) {
        if (liveData == null) {
            liveData = repository.getTweets(token);
        }

        return liveData;

    }


    public LiveData<List<Tweet>> getHomeTweet(String token) {
        homeData = repository.getHomeTweets(token);
        return homeData;
    }

}
