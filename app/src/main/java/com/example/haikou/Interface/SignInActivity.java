package com.example.haikou.Interface;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haikou.Datas.User;
import com.example.haikou.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username_signin;
    private EditText userpassword_signin;
    private Button  sgin_btn;
    private Button sgup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Bmob.initialize(this,"0673f4609a1aae936e4888ea0b19d01c");
        initView();
        setListen();
    }

    private void setListen() {
        sgup_btn.setOnClickListener(this);
        sgin_btn.setOnClickListener(this);
        sgin_btn.setOnClickListener(this);
        sgup_btn.setOnClickListener(this);
    }

    private void initView() {
        sgin_btn=(Button)findViewById(R.id.SignIn);
        sgup_btn=(Button)findViewById(R.id.SignUp);
        username_signin=(EditText)findViewById(R.id.SignIn_username);
        userpassword_signin=(EditText)findViewById(R.id.SignIn_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.SignIn:
                login();
                break;
            case  R.id.SignUp:
                Intent intent1=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent1);
        }

    }

    private void login() {
        final User user=new User();
        final  String username=username_signin.getText().toString().trim();
        final  String password=userpassword_signin.getText().toString().trim();
        user.setUsername(username);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public  void  done(User myUser, BmobException e)
            {
                if(e==null)
                {
                    User user=User.getCurrentUser(User.class);
                    Intent intent =new Intent(SignInActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
