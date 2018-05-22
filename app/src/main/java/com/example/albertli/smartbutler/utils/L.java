package com.example.albertli.smartbutler.utils;

/**
 * Project Name: SmartButler
 * Details: Log封装类
 * Created by albert.li on 2018/5/20.
 */

import android.util.Log;

public class L {

    //开关
    public static final  boolean DEBUG = true;
    //TAG
    public static final String TAG = "Smartbutler";

    //五个等级  DIWE

    public static void d(String text){
        if(DEBUG){
            Log.d(TAG,text);
        }
    }

    public static void i(String text){
        if(DEBUG){
            Log.i(TAG,text);
        }
    }

    public static void w(String text){
        if(DEBUG){
            Log.w(TAG,text);
        }
    }

    public static void e(String text){
        if(DEBUG){
            Log.e(TAG,text);
        }
    }
}
