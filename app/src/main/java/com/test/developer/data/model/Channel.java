package com.test.developer.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Channel {
    @SerializedName("id")
    private Integer id;

    @SerializedName("unread_messages_count")
    private Integer unreadCount;

    @SerializedName("last_message")
    private Message lastMessage;

    @SerializedName("users")
    private List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
