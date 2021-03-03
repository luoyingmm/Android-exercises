package com.example.tongxunlu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.InputDevice;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tongxunlu.dao.UserDao;
import com.example.tongxunlu.model.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_reg,btn_forget,btn_login;
    private CheckBox cb_rem;
    private EditText et_account;
    private EditText et_password;
    private SharedPreferences sp = null;

    private UserDao userDao;

    //存储数据
//    SharedPreferences sp;

    //保存所有注册的用户
//    public static List<User> list = new ArrayList<>();

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
//        et_phone = findViewById(R.id.et_phone);

        cb_rem = findViewById(R.id.cb_rem);


        sp = getSharedPreferences("login_rem.txt", MODE_PRIVATE);
        if (sp != null){
            String account = sp.getString("account",null);
            String password = sp.getString("password",null);



            if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)){
                et_password.setText(password);
                et_account.setText(account);
                cb_rem.setChecked(true);

            }

        }
        userDao = new UserDao(this);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        System.out.println(list);
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_login:
                //获取输入框的内容
                String account = et_account.getText().toString();
                String password = et_password.getText().toString();
//                String phone = et_phone.getText().toString();
                
                //判断是否为空
                if (account.trim().length() == 0){
                    Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                if (password.trim().length() == 0){
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }



                //执行登录验证接收「进行登录操作」的返回值
//                User user = doLogin(account, password);
                User user = userDao.login(account,password);
                if (user == null){
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }else{

                    SharedPreferences.Editor edit = sp.edit();
                    //存储账号和密码
                    if (cb_rem.isChecked()) {
                        //被勾选记住密码
                        edit.putString("account", account);
                        edit.putString("password", password);

                    }else {
                        edit.clear();
                    }
                    edit.commit();
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    intent.setClass(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
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
//    //进行登录操作
//    private User doLogin(String account,String passowrd){
//        User user = new User();
//
//
//
//        for(User item : list){
//            if(item.getAccount().equals(account) && item.getPassword().equals(passowrd)){
//                return item;
//            }
//        }
//        return null;
//    }
}