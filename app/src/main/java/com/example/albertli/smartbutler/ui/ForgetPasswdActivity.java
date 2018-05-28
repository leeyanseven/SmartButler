package com.example.albertli.smartbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.albertli.smartbutler.R;
import com.example.albertli.smartbutler.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Project Name: SmartButler
 * Details: TODO
 * Created by albert.li on 2018/5/27.
 */

public class ForgetPasswdActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_forget_password;
    private EditText et_email;
    private EditText et_now;
    private EditText et_new;
    private EditText et_new_passwd;
    private Button btn_update_password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        //initView();
    }
    public void initView()
    {
        btn_forget_password = (Button) findViewById(R.id.btn_forget_password);
        et_email = (EditText) findViewById(R.id.et_email);
        et_now = (EditText) findViewById(R.id.et_now);
        et_new = (EditText) findViewById(R.id.et_new);
        et_new_passwd = (EditText) findViewById(R.id.et_new_passwd);
        btn_update_password = (Button) findViewById(R.id.btn_update_password);

        btn_forget_password.setOnClickListener(this);
        btn_update_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update_password:
                //1.获取当前密码、新密码、新密码确认
                String pd_now = et_now.getText().toString().trim();
                String pd_new = et_new.getText().toString().trim();
                String pd_new_again = et_new_passwd.getText().toString().trim();

                //2.判断是否为空
                if(!TextUtils.isEmpty(pd_now) & !TextUtils.isEmpty(pd_new)
                        & !TextUtils.isEmpty(pd_new_again))
                {
                    //3.判断两次密码是否一致
                    if(pd_new.equals(pd_new_again))
                    {
                        //4.更新密码
                        MyUser.updateCurrentUserPassword(pd_now, pd_new, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e == null)
                                {
                                    Toast.makeText(ForgetPasswdActivity.this,"update passwd success",
                                            Toast.LENGTH_SHORT).show();
                                    //结束当前页面
                                    finish();

                                } else {
                                    Toast.makeText(ForgetPasswdActivity.this,"update passwd fail",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    } else {
                        Toast.makeText(this,"two passwd input not same.",Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this,"input can not be null",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_forget_password:
                //1.获取登陆邮箱
                final String email = et_email.getText().toString().trim();
                //2.判断邮箱是否为空
                if(!TextUtils.isEmpty(email))
                {
                    //3.邮箱重置密码
                    MyUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e == null)
                            {
                                Toast.makeText(ForgetPasswdActivity.this,"send email success,"+email,
                                        Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(ForgetPasswdActivity.this,"send email fail,"+e,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(this,"mail address can not be null",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
