package com.wall.newsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wall.newsapp.Activities.NewsDetailsActivity;
import com.wall.newsapp.Models.NewsModel;
import com.wall.newsapp.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private static final String TAG = "NewsAdapter";
    private Context context;
    private ArrayList<NewsModel.Article> newsModelArrayList;

    public NewsAdapter(Context context, ArrayList<NewsModel.Article> newsModelArrayList) {
        this.context = context;
        this.newsModelArrayList = newsModelArrayList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_items_layout,viewGroup,false);
        return  new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder newsViewHolder, int position) {
        NewsModel.Article newsModel=newsModelArrayList.get(position);
        final String url;

        url=newsModel.getUrl().toString();

        newsViewHolder.title.setText(newsModel.getTitle());
        newsViewHolder.author.setText("Author:"+newsModel.getAuthor());
        final String image_url=newsModel.getUrlToImage();
        Picasso.get().load(image_url).placeholder(R.drawable.news_logo).networkPolicy(NetworkPolicy.OFFLINE).into(newsViewHolder.image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(image_url).into(newsViewHolder.image);

            }
        });

        newsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(context, NewsDetailsActivity.class);
                intent.putExtra("url",url);
                context.startActivity(intent);



            }
        });
        newsViewHolder.image_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareintent= new Intent(Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                String shareSub="Sharing News";
                String shareBody="You Can View This News From : "+url;
                shareintent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                shareintent.putExtra(Intent.EXTRA_TEXT,shareBody);
                context.startActivity(Intent.createChooser(shareintent,"Share Using"));

            }
        });
        newsViewHolder.image_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: popup clicked");
                PopupMenu popupMenu= new PopupMenu(context,v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_news,popupMenu.getMenu());
                
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()){

                            case R.id.action_alert1:
                                Toast.makeText(context, "You have Clicked"+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_about1:
                                Toast.makeText(context, "You have Clicked"+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return newsModelArrayList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView image,image_share,image_more;
        private TextView title;
        private TextView author;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.news_image);
            image_share=itemView.findViewById(R.id.image_share);
            image_more=itemView.findViewById(R.id.image_more);
            title=itemView.findViewById(R.id.news_title);
            author=itemView.findViewById(R.id.news_author);

        }
    }
}
