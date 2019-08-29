package com.lester.demo.presentation.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.lester.demo.R;
import com.lester.demo.data.model.UserE;
import com.lester.demo.presentation.ui.fragment.UserDetailFragment;
import com.lester.demo.presentation.viewmodel.UserDataViewModel;
import com.lester.demo.presentation.viewmodel.UserDataViewModelFactory;

import java.util.List;

public class UserDetailActivity extends BaseActivity implements UserDetailFragment.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(UserDetailFragment.ARG_ITEM_TITLE, getIntent().getStringExtra(UserDetailFragment.ARG_ITEM_TITLE));
            arguments.putInt(UserDetailFragment.ARG_ITEM_IDX, getIntent().getIntExtra(UserDetailFragment.ARG_ITEM_IDX, 0));
            arguments.putSerializable(UserDetailFragment.ARG_DATA, getIntent().getSerializableExtra(UserDetailFragment.ARG_DATA));
            UserDetailFragment fragment = new UserDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.user_detail_container, fragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addFavorite(int index) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(UserDetailFragment.ARG_ITEM_IDX, index);
        returnIntent.putExtra(UserDetailFragment.ARG_ACTION_ADD, true);
        setResult(Activity.RESULT_OK, returnIntent);
    }

    @Override
    public void removeFavorite(int index) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(UserDetailFragment.ARG_ITEM_IDX, index);
        returnIntent.putExtra(UserDetailFragment.ARG_ACTION_ADD, false);
        setResult(Activity.RESULT_OK, returnIntent);
    }
}