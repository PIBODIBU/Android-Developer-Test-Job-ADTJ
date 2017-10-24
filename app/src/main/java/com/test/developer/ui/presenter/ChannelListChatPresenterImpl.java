package com.test.developer.ui.presenter;

import com.test.developer.R;
import com.test.developer.data.api.ChannelAPI;
import com.test.developer.data.api.ServiceGenerator;
import com.test.developer.data.model.Channel;
import com.test.developer.data.model.response.ChannelListResponse;
import com.test.developer.ui.adapter.ChannelListChatAdapter;
import com.test.developer.ui.contract.ChannelListContract;

import java.util.Collections;
import java.util.Comparator;
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

    }
}
