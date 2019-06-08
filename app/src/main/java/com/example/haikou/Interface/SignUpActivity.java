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



public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText re_username;
    private EditText re_possword;
    private EditText re_fake;
    private EditText re_sex;
    private EditText re_intro;
    private EditText re_inter;
    private Button   re_sgin_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Bmob.initialize(this,"0673f4609a1aae936e4888ea0b19d01c");
        initView();
        setListen();
    }

    private void setListen() {
        re_sgin_btn.setOnClickListener(this);
    }

    private void initView() {
        re_username=(EditText)findViewById(R.id.re_name);
        re_possword=(EditText)findViewById(R.id.re_password);
        re_fake=(EditText)findViewById(R.id.re_akename);
        re_sex=(EditText)findViewById(R.id.re_sex);
        re_intro=(EditText)findViewById(R.id.re_intro);
        re_inter=(EditText)findViewById(R.id.re_inter);
        re_sgin_btn=(Button)findViewById(R.id.sgin_up_btn);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sgin_up_btn)
        {
            SignUp();
        }
    }

    private void SignUp() {
        final  User User=new User();
        User.setUsername(re_username.getText().toString().trim());
        User.setPassword(re_possword.getText().toString().trim());
        User.setFakename(re_fake.getText().toString().trim());
        User.setSex(re_sex.getText().toString().trim());
        User.setIntrod(re_intro.getText().toString().trim());
        User.setBirthday(re_inter.getText().toString().trim());
        User.signUp(new SaveListener<User>() {
            @Override
            public  void  done(User user, BmobException e)
            {
                if (e==null)
                {
                    Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignUpActivity.this,SignInActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
