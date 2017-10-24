package com.test.developer.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.LinkedList;

public class ChannelListPagerAdapter extends FragmentStatePagerAdapter {
    private LinkedList<Fragment> fragments = new LinkedList<>();
    private LinkedList<String> titles = new LinkedList<>();

    public ChannelListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ChannelListPagerAdapter addFragment(Fragment fragment, String title) {
        this.fragments.add(fragment);
        this.titles.add(title);
        return this;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
