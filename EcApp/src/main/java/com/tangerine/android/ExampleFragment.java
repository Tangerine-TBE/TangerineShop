package com.tangerine.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tangerine.eccore.EcShopping.Fragment.StartFragment;

public class ExampleFragment extends StartFragment {
    @Override
    public Object setLayout() {
        return R.layout.fragment_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
