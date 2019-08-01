package com.tangerine.android;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.tangerine.eccore.EcShopping.StartApp.EcStart;
import com.tangerine.ecui.Icon.FrontEcMoudle;

import org.litepal.LitePal;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EcStart.init(this)
                .withApi_Host("http://mock.fulingjie.com/mock-android/data/user_profile.json/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FrontEcMoudle())
                .withAppId("Xxxx")
                .withAppSecret("xxxx")
                .Configure();
        LitePal.initialize(this);
        initStetho();
    }

    private void initStetho(){
        Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build());
    }

}
