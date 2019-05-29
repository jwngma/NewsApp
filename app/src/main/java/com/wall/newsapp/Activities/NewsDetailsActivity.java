package com.wall.newsapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wall.newsapp.R;

public class NewsDetailsActivity extends AppCompatActivity {
    private static final String TAG = "NewsDetailsActivity";
    private WebView news_webview;
    private String url;
    private Toolbar mtoolbar;
    private ImageView close;
    private TextView url_box;
    private SwipeRefreshLayout detail_swipe;
    private ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        initAll();
        initToolbar();
        getInCominingIntent();
        setWebview();
    }

    private void initAll() {
        detail_swipe = findViewById(R.id.detail_swipe);
    }

    private void initToolbar() {
        mtoolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(mtoolbar);
        close = findViewById(R.id.close);
        url_box = findViewById(R.id.url_box);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void setWebview() {
        news_webview = findViewById(R.id.news_webview);
        progressDoalog = new ProgressDialog(NewsDetailsActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("Loading News");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();


        news_webview.getSettings().setLoadsImagesAutomatically(true);
        news_webview.getSettings().setJavaScriptEnabled(true);
        news_webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        news_webview.loadUrl(url);
        news_webview.setWebViewClient(new MyBrowser());
        detail_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                news_webview.reload();
            }
        });
    }

    private void getInCominingIntent() {
        if (getIntent().hasExtra("url")) {
            url = getIntent().getStringExtra("url");
            url_box.setText(url);

        }
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            detail_swipe.setRefreshing(false);


            progressDoalog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.detail_share:
                Intent shareintent = new Intent(Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                String shareSub = "Sharing News";
                String shareBody = "You Can View This News From : " + url;
                shareintent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                shareintent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareintent, "Share Using"));
                break;

            case R.id.detail_refresh:
                detail_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        news_webview.reload();
                    }
                });
                break;


        }

        return true;
    }
}
