package com.tangerine.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tangerine.eccore.EcShopping.EcStart;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EcStart.init(this)
                .Configure();
    }
}
