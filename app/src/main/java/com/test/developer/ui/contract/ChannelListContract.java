package com.test.developer.ui.contract;

import com.test.developer.data.model.Channel;
import com.test.developer.ui.adapter.ChannelListChatAdapter;
import com.test.developer.ui.callback.ChannelLoadListener;

import java.util.LinkedList;

public interface ChannelListContract {
    interface Chat {
        interface Presenter extends BasePresenter<View> {
            void fetchChatList();

            void fetchChatList(ChannelLoadListener loadListener);

            LinkedList<Channel> prepareDataSet(LinkedList<Channel> channels);

            ChannelListChatAdapter setupAdapter();

            void reloadChatList();

            Integer getUnreadCount();
        }

        interface View extends BaseView {
            void showLoadingIndicator();

            void hideLoadingIndicator();

            void showErrorMessage();

            void hideErrorMessage();

            void tryAgainClick();

            void setupRecyclerView();

            Presenter getPresenter();
        }
    }

    interface ChatLive {
        interface Presenter extends BasePresenter<View> {
        }

        interface View extends BaseView {
        }
    }
}
