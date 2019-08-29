package com.lester.demo.presentation.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import com.lester.demo.R;
import com.lester.demo.data.model.UserE;
import com.lester.demo.data.model.UserLocationE;
import com.lester.demo.presentation.ui.activity.UserDetailActivity;
import com.lester.demo.presentation.viewmodel.UserDataViewModel;
import com.lester.demo.presentation.viewmodel.UserDataViewModelFactory;

public class UserDetailFragment extends Fragment {
    public static final String ARG_ITEM_TITLE = "item_title";
    public static final String ARG_DATA = "user_data";
    public static final String ARG_ITEM_IDX = "item_idx";
    public static final String ARG_ACTION_ADD = "item_add";

    ToggleButton btn_favrorite;

    private UserE userData;
    private Callback callback;
    private int index;

    private UserDataViewModel userDataViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_DATA)) {
            userData = (UserE) getArguments().getSerializable(ARG_DATA);
            index = getArguments().getInt(ARG_ITEM_IDX);

            if(getActivity() instanceof UserDetailActivity){
                Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
                if (toolbar != null) {
                    toolbar.setTitle(userData.getName().getFullName());
                }
            }
        }

        userDataViewModel = ViewModelProviders.of(this, new UserDataViewModelFactory(getContext())).get(UserDataViewModel.class);
        userDataViewModel.getMessageLive().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                showMessage(message);
            }
        });
    }

    private void showMessage(String message) {
        Snackbar snackbar = Snackbar.make(getView(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_detail, container, false);

        btn_favrorite = rootView.findViewById(R.id.btn_favorite);
        ((TextView) rootView.findViewById(R.id.txt_name)).setText(userData.getName().getFullName());
        ((TextView) rootView.findViewById(R.id.txt_email)).setText(userData.getEmail());
        ((TextView) rootView.findViewById(R.id.txt_phone)).setText(userData.getCell());
        ((ToggleButton) rootView.findViewById(R.id.btn_favorite)).setChecked(userData.isFavorite());
        rootView.findViewById(R.id.btn_cell).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.callToUser(userData.getCell());
            }
        });
        ((TextView) rootView.findViewById(R.id.txt_phone)).setText(userData.getCell());
        rootView.findViewById(R.id.btn_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.callToUser(userData.getPhone());
            }
        });
        rootView.findViewById(R.id.btn_mail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.mailToUser(userData.getEmail());
            }
        });
        rootView.findViewById(R.id.btn_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.showUserLocation(userData.getLocation());
            }
        });
        rootView.findViewById(R.id.btn_favorite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_favrorite.isChecked()){
                    addFavorite(userData);
                }else{
                    removeFavorite(userData);
                }
            }
        });
        rootView.findViewById(R.id.btn_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.addContact(userData.getName().getFullName(), userData.getCell(), userData.getPhone(), userData.getEmail());
            }
        });

        ImageView ivProfile = rootView.findViewById(R.id.iv_profile);
        Glide.with(getContext())
                .load(userData.getPicture().getLarge())
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfile);

        return rootView;
    }

    private void addFavorite(UserE user){
        userDataViewModel.addFavorite(user);
        callback.addFavorite(index);
    }

    private void removeFavorite(UserE user){
        userDataViewModel.removeFavorite(user.getFavorite_id());
        callback.removeFavorite(index);
    }


    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.callback = (Callback)context;
        } catch (final ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnCompleteListener");
        }
    }

    public void setFavorite(int currentPosition, boolean isFavorite) {
        if(index == currentPosition && btn_favrorite != null){
            btn_favrorite.setChecked(isFavorite);
        }

    }

    public interface Callback{
        void mailToUser(String data);
        void callToUser(String data);
        void showUserLocation(UserLocationE currentPosition);
        void addContact(String name, String phone, String sec_phone, String email);

        void addFavorite(int index);
        void removeFavorite(int index);
    }
}