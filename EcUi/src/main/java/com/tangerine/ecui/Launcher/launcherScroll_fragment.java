package com.tangerine.ecui.Launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.tangerine.eccore.EcShopping.Fragment.StartFragment;
import com.tangerine.eccore.EcShopping.StartApp.AccountManager;
import com.tangerine.eccore.EcShopping.StartApp.IUserChecker;
import com.tangerine.eccore.EcShopping.Util.sharedPreferences.EcPreferences;
import com.tangerine.eccore.EcShopping.launcher.ILauncherListener;
import com.tangerine.eccore.EcShopping.launcher.LauncherHolderCreator;
import com.tangerine.eccore.EcShopping.launcher.OnLauncherFinishTag;
import com.tangerine.ecui.R;

import java.util.ArrayList;

public  class launcherScroll_fragment extends StartFragment implements ViewPager.OnPageChangeListener, IUserChecker {
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener launcherListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            launcherListener = (ILauncherListener) activity;
        }
    }
    private void initBanner(){
        INTEGERS.add(R.mipmap.beauty04);
        INTEGERS.add(R.mipmap.beauty03);
        INTEGERS.add(R.mipmap.beauty02);
        INTEGERS.add(R.mipmap.beauty01);

        mConvenientBanner
                .setPages(new LauncherHolderCreator(),INTEGERS )
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnPageChangeListener(this)
                .setCanLoop(false);

    }
    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        if (i == 3){
            EcPreferences.setAppFlag(LaucherScrollTag.HAS_FRIST_LOG.name(), true);
            AccountManager.checkAccount(this);
        }
    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

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
}
