package com.lester.demo.presentation.ui.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.lester.demo.R;
import com.lester.demo.data.model.UserE;
import com.lester.demo.presentation.ui.adapter.PaginationListener;
import com.lester.demo.presentation.ui.adapter.UserAdapter;
import com.lester.demo.presentation.ui.fragment.UserDetailFragment;
import com.lester.demo.presentation.viewmodel.UserDataViewModel;
import com.lester.demo.presentation.viewmodel.UserDataViewModelFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeUserActivity extends BaseActivity implements UserAdapter.Callback, UserDetailFragment.Callback{

    private boolean mTwoPane;

    private LinearLayoutManager layoutManager;
    private UserAdapter userAdapter;
    private List<UserE> userList = new ArrayList<>();
    private Map<Integer, Integer> userFilterMap = new HashMap<>();

    private UserDataViewModel userDataViewModel;

    private int currentPage = PaginationListener.PAGE_START;
    private boolean isLastPage = false;
    private final int TOTAL_PER_PAGE = 50;
    private boolean isLoading = false;

    private SearchView searchView = null;
    private SimpleCursorAdapter searchAdapter;

    private View v_root;

    private UserDetailFragment detailFragment = new UserDetailFragment();

    public static final int REQUEST_USER_UPDATES = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        mTwoPane = findViewById(R.id.user_detail_container) != null;

        v_root = findViewById(R.id.v_root);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        searchAdapter =  new SimpleCursorAdapter(this, android.R.layout.simple_spinner_dropdown_item, null, new String[] {"userName"}, new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        RecyclerView rv_user_list = findViewById(R.id.rv_user_list);
        layoutManager = new LinearLayoutManager(this);
        rv_user_list.setLayoutManager(layoutManager);
        userAdapter = new UserAdapter(userList, this);
        rv_user_list.setAdapter(userAdapter);

        rv_user_list.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                loadUsers();
            }
            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
            @Override
            public boolean isLoading() {
                return isLoading;
            }
            @Override
            public int getPageSize() {
                return TOTAL_PER_PAGE;
            }
        });

        userDataViewModel = ViewModelProviders.of(this, new UserDataViewModelFactory(this)).get(UserDataViewModel.class);
        userDataViewModel.getDataListLive().observe(this, new Observer<List<UserE>>() {
            @Override
            public void onChanged(List<UserE> list) {
                onDataReady(list);
            }
        });
        userDataViewModel.getMessageLive().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                showMessage(message);
            }
        });

        loadfavorites();
        loadUsers();
    }

    private void showMessage(String message) {
        Snackbar snackbar = Snackbar.make(v_root, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void loadfavorites() {
        userDataViewModel.loadFavorites();
    }

    private void loadUsers() {
        userDataViewModel.loadUserData(currentPage, TOTAL_PER_PAGE);
    }

    public void onDataReady(List<UserE> dataList) {
        if (currentPage != PaginationListener.PAGE_START) userAdapter.removeLoading();
        userList.addAll(dataList);

        if (currentPage < TOTAL_PER_PAGE) {
            userAdapter.addLoading();
        } else {
            isLastPage = true;
        }
        isLoading = false;
        currentPage++;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) searchView = (SearchView) searchItem.getActionView();

        if (searchView != null && searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setSuggestionsAdapter(searchAdapter);
            searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
                @Override
                public boolean onSuggestionClick(int position) {
                    int itemIdx = userFilterMap.get(position);
                    searchView.setQuery(userList.get(itemIdx).getName().getFullName(),false);
                    searchView.clearFocus();
                    onItemClick(itemIdx);
                    return true;
                }
                @Override
                public boolean onSuggestionSelect(int position) { return true; }
            });
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) { return false; }
                @Override
                public boolean onQueryTextChange(String s) {
                    userFilterMap.clear();
                    int idx = 0;
                    final MatrixCursor mc = new MatrixCursor(new String[]{ BaseColumns._ID, "userName"});
                    for (int i=0; i<userList.size()-1; i++) {
                        if (userList.get(i).getName().getFullName().toLowerCase().contains(s.toLowerCase())){
                            mc.addRow(new Object[] {idx, userList.get(i).getName().getFullName()});
                            userFilterMap.put(idx, i);
                            idx++;
                        }
                    }
                    searchAdapter.changeCursor(mc);
                    return false;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_USER_UPDATES) {
            if(resultCode == Activity.RESULT_OK){
                int idx = data.getIntExtra(UserDetailFragment.ARG_ITEM_IDX, 0);
                boolean isAddFav = data.getBooleanExtra(UserDetailFragment.ARG_ACTION_ADD, false);
                if(isAddFav) updateFavoriteAdded(idx);
                else updateFavoriteRemoved(idx);
            }
        }
    }

    private void showUserDetail(int position) {
        if (mTwoPane) {
            detailFragment = new UserDetailFragment();
            Bundle arguments = new Bundle();
            arguments.putString(UserDetailFragment.ARG_ITEM_TITLE, userList.get(position).getName().getFullName());
            arguments.putInt(UserDetailFragment.ARG_ITEM_IDX, position);
            arguments.putSerializable(UserDetailFragment.ARG_DATA, userList.get(position));


            detailFragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().replace(R.id.user_detail_container, detailFragment).commit();
        } else {
            Intent intent = new Intent(this, UserDetailActivity.class);
            intent.putExtra(UserDetailFragment.ARG_ITEM_TITLE, userList.get(position).getName().getFullName());
            intent.putExtra(UserDetailFragment.ARG_ITEM_IDX, position);
            Bundle b = new Bundle();
            b.putSerializable(UserDetailFragment.ARG_DATA, userList.get(position));
            intent.putExtras(b);
            startActivityForResult(intent, REQUEST_USER_UPDATES);
        }
    }

    @Override
    public void onItemClick(int position) {
        showUserDetail(position);
    }

    @Override
    public void addFavorite(int currentPosition) {
        if(mTwoPane) detailFragment.setFavorite(currentPosition, true);
        userDataViewModel.addFavorite(userList.get(currentPosition));

        updateFavoriteAdded(currentPosition);
    }
    private void updateFavoriteAdded(int currentPosition) {
        int idx = 0;
        for (UserE usr: userList){
            if(!usr.isFavorite() && idx < userList.size() - 1){
                if(idx == currentPosition + 1) break;

                userList.get(currentPosition).setFavorite(true);
                Collections.swap(userList, currentPosition, idx);
                userAdapter.notifyItemMoved(currentPosition, idx);
                userAdapter.notifyItemChanged(idx);
                userAdapter.notifyItemChanged(currentPosition);

                layoutManager.scrollToPosition(idx);
                break;
            }
            idx++;
        }
    }

    @Override
    public void removeFavorite(int currentPosition) {
        if(mTwoPane) detailFragment.setFavorite(currentPosition, false);
        userDataViewModel.removeFavorite(userList.get(currentPosition).getFavorite_id());

        updateFavoriteRemoved(currentPosition);
    }

    private void updateFavoriteRemoved(int currentPosition) {
        int idx = 0;
        for (UserE usr: userList){
            if(!usr.isFavorite() && idx < userList.size() - 1){
                userList.get(currentPosition).setFavorite(false);
                if(idx == currentPosition + 1) break;

                userList.add(idx, userList.get(currentPosition));
                userAdapter.notifyItemChanged(idx);

                userList.remove(currentPosition);
                userAdapter.notifyItemRemoved(currentPosition);

                userAdapter.notifyDataSetChanged();

                layoutManager.scrollToPosition(idx);
                break;
            }
            idx++;
        }
    }
}