package com.wall.newsapp.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.wall.newsapp.Adapters.NewsRecyclerviewAdapter;
import com.wall.newsapp.Models.NewsData;
import com.wall.newsapp.R;
import com.wall.newsapp.Utils.MainActor;

import java.util.ArrayList;
import java.util.List;

public class HeadLinesFragment extends Fragment {
    private RecyclerView recy;
    private NewsRecyclerviewAdapter adapter;
    private List<NewsData> newsDataList;
    private MainActor mainActor;
    private RequestQueue requestQueue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_head_lines, container, false);

        initRecyclerview(view);
        return view;
    }



    private void initRecyclerview(View view) {

        recy = view.findViewById(R.id.headline_recy);
        recy.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recy.setLayoutManager(layoutManager);
        newsDataList = new ArrayList<>();

        mainActor=new MainActor(getContext(),newsDataList,adapter,recy,requestQueue);
        mainActor.NewsApplication("techcrunch","the-hindu","engadget","cnbc","business-insider","crypto-coins-news","techcrunch");

    }
}

//"techcrunch",,"engadget","cnbc","business-insider","crypto-coins-news","the-hindu"