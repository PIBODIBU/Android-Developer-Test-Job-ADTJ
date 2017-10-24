package com.test.developer.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.test.developer.R;
import com.test.developer.ui.fragment.ChannelListChatFragment;
import com.test.developer.ui.fragment.ChannelListChatLiveFragment;
import com.test.developer.ui.adapter.ChannelListPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChannelListActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.view_pager)
    public ViewPager viewPager;

    private ChannelListPagerAdapter adapter;
    private ChannelListChatFragment chatFragment = new ChannelListChatFragment();
    private ChannelListChatLiveFragment liveChatFragment = new ChannelListChatLiveFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_list);
        ButterKnife.bind(this);

        setupUi();
        setupViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_channel_list_activity, menu);
        return true;
    }

    private void setupUi() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupViewPager() {
        adapter = new ChannelListPagerAdapter(getSupportFragmentManager());
        adapter
                .addFragment(chatFragment, "")
                .addFragment(liveChatFragment, "");

        viewPager.setAdapter(adapter);
    }
}