package com.tangerine.ecui.Launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.tangerine.eccore.EcShopping.Fragment.StartFragment;
import com.tangerine.eccore.EcShopping.StartApp.AccountManager;
import com.tangerine.eccore.EcShopping.StartApp.IUserChecker;
import com.tangerine.eccore.EcShopping.launcher.ILauncherListener;
import com.tangerine.eccore.EcShopping.launcher.OnLauncherFinishTag;
import com.tangerine.eccore.EcShopping.Util.sharedPreferences.EcPreferences;
import com.tangerine.eccore.EcShopping.Util.timer.BaseTimerTask;
import com.tangerine.eccore.EcShopping.Util.timer.ITimerListener;
import com.tangerine.ecui.R;
import com.tangerine.ecui.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


public class launcher_fragment extends StartFragment implements ITimerListener {
    private int account = 5;
    private Timer mTimer = null;
    private ILauncherListener launcherListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            launcherListener = (ILauncherListener) activity;
        }
    }

    @BindView(R2.id.tv_launcherTimer)
    AppCompatTextView mTvTimer = null;
    @OnClick(R2.id.tv_launcherTimer)
    void onClickTimer(){
        if (mTvTimer != null){
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.launcher_fragment;

    }
    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask,0,1000);

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void checkIsShowScroll(){
        if (!EcPreferences.getAppFlag(LaucherScrollTag.HAS_FRIST_LOG.name())){
            startWithPop(new launcherScroll_fragment());
        }else{
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (launcherListener != null){
                        launcherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (launcherListener != null){
                        launcherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",account));
                    account--;
                    if (account < 0){
                        if (mTvTimer != null){
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
