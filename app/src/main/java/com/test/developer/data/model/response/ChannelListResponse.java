package com.test.developer.data.model.response;

import com.google.gson.annotations.SerializedName;
import com.test.developer.data.model.Channel;

import java.util.List;

public class ChannelListResponse extends BaseResponse<Channel> {
    @SerializedName("channels")
    private List<Channel> channels;

    @Override
    public List<Channel> getData() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
