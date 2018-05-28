package com.example.albertli.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.albertli.smartbutler.MainActivity;
import com.example.albertli.smartbutler.R;
import com.example.albertli.smartbutler.entity.MyUser;
import com.example.albertli.smartbutler.utils.ShareUtils;
import com.example.albertli.smartbutler.view.CustomDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Project Name: SmartButler
 * Details: Login
 * Created by albert.li on 2018/5/26.
 */

//public class LoginActivity extends AppCompatActivity {
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;
    private EditText et_name;
    private EditText et_passwd;
    private Button btnLogin;
    private CheckBox keep_password;
    private TextView tv_forget;
    private CustomDialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    public void initView()
    {
        btnRegister = (Button) findViewById(R.id.btnRegister);
        //监听点击事件
        btnRegister.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_passwd = (EditText) findViewById(R.id.et_password);
        keep_password = (CheckBox) findViewById(R.id.keep_password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);
        dialog = new CustomDialog(this,100,100,R.layout.dialog_loading, R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        //屏幕外点击无效
        dialog.setCancelable(false);

        //设置 记住密码
        Boolean isCheck = ShareUtils.getBoolean(this,"keep_passwd",false);
        if(isCheck)
        {
            et_name.setText(ShareUtils.getString(this,"name",""));
            et_passwd.setText(ShareUtils.getString(this,"passwd",""));
        }
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.tv_forget:
                 startActivity(new Intent(this,ForgetPasswdActivity.class));
                 break;
             case R.id.btnRegister:
                 //跳转到注册页
                 startActivity(new Intent(this,RegisterActivity.class));
                 break;
             case R.id.btnLogin:
                 //1.获取输入值
                 String name = et_name.getText().toString().trim();
                 String passwd = et_passwd.getText().toString().trim();

                 //2.判断是否为空
                 if(!TextUtils.isEmpty(name) & !TextUtils.isEmpty(passwd))
                 {
                     dialog.show();
                     //登录
                     final MyUser user = new MyUser();
                     user.setUsername(name);
                     user.setPassword(passwd);

                     user.login(new SaveListener<MyUser>() {
                         @Override
                         public void done(MyUser myUser, BmobException e) {
                             dialog.dismiss();
                             if(e == null)
                             {
                                 if(user.getEmailVerified())
                                 {
                                     //跳转
                                     startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                     finish();
                                 }else{
                                     Toast.makeText(LoginActivity.this,"请前往邮箱验证",Toast.LENGTH_SHORT).show();
                                 }
                             } else {
                                 Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
                             }

                         }
                     });

                 } else {
                     Toast.makeText(this,"输入不能为空",Toast.LENGTH_SHORT).show();
                 }
                 break;

         }
    }


    //用户输入账户、密码，但不点击登陆，此时仍然执行保存密码功能
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //保存 记住密码 状态
        ShareUtils.putBoolean(this,"keep_passwd",keep_password.isChecked());

        //是否记住密码
        if(keep_password.isChecked())
        {
            ShareUtils.putString(this,"name",et_name.getText().toString().trim());
            ShareUtils.putString(this,"passwd",et_passwd.getText().toString().trim());

        }else{
            ShareUtils.deleShare(this,"name");
            ShareUtils.deleShare(this,"passwd");
        }
    }
}
