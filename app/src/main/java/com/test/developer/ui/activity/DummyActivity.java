package com.test.developer.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.developer.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DummyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void start() {
        startActivity(new Intent(DummyActivity.this, ChannelListActivity.class));
        finish();
    }
}
