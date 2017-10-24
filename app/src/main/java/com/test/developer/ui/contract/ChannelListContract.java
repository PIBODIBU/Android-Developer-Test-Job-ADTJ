package com.test.developer.ui.contract;

import com.test.developer.data.model.Channel;
import com.test.developer.ui.adapter.ChannelListChatAdapter;

import java.util.LinkedList;

public interface ChannelListContract {
    interface Chat {
        interface Presenter extends BasePresenter<View> {
            void fetchChatList();

            LinkedList<Channel> prepareDataSet(LinkedList<Channel> channels);

            ChannelListChatAdapter setupAdapter();

            void reloadChatList();
        }

        interface View extends BaseView {
            void showLoadingIndicator();

            void hideLoadingIndicator();

            void showErrorMessage();

            void hideErrorMessage();

            void tryAgainClick();

            void setupRecyclerView();
        }
    }

    interface ChatLive {
        interface Presenter extends BasePresenter<View> {
        }

        interface View extends BaseView {
        }
    }
}
