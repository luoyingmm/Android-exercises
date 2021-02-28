package com.example.tongxunlu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tongxunlu.model.User;

import java.util.List;

import static com.example.tongxunlu.LoginActivity.list;

public class RegActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_save,btn_reset,btn_back;
    private EditText et_account,et_password,et_password_cfm,et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(this);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);
        et_password_cfm = findViewById(R.id.et_password_cfm);
        et_phone = findViewById(R.id.et_phone);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_save:
                //获取输入框的内容
                String account = et_account.getText().toString();
                String password = et_password.getText().toString();
                String password_cfm = et_password_cfm.getText().toString();
                String phone = et_phone.getText().toString();
                //判断内容是否合法
                if (account.trim().length() == 0){
                    Toast.makeText(RegActivity.this, "账号不能为空", 0).show();
                    return;//中断方法
                }
                if (password.length() == 0){
                    Toast.makeText(RegActivity.this, "密码不能为空", 0).show();
                    return;
                }
                if (!password.equals(password_cfm)){
                    Toast.makeText(RegActivity.this, "两次输入密码不一致", 0).show();
                    return;
                }
                if (phone.trim().length() == 0){
                    Toast.makeText(RegActivity.this, "手机号不能为空", 0).show();
                    return;
                }
                //判断账号和密码是否重复
                if (isExistByAccountAndPhone(account,phone)){
                    Toast.makeText(this, "账号或手机已经存在，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                 }
                //把数据打包到User对象中
                User bean = new User(account,password,phone);
                //添加到集合中
                LoginActivity.list.add(bean);
                finish();
                break;
            case R.id.btn_reset:
                //清空输入框内容
                et_account.setText("");
                et_password.setText("");
                et_password_cfm.setText("");
                et_phone.setText("");
                break;
            case R.id.btn_back:
                finish();//返回功能
                break;
        }
    }
    //根据账号和密码查询是否存在用户
    public boolean isExistByAccountAndPhone(String account,String phone){

        for(User item: LoginActivity.list){
            if (item.getAccount().equals(account) || item.getPhone().equals(phone)){
                return true;
            }
        }
        return false;
    }


}