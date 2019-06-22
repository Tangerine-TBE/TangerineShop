package com.tangerine.eccore.EcShopping;

import android.content.Context;

import java.util.HashMap;

public class EcStart {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigureType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }
    private static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getEcConfigs();
    }
}
