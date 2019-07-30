package com.tangerine.eccore.EcShopping.Loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

public class LoaderCreator {
    //    创建存储indicator的Map集合
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    //    AVLoadingIndicatorView 是一个控件
    static AVLoadingIndicatorView creator(String type, Context context) {
//        实例化一个控件对象传入context
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
//        如果Map集合中没有对Indicator进行缓存即判定为null值，调用getIndicator进行反射，取包名，类名等进行暴力实例化得到对象
        if (LOADING_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
//           取得indicator对象存储进Map集合中
            LOADING_MAP.put(type, indicator);
        }
//        对indicator对象进行控件加载
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
//        返回控件对象
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String type) {
//        如果type是null即返回null，报错NullPointExcetipn
        if (type == null || type.isEmpty()) {
            return null;
        }
//        如果type带值，进行反射加载，包名，类名
        final StringBuilder drawableClassName = new StringBuilder();
        if (!type.contains(".")) {
            final String defaultPackAgeName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackAgeName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(type);
        try {
//            拿到类名
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
//            反射加载实例化对象
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
