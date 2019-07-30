package com.tangerine.eccore.EcShopping.Util.sharedPreferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.tangerine.eccore.EcShopping.StartApp.EcStart;

public class EcPreferences  {
    private static final SharedPreferences SHARED_PREFERENCES = PreferenceManager.getDefaultSharedPreferences(EcStart.getApplication());
    private static final String APP_PREFERENCES_KEY = "profile";

    private static SharedPreferences getSharedPreferences(){
        return SHARED_PREFERENCES;
    }
    public static void setAppProfile(String value){
        getSharedPreferences()
                .edit()
                .putString(APP_PREFERENCES_KEY,value)
                .apply();
    }
    public static String getAppProfile(){
        return getSharedPreferences().getString(APP_PREFERENCES_KEY,null);
    }
    public static void setAppFlag(String key,boolean flag){
        getSharedPreferences().edit()
                .putBoolean(key, flag)
                .apply();

    }
    public static boolean getAppFlag(String key){
        return getSharedPreferences().getBoolean(key, false);
    }
}
