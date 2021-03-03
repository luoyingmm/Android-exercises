package com.example.tongxunlu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tongxunlu.model.User;

import static com.example.tongxunlu.LoginActivity.list;

public class ForgetActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_back;
    private Button btn_query;
    private EditText et_account;
    private EditText et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        btn_query = findViewById(R.id.btn_query);
        btn_query.setOnClickListener(this);

        et_account = findViewById(R.id.et_account);
        et_phone = findViewById(R.id.et_phone);
    }

    @Override
    public void onClick(View v) {
        String account = et_account.getText().toString();
        String phone = et_phone.getText().toString();
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_query:
                //检查用户输入是否为空
                if (account.trim().length() == 0){
                    Toast.makeText(this, "账号不能为空", 0).show();
                    return;
                }
                if (phone.trim().length() == 0){
                    Toast.makeText(this, "手机号不能为空", 0).show();
                    return;
                }
                //对输入的号码调用query方法进行验证
                User user = query(account, phone);
                if (user == null){
                    Toast.makeText(this, "你输入的账号和手机号不匹配", 0).show();
                }else {
//                    Toast.makeText(this, "你的密码是" + user.getPassword(), Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgetActivity.this);
                    builder.setTitle("系统提示").setMessage("你的密码是『" + user.getPassword() + "』").setPositiveButton("好的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }


                break;
        }
    }
    //判断输入的账号和手机号是否匹配
    public User query(String account,String phone){
        for(User item : list){
            if (item.getAccount().equals(account) && item.getPhone().equals(phone)){
                return item;
            }
        }
        return null;
    }
}