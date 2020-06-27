package com.bidemo.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bidemo.demo.R;
import com.bidemo.demo.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter {
    private List<UserBean> userBeans = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public UserListAdapter(List<UserBean> userBeans,Context context){
        this.userBeans = userBeans;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(inflater.inflate(R.layout.main_rv,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserViewHolder userViewHolder = (UserViewHolder) holder;
        userViewHolder.setData(position == 0 ? null : userBeans.get(position - 1));
    }

    @Override
    public int getItemCount() {
        return 1 + userBeans.size();
    }

    private class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView username;
        private TextView password;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username_tv);
            password = itemView.findViewById(R.id.password_tv);
        }

        void setData(UserBean userBean ){
            username.setText(userBean == null ? "用户名" : userBean.getUsername());
            password.setText(userBean == null ? "密码" : userBean.getPassword());
        }
    }
}
