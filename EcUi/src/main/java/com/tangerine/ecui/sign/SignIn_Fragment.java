package com.tangerine.ecui.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.tangerine.eccore.EcShopping.Fragment.StartFragment;
import com.tangerine.eccore.EcShopping.Net.CallBack.ISuccess;
import com.tangerine.eccore.EcShopping.Net.RestClient;
import com.tangerine.ecui.R;
import com.tangerine.ecui.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class SignIn_Fragment extends StartFragment {
    @BindView(R2.id.android_sign_in_edit_email)
    TextInputEditText signInEmail = null;

    @BindView(R2.id.android_sign_in_edit_passWord)
    TextInputEditText signInPassWord = null;

    @OnClick(R2.id.android_sign_in_button)
    void signInButtonOnClick(){
        if (checkParams()) {
            RestClient.build()
                    .url("http://mock.fulingjie.com/mock-android/data/user_profile.json")
                    .params("email", signInEmail.getText().toString())
                    .params("passWord", signInPassWord.getText().toString())
                    .successs(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.i("msg ", "onSuccess: " + response);
                            SignHandler.onSignIn(response,mISignListener);
                        }
                    })
                    .build()
                    .post();
        }else{
            Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.android_sign_in_button_TextView)
    void signInButtonEditTextOnClick(){
        start(new SignUp_fragment());
    }

    @OnClick(R2.id.icon_sign_in_weChat)
    void signInButtonWeChat(){
        Toast.makeText(getContext(), "没有任何微信接口", Toast.LENGTH_SHORT).show();
    }

    private boolean checkParams(){
        final String email = signInEmail.getText().toString();
        final String passWord = signInPassWord.getText().toString();
        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signInEmail.setError("错误的邮箱格式");
            isPass = false;
        }else{
            signInEmail.setError(null);
        }

        if (passWord.isEmpty() || passWord.length() < 6){
            signInPassWord.setError("错误的密码格式");
            isPass = false;
        }else{
            signInPassWord.setError(null);
        }
        return isPass;
    }

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.sign_in_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
