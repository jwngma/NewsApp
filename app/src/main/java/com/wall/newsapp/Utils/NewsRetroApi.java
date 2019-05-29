package com.wall.newsapp.Utils;

import com.wall.newsapp.Models.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsRetroApi {

    @GET("v2/top-headlines?country=in&category=business&apiKey=4d3e09a5446348ca9be4ae15697fe125")
    Call<NewsModel> getBusinessNews();

    @GET("v2/top-headlines?country=in&category=entertainment&apiKey=4d3e09a5446348ca9be4ae15697fe125")
    Call<NewsModel> getEntertainmentNews();

    @GET("v2/top-headlines?country=in&category=health&apiKey=4d3e09a5446348ca9be4ae15697fe125")
    Call<NewsModel> getHealthNews();

    @GET("v2/top-headlines?country=in&category=science&apiKey=4d3e09a5446348ca9be4ae15697fe125")
    Call<NewsModel> getScienceNews();

    @GET("v2/top-headlines?country=in&category=sports&apiKey=4d3e09a5446348ca9be4ae15697fe125")
    Call<NewsModel> getSportsNews();

    @GET("v2/top-headlines?country=in&category=technology&apiKey=4d3e09a5446348ca9be4ae15697fe125")
    Call<NewsModel> getTechnologyNews();

}
