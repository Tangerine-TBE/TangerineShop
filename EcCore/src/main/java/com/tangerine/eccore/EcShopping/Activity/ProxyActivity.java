package com.tangerine.eccore.EcShopping.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.tangerine.eccore.EcShopping.Fragment.StartFragment;
import com.tangerine.eccore.R;

import me.yokeyword.fragmentation.SupportActivity;
@SuppressLint("RestrictedApi")
public abstract class ProxyActivity extends SupportActivity {
    public abstract StartFragment setRootFragment();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }
    private void initContainer(@Nullable Bundle savedInstanceState){
         final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.fragment_container);
        setContentView(container);
        if (savedInstanceState == null){
            loadRootFragment(R.id.fragment_container,setRootFragment());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
