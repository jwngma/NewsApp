package com.wall.newsapp.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wall.newsapp.Adapters.NewsAdapter;
import com.wall.newsapp.Adapters.NewsRecyclerviewAdapter;
import com.wall.newsapp.Models.NewsData;
import com.wall.newsapp.Models.NewsModel;
import com.wall.newsapp.R;
import com.wall.newsapp.Utils.NewsRetroApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class EntertainmentFragment extends Fragment {

    private static final String TAG = "EntertainmentFragment";
    String url = "https://newsapi.org/";
    private NewsAdapter newsAdapter;


    private RecyclerView recy;
    private NewsRecyclerviewAdapter adapter;
    private List<NewsData> newsDataList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entertainment, container, false);

        initRecyclerview(view);
        return view;

    }

    private void initRecyclerview(View view) {

        recy = view.findViewById(R.id.entertainment_recy);
        recy.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recy.setLayoutManager(layoutManager);
        newsDataList = new ArrayList<>();


        adapter = new NewsRecyclerviewAdapter(getContext(), newsDataList);
        recy.setAdapter(adapter);
        ParseJson();
    }

    private void ParseJson() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsRetroApi newsRetroApi = retrofit.create(NewsRetroApi.class);
        Call<NewsModel> call = newsRetroApi.getEntertainmentNews();
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                generateNewsList(response.body().getArticles());

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void generateNewsList(ArrayList<NewsModel.Article> articles) {
        newsAdapter = new NewsAdapter(getContext(), articles);
        recy.setAdapter(newsAdapter);



    }

}
