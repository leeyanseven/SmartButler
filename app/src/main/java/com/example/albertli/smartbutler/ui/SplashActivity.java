package com.example.albertli.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.albertli.smartbutler.MainActivity;
import com.example.albertli.smartbutler.R;
import com.example.albertli.smartbutler.utils.ShareUtils;
import com.example.albertli.smartbutler.utils.StaticClass;

/**
 * Project Name: SmartButler
 * Details: Splash 闪屏页
 * Created by albert.li on 2018/5/23.
 */

/**
 * 1.延时2000ms
 * 2.判断第一次
 * 3.自定义字体
 * 4.全屏主题
 * */

public class SplashActivity extends AppCompatActivity {

    private TextView tv_splash;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case StaticClass.HANDLE_SPLASH:
                    //判断程序是否第一次
                    if(isFirst())
                    {
                        //跳转到引导页
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    } else {
                        //跳转到MainActivity
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

    //初始化View
    private void initView()
    {
        tv_splash = (TextView) findViewById(R.id.tv_splash);
        //定义延迟2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLE_SPLASH,2000);
    }

    private boolean isFirst()
    {
        boolean isFirst = ShareUtils.getBoolean(this,StaticClass.IS_FIRST_RUNNING,true);
        if(isFirst)
        {
            ShareUtils.putBoolean(this,StaticClass.IS_FIRST_RUNNING,false);
            return true;
        }else
        {
            return false;
        }
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
