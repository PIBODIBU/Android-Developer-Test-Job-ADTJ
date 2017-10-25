package com.test.developer.ui.presenter;

import android.content.Intent;

import com.test.developer.R;
import com.test.developer.data.api.ChannelAPI;
import com.test.developer.data.api.ServiceGenerator;
import com.test.developer.data.model.Channel;
import com.test.developer.data.model.response.ChannelListResponse;
import com.test.developer.ui.activity.ChatDetailsActivity;
import com.test.developer.ui.adapter.ChannelListChatAdapter;
import com.test.developer.ui.callback.ChannelLoadListener;
import com.test.developer.ui.contract.ChannelListContract;

import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChannelListChatPresenterImpl implements ChannelListContract.Chat.Presenter, ChannelListChatAdapter.ChannelClickListener {
    private ChannelListContract.Chat.View view;
    private ChannelListChatAdapter adapter;

    @Override
    public LinkedList<Channel> prepareDataSet(LinkedList<Channel> channels) {
        LinkedList<Channel> channelsRead = new LinkedList<>();
        LinkedList<Channel> channelsUnread = new LinkedList<>();

        for (Channel channel : channels) {
            if (channel.getUnreadCount() == 0)
                channelsRead.add(channel);
            else
                channelsUnread.add(channel);
        }

        channels.clear();
        channels.add(null);
        channels.addAll(channelsUnread);
        channels.add(null);
        channels.addAll(channelsRead);

        return channels;
    }

    @Override
    public void start() {
        view.showLoadingIndicator();
        fetchChatList();
    }

    @Override
    public ChannelListChatAdapter setupAdapter() {
        if (adapter == null)
            adapter = new ChannelListChatAdapter(view.getViewContext(), this);

        return adapter;
    }

    @Override
    public void fetchChatList() {
        ServiceGenerator.createService(ChannelAPI.class,
                view.getViewContext().getResources().getString(R.string.api_login),
                view.getViewContext().getResources().getString(R.string.api_password))
                .getChannelList().enqueue(new Callback<ChannelListResponse>() {
            @Override
            public void onResponse(Call<ChannelListResponse> call, Response<ChannelListResponse> response) {
                if (!response.isSuccessful() || response.body() == null || response.body().getData() == null) {
                    view.showErrorMessage();
                    return;
                }

                adapter.setChannels(prepareDataSet(new LinkedList<>(response.body().getData())));
                adapter.notifyDataSetChanged();
                view.hideLoadingIndicator();
            }

            @Override
            public void onFailure(Call<ChannelListResponse> call, Throwable t) {
                view.showErrorMessage();
            }
        });
    }

    @Override
    public void fetchChatList(final ChannelLoadListener loadListener) {
        ServiceGenerator.createService(ChannelAPI.class,
                view.getViewContext().getResources().getString(R.string.api_login),
                view.getViewContext().getResources().getString(R.string.api_password))
                .getChannelList().enqueue(new Callback<ChannelListResponse>() {
            @Override
            public void onResponse(Call<ChannelListResponse> call, Response<ChannelListResponse> response) {
                if (!response.isSuccessful() || response.body() == null || response.body().getData() == null) {
                    return;
                }

                if (loadListener != null)
                    loadListener.onLoaded(response.body().getData());
            }

            @Override
            public void onFailure(Call<ChannelListResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void reloadChatList() {
        view.hideErrorMessage();
        view.showLoadingIndicator();
        adapter.getChannels().clear();
        fetchChatList();
    }

    @Override
    public void setView(ChannelListContract.Chat.View view) {
        this.view = view;
    }

    @Override
    public void onChannelClick(Channel channel) {
        view.getViewContext().startActivity(new Intent(view.getViewContext(), ChatDetailsActivity.class)
                .putExtra(ChatDetailsActivity.INTENT_KEY_USER, channel.getLastMessage().getSender()));
    }

    @Override
    public Integer getUnreadCount() {
        return adapter.getChannels().size();
    }
}
