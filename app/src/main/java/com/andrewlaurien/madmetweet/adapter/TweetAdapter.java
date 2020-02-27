package com.andrewlaurien.madmetweet.adapter;

import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andrewlaurien.madmetweet.R;
import com.andrewlaurien.madmetweet.model.Tweet;
import com.bumptech.glide.Glide;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    private ArrayList<Tweet> mList = new ArrayList<>();
    Context context;

    public TweetAdapter(Context context) {
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tweet)
        TextView tweet;
        @BindView(R.id.screenname)
        TextView screenname;
        @BindView(R.id.userid)
        TextView userid;
        @BindView(R.id.profile)
        ImageView profile;
        @BindView(R.id.datecreated)
        TextView datecreated;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = mList.get(position);
        try {
            holder.tweet.setText(URLDecoder.decode(tweet.getText().toString(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        holder.screenname.setText("" + tweet.getUser().getScreen_name());
        holder.userid.setText("" + tweet.getUser().getName());
        Linkify.addLinks(holder.tweet, Linkify.WEB_URLS);

        Glide.with(context)
                .load(tweet.getUser().getProfile_background_image_url())
                .into(holder.profile);

        holder.datecreated.setText(tweet.getCreated_at());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setItemList(List<Tweet> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }


}
