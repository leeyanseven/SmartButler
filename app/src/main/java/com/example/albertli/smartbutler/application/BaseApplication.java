package com.example.albertli.smartbutler.application;

import android.app.Application;

import com.example.albertli.smartbutler.utils.StaticClass;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by albert.li on 2018/5/20.
 */

public class BaseApplication extends Application{

    //创建
    @Override
    public void onCreate() {
        super.onCreate();

        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_ID, true);
    }
}
