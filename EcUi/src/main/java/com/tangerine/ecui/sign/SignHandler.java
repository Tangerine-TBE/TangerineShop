package com.tangerine.ecui.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tangerine.eccore.EcShopping.StartApp.AccountManager;
import com.tangerine.ecui.database.DataBaseManger;
import com.tangerine.ecui.database.user_profile;

public class SignHandler {
    public static void onSignUp(String response,ISignListener iSignListener){
        DataBaseManger.getDataBase();
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final int userId = profileJson.getInteger("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final user_profile data = new user_profile(name, avatar, gender, address, userId);
       data.save();
        AccountManager.setSignState(true);
        iSignListener.onSignUpSuccess();
    }

    public static void onSignIn(String response, ISignListener mISignListener) {
        DataBaseManger.getDataBase();
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final int userId = profileJson.getInteger("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final user_profile data = new user_profile(name, avatar, gender, address, userId);
        data.save();
        AccountManager.setSignState(true);
        mISignListener.onSignInSuccess();
    }
}
