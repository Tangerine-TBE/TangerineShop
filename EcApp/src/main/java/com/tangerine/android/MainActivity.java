package com.tangerine.android;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.tangerine.eccore.EcShopping.Activity.ProxyActivity;
import com.tangerine.eccore.EcShopping.Fragment.StartFragment;
import com.tangerine.eccore.EcShopping.launcher.ILauncherListener;
import com.tangerine.eccore.EcShopping.launcher.OnLauncherFinishTag;
import com.tangerine.ecui.Launcher.launcher_fragment;
import com.tangerine.ecui.sign.ISignListener;
import com.tangerine.ecui.sign.SignIn_Fragment;

public class MainActivity extends ProxyActivity implements ISignListener, ILauncherListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public StartFragment setRootFragment() {
       return new launcher_fragment();
    }

    @Override
    public void onSignInSuccess() {
        startWithPop(new ExampleFragment());
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                Toast.makeText(this, "启动结束，用户已经登录了", Toast.LENGTH_SHORT).show();
                startWithPop(new ExampleFragment());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没有过登录", Toast.LENGTH_SHORT).show();
                startWithPop(new SignIn_Fragment());
                break;
                default:
                    break;
        }
    }
}
