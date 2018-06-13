package com.kcr.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kcr.main.ui.fragment.HomeWelfareFragment;


public class HomePageAdapter extends FragmentPagerAdapter {
    private String[] mTitle = new String[]{"未完成", "已完成"};

    public HomePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return HomeWelfareFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}