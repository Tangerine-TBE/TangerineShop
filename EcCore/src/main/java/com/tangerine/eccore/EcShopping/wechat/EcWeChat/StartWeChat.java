package com.tangerine.eccore.EcShopping.wechat.EcWeChat;

import android.app.Activity;

import com.tangerine.eccore.EcShopping.StartApp.ConfigureType;
import com.tangerine.eccore.EcShopping.StartApp.EcStart;
import com.tangerine.eccore.EcShopping.wechat.CallBacks.IWXSignInCallBack;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class StartWeChat {
    public static final String APP_ID = (String) EcStart.getConfigurations().get(ConfigureType.WE_CHAT_APP_ID.name());
    public static final String APP_SECRET = (String) EcStart.getConfigurations().get(ConfigureType.WE_CHAT_APP_SECRET.name());
    private final IWXAPI iwxapi;
    private IWXSignInCallBack iwxSignInCallBack = null;


    private static final class Holder{
        private static final StartWeChat INSTANCE = new StartWeChat();
    }

    public static StartWeChat getInstance(){
        return Holder.INSTANCE;
    }
    private StartWeChat(){
        final Activity activity = (Activity) EcStart.getConfigurations().get(ConfigureType.ACTIVITY.name());
        iwxapi = WXAPIFactory.createWXAPI(activity, APP_ID,true);
        iwxapi.registerApp(APP_ID);
    }
    public IWXSignInCallBack getIwxSignInCallBack(){
            return iwxSignInCallBack;
    }
    public StartWeChat onSignInSuccess(IWXSignInCallBack callBack){
         iwxSignInCallBack = callBack;
         return this;
    }

    public final IWXAPI getIwxapi(){
        return iwxapi;
    }
    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        iwxapi.sendReq(req);
    }
}
