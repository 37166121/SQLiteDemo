package com.bidemo.demo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bidemo.demo.R;
import com.bidemo.demo.adapter.SpannerAdapter;
import com.bidemo.demo.adapter.UserListAdapter;
import com.bidemo.demo.bean.UserBean;
import com.bidemo.demo.dao.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button add;
    private Button delete;
    private Button change;
    private Button check;
    private EditText username_et;
    private EditText password_et;
    private Spinner spinner;
    private String username;
    private String password;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<UserBean> userBeans;
    private UserBean userBean;
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView(){
        add = findViewById(R.id.adduser);
        delete = findViewById(R.id.deleteuser);
        change = findViewById(R.id.changeuser);
        check = findViewById(R.id.checkuser);
        username_et = findViewById(R.id.username_et);
        password_et = findViewById(R.id.password_et);
        recyclerView = findViewById(R.id.main_rv);
        spinner = findViewById(R.id.spanner);
    }

    private void initData(){
        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        change.setOnClickListener(this);
        check.setOnClickListener(this);
        linearLayoutManager = new LinearLayoutManager(this);
        userBeans = new ArrayList<>();
        userDao = new UserDao(this);
        userBean = new UserBean();
    }

    private void insert(){
        if ("".equals(username) || "".equals(password)){
            Toast.makeText(this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
        } else {
            //添加
            userDao.insert(userBean);
        }
    }

    private void delete(){
        if ("".equals(username)){
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
        } else {
            //删除
            userDao.delete(userBean);
        }
    }

    private void query(){
        //查询
        userBeans = userDao.query();
        UserListAdapter userListAdapter = new UserListAdapter(userBeans,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userListAdapter);
        SpannerAdapter spannerAdapter = new SpannerAdapter(userBeans,this);
        spinner.setAdapter(spannerAdapter);
    }

    private void update(){
        if ("".equals(username) || "".equals(password)){
            Toast.makeText(this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
        } else {
            //更改
            userDao.update(userBean);
        }
    }

    @Override
    public void onClick(View v) {
        username = username_et.getText().toString();
        password = password_et.getText().toString();
        userBean.setUsername(username);
        userBean.setPassword(password);
        userBeans.clear();
        switch (v.getId()) {
            case R.id.adduser:
                insert();
                break;
            case R.id.deleteuser:
                delete();
                break;
            case R.id.changeuser:
                query();
                return;
            case R.id.checkuser:
                update();
                break;
            default:break;
        }
        query();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.spanner:
                Toast.makeText(this,userBeans.get(position).getUsername() + "的密码：" + userBeans.get(position).getPassword(),Toast.LENGTH_SHORT).show();
                break;
            default:break;
        }
    }
}