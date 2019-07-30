package com.tangerine.eccore.EcShopping.StartApp;

import com.tangerine.eccore.EcShopping.Util.sharedPreferences.EcPreferences;

public class AccountManager {
    private enum  SignTag{
        SIGN_TAG
    }
    public static void setSignState(boolean state){
        EcPreferences.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }
    private static boolean isSignIn(){
        return EcPreferences.getAppFlag(SignTag.SIGN_TAG.name());
    }
    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else{
            checker.onNotSignIn();
        }
    }
}
