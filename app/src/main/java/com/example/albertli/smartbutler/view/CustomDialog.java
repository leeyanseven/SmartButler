package com.example.albertli.smartbutler.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.albertli.smartbutler.R;

/**
 * Project Name: SmartButler
 * Details: self dialog
 * Created by albert.li on 2018/5/27.
 */

public class CustomDialog extends Dialog {

    public CustomDialog(Context context)
    {
        super(context);
    }

    //自定义构造方法
    public CustomDialog(@NonNull Context context, int layout, int style) {
        this(context, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT
                , layout, style, Gravity.CENTER);
    }

    //定义属性： gravity 方向，anim 动画
    public CustomDialog(Context context, int width, int height, int layout, int style, int gravity,
                        int anim)
    {
        super(context, style);
        //设置属性
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);
        window.setWindowAnimations(anim);
    }

    public CustomDialog(Context context, int width, int height, int layout, int style, int gravity)
    {
        this(context, width, height,layout,style,gravity, R.style.pop_anim_style);
    }

}
