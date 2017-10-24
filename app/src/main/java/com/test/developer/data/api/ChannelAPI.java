package com.test.developer.data.api;

import com.test.developer.data.model.response.ChannelListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ChannelAPI {
    @GET("chat/channels/")
    Call<ChannelListResponse> getChannelList();
}
