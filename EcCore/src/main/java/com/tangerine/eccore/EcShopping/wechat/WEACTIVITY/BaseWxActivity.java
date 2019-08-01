package com.tangerine.eccore.EcShopping.wechat.WEACTIVITY;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tangerine.eccore.EcShopping.wechat.EcWeChat.StartWeChat;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public  abstract class BaseWxActivity extends AppCompatActivity implements IWXAPIEventHandler {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartWeChat.getInstance().getIwxapi().handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        StartWeChat.getInstance().getIwxapi().handleIntent(getIntent(),  this);
    }
}
