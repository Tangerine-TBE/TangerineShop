package com.tangerine.eccore.EcShopping.Net;

import android.content.Context;

import com.tangerine.eccore.EcShopping.Net.CallBack.IError;
import com.tangerine.eccore.EcShopping.Net.CallBack.IFailure;
import com.tangerine.eccore.EcShopping.Net.CallBack.IRequest;
import com.tangerine.eccore.EcShopping.Net.CallBack.ISuccess;
import com.tangerine.eccore.EcShopping.Net.CallBack.RequestCallBack;
import com.tangerine.eccore.EcShopping.Loader.EcLoader;
import com.tangerine.eccore.EcShopping.Loader.LoaderStyles;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RestClient {
    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyles LOADERSTYLE;
    private final Context CONTEXT;


    public RestClient(String url, Map<String, Object> params, IRequest request, ISuccess success, IFailure failure, IError error, RequestBody body,LoaderStyles loaderStyles,Context context) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.LOADERSTYLE = loaderStyles;
        this.CONTEXT = context;

    }
    public static RestClientBuild build (){
        return new RestClientBuild();
    }
    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOADERSTYLE != null){
            EcLoader.showLoading(CONTEXT, LOADERSTYLE);
        }
        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
                default:
                    break;
        }
        if (call != null){
            call.enqueue(getRequestCallBack());
        }
    }
    private Callback<String> getRequestCallBack(){
        return new RequestCallBack(REQUEST,SUCCESS,FAILURE,ERROR,LOADERSTYLE);
    }
    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
