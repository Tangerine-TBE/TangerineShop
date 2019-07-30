package com.tangerine.eccore.EcShopping.StartApp;

import android.content.Context;

import java.util.HashMap;

public class EcStart {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigureType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }
     public static  HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getEcConfigs();
    }
    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigureType.APPLICATION_CONTEXT.name());
    }
}
