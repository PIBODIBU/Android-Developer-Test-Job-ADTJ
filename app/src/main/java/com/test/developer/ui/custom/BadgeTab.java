package com.test.developer.ui.custom;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.test.developer.R;

public class BadgeTab {
    private Context context;
    private View tab;
    private TextView tvTitle;
    private View containerBadge;
    private TextView tvBadge;

    public BadgeTab(Context context, View tab, TextView tvTitle, View containerBadge, TextView tvBadge) {
        this.context = context;
        this.tab = tab;
        this.tvTitle = tvTitle;
        this.containerBadge = containerBadge;
        this.tvBadge = tvBadge;
    }

    public BadgeTab(Context context, View tab, TextView tvTitle, View containerBadge, TextView tvBadge, View.OnClickListener onClickListener) {
        this.context = context;
        this.tab = tab;
        this.tvTitle = tvTitle;
        this.containerBadge = containerBadge;
        this.tvBadge = tvBadge;
        this.tab.setOnClickListener(onClickListener);
    }

    public void select() {
        tab.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_selected));
        tvTitle.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
        containerBadge.setBackground(ContextCompat.getDrawable(context, R.drawable.badge));
        tvBadge.setTextColor(ContextCompat.getColor(context, R.color.white));
    }

    public void unSelect() {
        tab.setBackground(ContextCompat.getDrawable(context, android.R.color.transparent));
        tvTitle.setTextColor(ContextCompat.getColor(context, R.color.colorTextTools));
        containerBadge.setBackground(ContextCompat.getDrawable(context, R.drawable.badge_unselected));
        tvBadge.setTextColor(ContextCompat.getColor(context, R.color.colorTextTools));
    }

    public void setBadge(Integer badge) {
        this.tvBadge.setText(String.valueOf(badge));
    }
}
