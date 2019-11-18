package com.estoreid.estoredriver.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.os.Bundle;

import com.estoreid.estoredriver.R;
import com.estoreid.estoredriver.dashboard.adapter.PagerAdapter;
import com.estoreid.estoredriver.dashboard.fragments.Delivered;
import com.estoreid.estoredriver.dashboard.fragments.Ongoing;
import com.estoreid.estoredriver.dashboard.fragments.Return;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager dashboard_viewpager;
    TabLayout dashboard_tablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        listerners();
        setTabs();
    }

    private void init() {

        dashboard_tablayout = (TabLayout)findViewById(R.id.dashboard_tablayout);
        dashboard_viewpager = (ViewPager)findViewById(R.id.dashboard_viewpager);
    }

    private void listerners() {
    }

    private void setTabs() {
        PagerAdapter adapter=new PagerAdapter(getSupportFragmentManager());
        adapter.addfragmnets(new Ongoing(),"Ongoing");
        adapter.addfragmnets(new Delivered(),"Delivered");
        adapter.addfragmnets(new Return(),"Return");
        dashboard_viewpager.setAdapter(adapter);
        dashboard_tablayout.setupWithViewPager(dashboard_viewpager);
    }
}
