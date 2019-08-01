package com.tangerine.eccore.EcShopping.wechat.WEACTIVITY;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tangerine.eccore.EcShopping.Net.CallBack.IError;
import com.tangerine.eccore.EcShopping.Net.CallBack.IFailure;
import com.tangerine.eccore.EcShopping.Net.CallBack.ISuccess;
import com.tangerine.eccore.EcShopping.Net.RestClient;
import com.tangerine.eccore.EcShopping.wechat.EcWeChat.StartWeChat;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

public abstract class BaseWXEntryActivity extends BaseWxActivity {
    protected abstract void onSignUpSuccess(String userInfo);

    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp)baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl
                .append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(StartWeChat.APP_ID)
                .append("&secret=")
                .append(StartWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        Log.i("authUrl", "onResp: " + authUrl.toString());
        getAuth(authUrl.toString());
    }
    private void getAuth(final String authUrl){
        RestClient.build()
                .url(authUrl)
                .successs(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject authObj = JSON.parseObject(response);
                        final String accessToken = authObj.getString("access_token");
                        final String openId = authObj.getString("openid");
                        final StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl.append("https://api.weixin.qq.com/sns/auth?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId);
                        Log.i("userInfoUrl", "onSuccess: " + userInfoUrl.toString());
                        getUserInfo(userInfoUrl.toString());
                    }
                })
                .build()
                .get();
    }
    private void getUserInfo(String userInfoUrl){
        RestClient
                .build()
                .url(userInfoUrl)
                .successs(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        onSignUpSuccess(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}
