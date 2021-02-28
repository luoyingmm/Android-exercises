package com.example.tongxunlu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tongxunlu.model.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_reg,btn_forget,btn_login;
    private EditText et_account;
    private EditText et_password;

    //保存所有注册的用户
    public static List<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        btn_reg = findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(this);

        btn_forget = findViewById(R.id.btn_forget);
        btn_forget.setOnClickListener(this);

        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);




    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println(list);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_login:
                //获取输入框的内容
                String account = et_account.getText().toString();
                String password = et_password.getText().toString();
                
                //判断是否为空
                if (account.trim().length() == 0){
                    Toast.makeText(LoginActivity.this, "账号不能为空", 0).show();
                    return;
                }
                
                if (password.trim().length() == 0){
                    Toast.makeText(LoginActivity.this, "密码不能为空", 0).show();
                    return;
                }
                //执行登录验证接收「进行登录操作」的返回值
                User user = doLogin(account, password);
                if (user == null){
                    Toast.makeText(LoginActivity.this, "账号或密码错误", 0).show();
                }else{
                    Toast.makeText(LoginActivity.this, "登陆成功", 0).show();
                }
                break;
            case R.id.btn_reg:
                //跳转到RegActivity进行注册
                intent.setClass(LoginActivity.this,RegActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_forget:
                intent.setClass(LoginActivity.this,ForgetActivity.class);
                startActivity(intent);
                break;
        }

    }
    //进行登录操作
    private User doLogin(String account,String passowrd){
        for(User item : list){
            if(item.getAccount().equals(account) && item.getPassword().equals(passowrd)){
                return item;
            }
        }
        return null;
    }
}