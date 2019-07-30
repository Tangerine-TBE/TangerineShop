package com.tangerine.eccore.EcShopping.Util.diment;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.tangerine.eccore.EcShopping.StartApp.EcStart;

public class DimenUtil {
    public static int getScreenWidth(){
//        通过Context对象获取Resources对像
        final Resources resources = EcStart.getApplication().getResources();
//        通过Resources对象获取显示屏幕的指标（宽）
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
    public static int getScreenHeight(){
        final Resources resources = EcStart.getApplication().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
