package com.tangerine.ecui.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.tangerine.eccore.EcShopping.Fragment.StartFragment;
import com.tangerine.eccore.EcShopping.Net.CallBack.ISuccess;
import com.tangerine.eccore.EcShopping.Net.RestClient;
import com.tangerine.ecui.R;
import com.tangerine.ecui.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUp_fragment extends StartFragment {
    @BindView(R2.id.android_edit_name)
    TextInputEditText editName = null;
    @BindView(R2.id.android_edit_email)
    TextInputEditText editEmail = null;
    @BindView(R2.id.android_edit_passWord)
    TextInputEditText editPassWord = null;
    @BindView(R2.id.android_edit_rePassword)
    TextInputEditText editRePassWord = null;
    @BindView(R2.id.android_edit_phone)
    TextInputEditText editPhone = null;
    @OnClick(R2.id.sign_up_button_TextView)
    void signInLinkButtonOnClick(){
        start(new SignIn_Fragment());
    }
    @OnClick(R2.id.android_sign_up_button)
    void signUpOnclick(){
        if (checkParams()){
         RestClient.build()
                    .url("http://mock.fulingjie.com/mock-android/data/user_profile.json")
                    .params("name", editName.getText().toString())
                    .params("email", editEmail.getText().toString())
                    .params("passWord", editPassWord.getText().toString())
                    .params("phone",editPhone.getText().toString())
                    .successs(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.i("msg ", "onSuccess: " + response);
                            SignHandler.onSignUp(response,mISignListener);
                        }
                    })
                    .build()
                    .post();
        }
    }
    private ISignListener mISignListener = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }


    private boolean checkParams(){
        final String name = editName.getText().toString();
        final String email = editEmail.getText().toString();
        final String phone = editPhone.getText().toString();
        final String passWord = editPassWord.getText().toString();
        final String rePassWord = editRePassWord.getText().toString();
        boolean isPass = true;
        if (name.isEmpty()){
            editName.setError("请输入昵称");
            isPass = false;
        }else{
            editName.setError(null);
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("错误的邮箱格式");
            isPass = false;
        }else{
            editEmail.setError(null);
        }
        if (phone.isEmpty() || phone.length() !=11){
            editPhone.setError("错误的电话");
            isPass = false;
        }else{
            editPhone.setError(null);
        }
        if (passWord.isEmpty() || passWord.length() < 6){
            editPassWord.setError("错误的密码格式");
            isPass = false;
        }else{
            editPassWord.setError(null);
        }
        if ( passWord.isEmpty() ||!rePassWord.equals(passWord)){
            editRePassWord.setError("错误的重复密码");
            isPass = false;
        }else{
            editRePassWord.setError(null);
        }

        return isPass;



    }
    @Override
    public Object setLayout() {
        return R.layout.sign_up_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
