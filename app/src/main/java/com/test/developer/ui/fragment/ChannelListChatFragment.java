package com.test.developer.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.developer.R;
import com.test.developer.ui.contract.ChannelListContract;
import com.test.developer.ui.presenter.ChannelListChatPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChannelListChatFragment extends Fragment implements ChannelListContract.Chat.View {
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.pb_loading)
    public ProgressBar progressBar;

    @BindView(R.id.tv_try_again)
    public TextView tvTryAgain;

    @BindView(R.id.btn_try_again)
    public Button btnTryAgain;

    private ChannelListContract.Chat.Presenter presenter = new ChannelListChatPresenterImpl();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_channel_list_chat, container, false);
        ButterKnife.bind(this, view);

        presenter.setView(this);
        setupRecyclerView();
        presenter.start();

        return view;
    }

    @Override
    public void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getViewContext()));
        recyclerView.setAdapter(presenter.setupAdapter());
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void showLoadingIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        hideLoadingIndicator();
        tvTryAgain.setVisibility(View.VISIBLE);
        btnTryAgain.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorMessage() {
        tvTryAgain.setVisibility(View.GONE);
        btnTryAgain.setVisibility(View.GONE);
    }

    @Override
    @OnClick(R.id.btn_try_again)
    public void tryAgainClick() {
        presenter.reloadChatList();
    }
}
