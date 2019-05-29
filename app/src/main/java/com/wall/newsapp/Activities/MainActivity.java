package com.wall.newsapp.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.wall.newsapp.Adapters.SectionPagerAdapter;
import com.wall.newsapp.Fragments.BusinessFragment;
import com.wall.newsapp.Fragments.EntertainmentFragment;
import com.wall.newsapp.Fragments.HeadLinesFragment;
import com.wall.newsapp.Fragments.HealthFragment;
import com.wall.newsapp.Fragments.ScienceFragment;
import com.wall.newsapp.Fragments.SportsFragment;
import com.wall.newsapp.Fragments.TechnologyFragment;
import com.wall.newsapp.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SectionPagerAdapter msectionPagerAdapter;
    private ViewPager viewPager;
    private Toolbar mtoolbar;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initAll();
    }
    private void initToolbar() {

        mtoolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
    }
    private void initAll() {
        viewPager = findViewById(R.id.container);
        setUpViewPager(viewPager);
        tabLayout=findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setUpViewPager(ViewPager viewPager) {
        msectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        msectionPagerAdapter.AddFragment(new HeadLinesFragment(),"HeadLines");
        msectionPagerAdapter.AddFragment(new BusinessFragment(),"Business");
        msectionPagerAdapter.AddFragment(new EntertainmentFragment(),"Entertainment");
        msectionPagerAdapter.AddFragment(new HealthFragment(),"Health");
        msectionPagerAdapter.AddFragment(new ScienceFragment(),"Science");
        msectionPagerAdapter.AddFragment(new SportsFragment(),"Sports");
        msectionPagerAdapter.AddFragment(new TechnologyFragment(),"Technology");
        viewPager.setAdapter(msectionPagerAdapter);



    }
}
