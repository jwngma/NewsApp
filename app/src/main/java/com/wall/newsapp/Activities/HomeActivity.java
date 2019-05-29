package com.wall.newsapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.wall.newsapp.Adapters.SectionPagerAdapter;
import com.wall.newsapp.Fragments.BusinessFragment;
import com.wall.newsapp.Fragments.EntertainmentFragment;
import com.wall.newsapp.Fragments.HeadLinesFragment;
import com.wall.newsapp.Fragments.HealthFragment;
import com.wall.newsapp.Fragments.ScienceFragment;
import com.wall.newsapp.Fragments.SportsFragment;
import com.wall.newsapp.Fragments.TechnologyFragment;
import com.wall.newsapp.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "HomeActivity";

    private Toolbar mtoolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private SectionPagerAdapter msectionPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initToolbar();
        initDrawer();
        initAll();





    }


    private void initToolbar() {
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void initAll() {
        viewPager = findViewById(R.id.container);
        setUpViewPager(viewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initDrawer() {

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, mtoolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setUpViewPager(ViewPager viewPager) {
        msectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        msectionPagerAdapter.AddFragment(new HeadLinesFragment(), "HeadLines");
        msectionPagerAdapter.AddFragment(new BusinessFragment(), "Business");
        msectionPagerAdapter.AddFragment(new EntertainmentFragment(), "Entertainment");
        msectionPagerAdapter.AddFragment(new HealthFragment(), "Health");
        msectionPagerAdapter.AddFragment(new ScienceFragment(), "Science");
        msectionPagerAdapter.AddFragment(new SportsFragment(), "Sports");
        msectionPagerAdapter.AddFragment(new TechnologyFragment(), "Technology");
        viewPager.setAdapter(msectionPagerAdapter);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_alert) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent homeIntent = new Intent(HomeActivity.this, HomeActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(homeIntent);
                closeDrawer();
                break;
            case R.id.nav_video:
                Intent videoIntent = new Intent(HomeActivity.this, VideoNewsActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(videoIntent);
                closeDrawer();
                break;
            case R.id.nav_fav:
                Intent favIntent = new Intent(HomeActivity.this, FavoriteNewsActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(favIntent);
                closeDrawer();
                break;
            case R.id.nav_share:
                Intent shareintent= new Intent(Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                String shareSub="Sharing News";
                String shareBody="You Can Download this News from: ";
                shareintent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                shareintent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(shareintent,"Share Using"));
                closeDrawer();
                break;
            case R.id.nav_rate:
                Intent rateIntent = new Intent(HomeActivity.this, HelpActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(rateIntent);
                closeDrawer();
                break;
            case R.id.nav_help:
                Intent helpIntent = new Intent(HomeActivity.this, HelpActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(helpIntent);
                closeDrawer();
                break;

        }

        return true;
    }

    private void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

}
