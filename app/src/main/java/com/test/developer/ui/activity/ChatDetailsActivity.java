package com.test.developer.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.test.developer.R;
import com.test.developer.data.model.User;
import com.test.developer.databinding.ActivityChatDetailsBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatDetailsActivity extends AppCompatActivity {
    public static final String INTENT_KEY_USER = "INTENT_KEY_USER";

    private User user;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChatDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_details);
        ButterKnife.bind(this);

        if (!getUserFromIntent())
            return;

        binding.setUser(user);
        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(user.getFullName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_channel_details_activity, menu);
        return true;
    }

    private Boolean getUserFromIntent() {
        if (getIntent().getExtras() == null || !getIntent().getExtras().containsKey(INTENT_KEY_USER))
            return false;

        user = ((User) getIntent().getExtras().getSerializable(INTENT_KEY_USER));
        return true;
    }
}
