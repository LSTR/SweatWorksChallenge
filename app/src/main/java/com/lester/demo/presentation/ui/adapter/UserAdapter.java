package com.lester.demo.presentation.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lester.demo.R;
import com.lester.demo.data.model.UserE;
import com.lester.demo.data.model.UserLocationE;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<BaseViewHolder>  {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;

    private final Callback callback;
    private final List<UserE> mValues;

    public UserAdapter(List<UserE> items, Callback callback) {
        this.callback = callback;
        this.mValues = items;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_content, parent, false));
            case VIEW_TYPE_LOADING:
                return new LoadingHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.view_loading, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == mValues.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addLoading() {
        isLoaderVisible = true;
        mValues.add(new UserE());
        notifyItemInserted(mValues.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = mValues.size() - 1;
        UserE item = mValues.get(position);
        if (item != null) {
            mValues.remove(position);
            notifyItemRemoved(position);
        }
    }

    class ViewHolder extends BaseViewHolder implements View.OnClickListener {
        final TextView mName;
        final TextView mIdName;
        final TextView mIdVal;
        final TextView mAge;
        final TextView mAddress;
        final ImageView mProfile;
        final TextView mPhone;
        final TextView mEmail;
        final TextView mLocation;
        final ToggleButton btnFavorite;

        ViewHolder(View view) {
            super(view);
            mName = view.findViewById(R.id.txt_name);
            mIdName = view.findViewById(R.id.txt_id_name);
            mIdVal = view.findViewById(R.id.txt_id_val);
            mAge = view.findViewById(R.id.txt_age);
            mAddress = view.findViewById(R.id.txt_address);
            mProfile = view.findViewById(R.id.iv_profile);
            mPhone = view.findViewById(R.id.txt_phone);
            mEmail = view.findViewById(R.id.txt_email);
            mLocation = view.findViewById(R.id.txt_location);
            btnFavorite = view.findViewById(R.id.btn_favorite);

            itemView.setOnClickListener(this);
            view.findViewById(R.id.btn_call).setOnClickListener(this);
            view.findViewById(R.id.btn_mail).setOnClickListener(this);
            view.findViewById(R.id.btn_location).setOnClickListener(this);
            view.findViewById(R.id.btn_favorite).setOnClickListener(this);
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            UserE obj = mValues.get(position);
            mName.setText(obj.getName().getFullName());
            mIdName.setText(obj.getId().getName());
            mIdVal.setText(obj.getId().getValue());
            mAge.setText(obj.getDob().getFullAge());
            mAddress.setText(obj.getLocation().getAddress());
            mPhone.setText(obj.getCell());
            mEmail.setText(obj.getEmail());
            mLocation.setText(obj.getLocation().getTimezone().getTz());
            btnFavorite.setChecked(obj.isFavorite());

            Glide.with(itemView.getContext())
                    .load(obj.getPicture().getThumbnail())
                    .apply(RequestOptions.circleCropTransform())
                    .into(mProfile);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_call: callback.callToUser(mValues.get(getCurrentPosition()).getCell()); break;
                case R.id.btn_mail: callback.mailToUser(mValues.get(getCurrentPosition()).getEmail()); break;
                case R.id.btn_location: callback.showUserLocation(mValues.get(getCurrentPosition()).getLocation()); break;
                case R.id.btn_favorite: if(btnFavorite.isChecked()) callback.addFavorite(getCurrentPosition()); else callback.removeFavorite(getCurrentPosition()); break;
                default: callback.onItemClick(getCurrentPosition()); break;
            }
        }
    }

    public class LoadingHolder extends BaseViewHolder {
        LoadingHolder(View itemView) {
            super(itemView);
        }
    }

    public interface Callback{
        void onItemClick(int position);
        void mailToUser(String data);
        void callToUser(String data);
        void showUserLocation(UserLocationE currentPosition);
        void addFavorite(int currentPosition);
        void removeFavorite(int currentPosition);
    }
}