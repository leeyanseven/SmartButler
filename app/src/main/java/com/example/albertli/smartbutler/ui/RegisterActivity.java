package com.example.albertli.smartbutler.ui;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.albertli.smartbutler.R;
/*import com.example.albertli.smartbutler.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;*/

/**
 * Project Name: SmartButler
 * Details: TODO
 * Created by albert.li on 2018/5/26.
 */

public class RegisterActivity extends BaseActivity {
//public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private EditText et_user;
    private EditText et_age;
    private EditText et_desc;
    private RadioGroup mRadioGroup;
    private EditText et_pass;
    private EditText et_passwd;
    private EditText et_mail;
    private Button btnRegistered;
    private Boolean isGender = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initView();
    }

    /*private void initView()
    {
        et_user = (EditText) findViewById(R.id.et_user);
        et_age = (EditText) findViewById(R.id.et_age);
        et_desc = (EditText) findViewById(R.id.et_desc);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_passwd = (EditText) findViewById(R.id.et_passwd);
        et_mail = (EditText) findViewById(R.id.et_mail);
        btnRegistered = (Button) findViewById(R.id.btnRegistered);
        btnRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                String name = et_user.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                String passwd = et_passwd.getText().toString().trim();
                String email = et_mail.getText().toString().trim();

                if(!(TextUtils.isEmpty(name)) & !(TextUtils.isEmpty(age)) & !(TextUtils.isEmpty(pass))
                       & !(TextUtils.isEmpty(passwd)) & !(TextUtils.isEmpty(email)) )
                {
                    if(passwd.equals(pass))
                    {
                        //判断性别
                        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                                if(checkedId == R.id.rb_boy)
                                {
                                    isGender = true;
                                } else if(checkedId == R.id.rb_girl) {
                                    isGender = false;
                                }
                            }
                        });
                        //判断简介是否为空
                        if(TextUtils.isEmpty(desc))
                        {
                            desc = "这个人很懒，什么也没留下";
                        }
                        MyUser user = new MyUser();
                        user.setUsername(name);
                        user.setDesc(desc);
                        user.setPassword(pass);
                        user.setEmail(email);
                        user.setAge(Integer.parseInt(age));
                        user.setSex(isGender);

                        user.signUp(new SaveListener<MyUser>() {
                            @Override
                            public void done(MyUser myUser, BmobException e) {
                                if(e==null){
                                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(RegisterActivity.this,"注册失败"+ e.toString(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else{
                        Toast.makeText(this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this,"输入不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }*/
}
