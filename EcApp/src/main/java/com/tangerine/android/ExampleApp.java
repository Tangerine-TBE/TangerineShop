package com.tangerine.android;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.tangerine.eccore.EcShopping.EcStart;
import com.tangerine.ecui.Icon.FrontEcMoudle;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EcStart.init(this)
                .withApi_Host("https//:www.baidu.com")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FrontEcMoudle())
                .Configure();
    }
}
