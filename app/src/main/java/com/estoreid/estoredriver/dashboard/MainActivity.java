package com.estoreid.estoredriver.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.estoreid.estoredriver.R;
import com.estoreid.estoredriver.dashboard.adapter.PagerAdapter;
import com.estoreid.estoredriver.dashboard.fragments.Delivered;
import com.estoreid.estoredriver.dashboard.fragments.Ongoing;
import com.estoreid.estoredriver.dashboard.fragments.Return;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    TabLayout dashboard_tablayout;
    TabItem ongoing,delivered,returns;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        init();
        listerners();
    }

    private void init() {

        dashboard_tablayout = (TabLayout)findViewById(R.id.dashboard_tablayout);
        ongoing = (TabItem)dashboard_tablayout.findViewById(R.id.ongoing);
        delivered =(TabItem)dashboard_tablayout.findViewById(R.id.delivered);
        returns = (TabItem)dashboard_tablayout.findViewById(R.id.returns);
        //pager = (ViewPager)findViewById(R.id.pager);
        fragmentManager.beginTransaction().replace(R.id.container,new Ongoing()).commit();
    }

    private void listerners() {

        dashboard_tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               switch (tab.getPosition())
               {
                   case 0:
                       fragmentManager.beginTransaction().replace(R.id.container,new Ongoing()).commit();

                       break;
                   case 1:
                       fragmentManager.beginTransaction().replace(R.id.container,new Delivered()).commit();
                       break;

                   case 2:
                       fragmentManager.beginTransaction().replace(R.id.container,new Return()).commit();
                       break;
               }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
