package com.tangerine.eccore.EcShopping.Loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.tangerine.eccore.R;
import com.tangerine.eccore.EcShopping.Util.diment.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;


public class EcLoader {
    private static final int LOADER_SIZE = 10;
    private static final int LOADER_SIZE_SCALE = 8;
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    public static void showLoading(Context context,Enum<LoaderStyles> type){
        showLoading(type.name(),context);
    }

    public static void showLoading(String type, Context context) {
//        AvIndicatorView控件官方使用是加载进一个view当中,我们这里使用的是提示框加载,需要对手机屏幕大小进行二次判定,识别到最佳分辨率
//        Style是对提示框的一种优化，我们需要提示框的效果是，无标题，半透明，背景是半透明，悬浮，并且去掉边框，根据design进行判断
//        @android：windowNotittle @android:windowIsFloating @android:windowBackground(@android:color/transparent)
//        @android:windowIsTranslucent @android:windowIsFrame 在R.style资源文件下进行修改，并且在实例化AppCompatDialog对象中进行引入
        final AppCompatDialog appCompatDialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.creator(type, context);
//        setContentView（显示的控件）
        appCompatDialog.setContentView(avLoadingIndicatorView);
//        取屏幕长宽高
        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();
        final Window dialogWindow = appCompatDialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            layoutParams.width = deviceWidth / LOADER_SIZE_SCALE;
            layoutParams.height = deviceHeight / LOADER_SIZE_SCALE;
            layoutParams.height = layoutParams.height + deviceHeight / 10;
            layoutParams.gravity = Gravity.CENTER;

        }
        LOADERS.add(appCompatDialog);
        appCompatDialog.show();
    }
    public static void cancel(){
        for (AppCompatDialog dialog : LOADERS){
            if (dialog != null){
                if (dialog.isShowing()){
                    dialog.cancel();
                    dialog.dismiss();
                }
            }
        }
    }
}
