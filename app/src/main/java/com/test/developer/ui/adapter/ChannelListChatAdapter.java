package com.test.developer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.test.developer.R;
import com.test.developer.data.model.Channel;
import com.test.developer.ui.holder.ChannelListChatViewHolder;
import com.test.developer.ui.holder.SectionDividerViewHolder;

import java.util.LinkedList;

public class ChannelListChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Integer TYPE_DEFAULT = 0;
    private final Integer TYPE_DIVIDER = 1;

    private Context context;
    private LinkedList<Channel> channels = new LinkedList<>();
    private ChannelClickListener channelClickListener;

    public ChannelListChatAdapter(Context context, ChannelClickListener channelClickListener) {
        this.context = context;
        this.channelClickListener = channelClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_DEFAULT)
            return new ChannelListChatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel, parent, false));
        else if (viewType == TYPE_DIVIDER)
            return new SectionDividerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_divider, parent, false));
        else
            return new ChannelListChatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (channels.get(position) == null)
            return TYPE_DIVIDER;
        else
            return TYPE_DEFAULT;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ChannelListChatViewHolder) {
            final Channel channel = channels.get(position);

            Picasso.with(context)
                    .load(channel.getLastMessage().getSender().getPhotoUrl())
                    .into(((ChannelListChatViewHolder) holder).civAvatar);

            ((ChannelListChatViewHolder) holder).tvName.setText(channel.getLastMessage().getSender().getFullName());
            ((ChannelListChatViewHolder) holder).tvText.setText(channel.getLastMessage().getText());

            if (channel.getUnreadCount() != 0)
                ((ChannelListChatViewHolder) holder).tvBadge.setText(String.valueOf(channel.getUnreadCount()));
            else
                ((ChannelListChatViewHolder) holder).containerBadge.setVisibility(View.GONE);

            if (channelClickListener != null)
                ((ChannelListChatViewHolder) holder).rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        channelClickListener.onChannelClick(channel);
                    }
                });
        }
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    public LinkedList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(LinkedList<Channel> channels) {
        this.channels = channels;
    }

    public interface ChannelClickListener {
        void onChannelClick(Channel channel);
    }
}
