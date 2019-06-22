package com.tangerine.eccore.EcShopping.StartApp;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

public class Configurator {
    private static final ArrayList<IconFontDescriptor> DESCRIPTORS = new ArrayList<>();
    private static final HashMap<String,Object> EC_CONFIGS = new HashMap<>();
    private Configurator(){
        EC_CONFIGS.put(ConfigureType.CONFIGURE_READY.name(),false);
    }
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }
     static  Configurator getInstance(){
        return Holder.INSTANCE;
    }
    public final void Configure(){
        initIcons();
        EC_CONFIGS.put(ConfigureType.CONFIGURE_READY.name(),true);
    }
    final HashMap<String ,Object> getEcConfigs(){
        return EC_CONFIGS;
    }
    public final Configurator withApi_Host(String host){
        EC_CONFIGS.put(ConfigureType.API_HOST.name(),host);
        return this;
    }
    public final Configurator withIcon(IconFontDescriptor descriptor){

        DESCRIPTORS.add(descriptor);
        return this;

    }
    private void initIcons(){
        if(DESCRIPTORS.size() > 0){
            final Iconify.IconifyInitializer initializer =Iconify.with(DESCRIPTORS.get(0));
            for (int i = 1 ; i < DESCRIPTORS.size(); i ++){
                initializer.with(DESCRIPTORS.get(i));
            }
        }
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) EC_CONFIGS.get(ConfigureType.CONFIGURE_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,please check the configurator");
        }
    }
    @SuppressWarnings("unchecked")
    final <T>T getConfiguration(Enum<ConfigureType> key){
        checkConfiguration();
        return (T) EC_CONFIGS.get(key.name());
    }
}
