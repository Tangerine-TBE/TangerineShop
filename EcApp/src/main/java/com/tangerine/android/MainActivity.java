package com.tangerine.android;


import com.tangerine.eccore.EcShopping.Activity.ProxyActivity;
import com.tangerine.eccore.EcShopping.Fragment.StartFragment;

public class MainActivity extends ProxyActivity {


    @Override
    public StartFragment setRootFragment() {
        return new ExampleFragment();
    }
}
