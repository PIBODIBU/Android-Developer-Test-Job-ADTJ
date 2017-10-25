package com.test.developer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.test.developer.R;
import com.test.developer.data.api.ChannelAPI;
import com.test.developer.data.api.ServiceGenerator;
import com.test.developer.data.model.Channel;
import com.test.developer.data.model.response.ChannelListResponse;
import com.test.developer.ui.callback.ChannelLoadListener;
import com.test.developer.ui.custom.BadgeTab;
import com.test.developer.ui.fragment.ChannelListChatFragment;
import com.test.developer.ui.fragment.ChannelListChatLiveFragment;
import com.test.developer.ui.adapter.ChannelListPagerAdapter;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChannelListActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.view_pager)
    public ViewPager viewPager;

    private LinkedList<BadgeTab> badgeTabs = new LinkedList<>();

    private ChannelListPagerAdapter adapter;
    private ChannelListChatFragment chatFragment = new ChannelListChatFragment();
    private ChannelListChatLiveFragment liveChatFragment = new ChannelListChatLiveFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_list);
        ButterKnife.bind(this);

        setupToolbar();
        setupViewPager();
        setupTabs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_channel_list_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(ChannelListActivity.this, DummyActivity.class));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupTabs() {
        badgeTabs.add(new BadgeTab(
                this,
                findViewById(R.id.tab_chat),
                ((TextView) findViewById(R.id.tv_title_chat)),
                findViewById(R.id.container_badge_chat),
                ((TextView) findViewById(R.id.tv_badge_chat)),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(0);
                    }
                }
        ));

        badgeTabs.add(new BadgeTab(
                this,
                findViewById(R.id.tab_chat_live),
                ((TextView) findViewById(R.id.tv_title_chat_live)),
                findViewById(R.id.container_badge_chat_live),
                ((TextView) findViewById(R.id.tv_badge_chat_live)),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(1);
                    }
                }
        ));

        badgeTabs.get(1).setBadge(1);

        ServiceGenerator.createService(ChannelAPI.class,
                getResources().getString(R.string.api_login),
                getResources().getString(R.string.api_password))
                .getChannelList().enqueue(new Callback<ChannelListResponse>() {
            @Override
            public void onResponse(Call<ChannelListResponse> call, Response<ChannelListResponse> response) {
                if (!response.isSuccessful() || response.body() == null || response.body().getData() == null) {
                    return;
                }

                Integer unread = 0;

                for (Channel channel : response.body().getData())
                    unread += channel.getUnreadCount();

                badgeTabs.get(0).setBadge(unread);
            }

            @Override
            public void onFailure(Call<ChannelListResponse> call, Throwable t) {
            }
        });
    }

    private void setupViewPager() {
        adapter = new ChannelListPagerAdapter(getSupportFragmentManager());
        adapter
                .addFragment(chatFragment, "")
                .addFragment(liveChatFragment, "");

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                badgeTabs.get(0).select();
                badgeTabs.get(1).unSelect();
                break;
            case 1:
                badgeTabs.get(0).unSelect();
                badgeTabs.get(1).select();
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}