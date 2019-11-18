package com.estoreid.estoredriver.dashboard.adapter;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter {

    Context context;
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addfragmnets(Fragment fragment) {
        fragments.add(fragment);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
