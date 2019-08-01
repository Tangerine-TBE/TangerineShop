package com.tangerine.eccore.EcShopping.wechat.templates;

import com.tangerine.eccore.EcShopping.wechat.EcWeChat.StartWeChat;
import com.tangerine.eccore.EcShopping.wechat.WEACTIVITY.BaseWXEntryActivity;

public class WXEntryTemplate extends BaseWXEntryActivity {
    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0 );
    }

    @Override
    protected void onSignUpSuccess(String userInfo) {
        StartWeChat.getInstance().getIwxSignInCallBack().onSignInSuccess(userInfo);
    }

}
