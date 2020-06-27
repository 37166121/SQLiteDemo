package com.bidemo.demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bidemo.demo.R;
import com.bidemo.demo.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class SpannerAdapter extends BaseAdapter {
    private List<UserBean> userBeans = new ArrayList<>();
    private Context context;
    public SpannerAdapter(List<UserBean> userBeans,Context context){
        this.userBeans = userBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return userBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.spanner_value,null);
            viewHolder.username = convertView.findViewById(R.id.spanner_username);
            viewHolder.password = convertView.findViewById(R.id.spanner_password);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.username.setText(userBeans.get(position).getUsername());
        viewHolder.password.setText(userBeans.get(position).getPassword());
        return convertView;
    }

    private class ViewHolder{
        private TextView username;
        private TextView password;
    }

    private ViewHolder getViewHold(View view){
        if (view.getTag() == null){
            return getViewHold((View) view.getParent());
        }
        return (ViewHolder)view.getTag();
    }

}
