package com.wall.newsapp.Models;

public class NewsData {

    private String author;
    private String title;
    private String urlToImage;
    private String url;

    public NewsData(String author, String title, String urlToImage, String url) {
        this.author = author;
        this.title = title;
        this.urlToImage = urlToImage;
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
