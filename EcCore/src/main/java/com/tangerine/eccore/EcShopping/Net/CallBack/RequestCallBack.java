package com.tangerine.eccore.EcShopping.Net.CallBack;

import android.os.Handler;

import com.tangerine.eccore.EcShopping.Loader.EcLoader;
import com.tangerine.eccore.EcShopping.Loader.LoaderStyles;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallBack implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyles LOADERSTYLES;
    private static final Handler HANDLER = new Handler();



    public RequestCallBack(IRequest request, ISuccess success, IFailure failure, IError error,LoaderStyles loaderStyles) {
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        LOADERSTYLES = loaderStyles;

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if (ERROR != null){
                ERROR.onError(response.code(),response.message());
            }
        }
        stopLoading();
    }

    private void stopLoading(){
        if (LOADERSTYLES != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    EcLoader.cancel();
                }
            }, 1);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null){
            FAILURE.onFailure();
        }
        if (REQUEST !=null){
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }
}
