package com.wall.newsapp.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wall.newsapp.Adapters.NewsRecyclerviewAdapter;
import com.wall.newsapp.Models.NewsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActor {

   // private static final String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=4d3e09a5446348ca9be4ae15697fe125";

    private Context context;
    private List<NewsData> newsDataList;
    private NewsRecyclerviewAdapter adapter;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;


    public MainActor(Context context, List<NewsData> newsDataList, NewsRecyclerviewAdapter adapter,
                     RecyclerView recyclerView, RequestQueue requestQueue) {
        this.context = context;
        this.newsDataList = newsDataList;
        this.adapter = adapter;
        this.recyclerView = recyclerView;
        this.requestQueue = requestQueue;
    }

    public void NewsApplication(String source1,String source2,String source3,String source4,String source5,String source6,String source7) {
        final String url="https://newsapi.org/v2/top-headlines?sources="+source1+","+source2+","+source3+","+source4+","+source5+","+source6+","+source7+"&apiKey=4d3e09a5446348ca9be4ae15697fe125";
        requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        NewsData newsData = new NewsData(
                                jsonObject.getString("author"),
                                jsonObject.getString("title"),
                                jsonObject.getString("urlToImage"),
                                jsonObject.getString("url"));

                        newsDataList.add(newsData);
                        adapter = new NewsRecyclerviewAdapter(context, newsDataList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
    }


}
