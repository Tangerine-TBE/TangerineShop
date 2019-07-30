package com.tangerine.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.tangerine.eccore.EcShopping.Fragment.StartFragment;
import com.tangerine.eccore.EcShopping.Net.CallBack.IError;
import com.tangerine.eccore.EcShopping.Net.CallBack.IFailure;
import com.tangerine.eccore.EcShopping.Net.CallBack.ISuccess;
import com.tangerine.eccore.EcShopping.Net.RestClient;

public class ExampleFragment extends StartFragment {
    @Override
    public Object setLayout() {
        return R.layout.fragment_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testNet();
    }
    private void testNet(){
        RestClient.build()
                .url("http//:www.baidu.com")
                .loader(getContext())
                .successs(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.i("MSG", response);
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
}
