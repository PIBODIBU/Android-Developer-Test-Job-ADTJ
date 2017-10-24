package com.test.developer.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.test.developer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChannelListChatViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.civ_avatar)
    public CircleImageView civAvatar;

    @BindView(R.id.tv_name)
    public TextView tvName;

    @BindView(R.id.tv_text)
    public TextView tvText;

    @BindView(R.id.tv_date)
    public TextView tvDate;

    @BindView(R.id.container_badge)
    public View containerBadge;

    @BindView(R.id.tv_badge)
    public TextView tvBadge;

    @BindView(R.id.root_view)
    public View rootView;

    public ChannelListChatViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
