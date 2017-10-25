package com.test.developer.ui.callback;

import com.test.developer.data.model.Channel;

import java.util.List;

public interface ChannelLoadListener {
    void onLoaded(List<Channel> channels);
}
