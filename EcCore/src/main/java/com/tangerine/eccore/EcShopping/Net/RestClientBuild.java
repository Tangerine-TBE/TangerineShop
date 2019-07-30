package com.tangerine.eccore.EcShopping.Net;

import android.content.Context;

import com.tangerine.eccore.EcShopping.Net.CallBack.IError;
import com.tangerine.eccore.EcShopping.Net.CallBack.IFailure;
import com.tangerine.eccore.EcShopping.Net.CallBack.IRequest;
import com.tangerine.eccore.EcShopping.Net.CallBack.ISuccess;
import com.tangerine.eccore.EcShopping.Loader.LoaderStyles;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuild {
    private  String mUrl = null;
    private static final Map<String,Object> PARAMS = RestCreator.getParams();
    private IRequest mRequest = null;
    private ISuccess mSuccess= null;
    private IFailure mFailure= null;
    private IError mError= null;
    private RequestBody mBody= null;
    private LoaderStyles mLoaderStyles= null;
    private Context mContext= null;

    public final RestClientBuild url (String url){
        this.mUrl = url;
        return this;
    }
    public final RestClientBuild params(Map<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }
    public final RestClientBuild params(String key , Object value){
        PARAMS.put(key, value);
        return this;
    }
    public final RestClientBuild raw (String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json,charset=UTF-8"),raw);
        return this;
    }
    public final RestClientBuild successs(ISuccess iSuccess){
        this.mSuccess = iSuccess;
        return this;
    }
    public final RestClientBuild requset(IRequest iRequest){
        this.mRequest = iRequest;
        return this;
    }
    public final RestClientBuild failure(IFailure iFailure){
        this.mFailure = iFailure;
        return this;
    }
    public final RestClientBuild error(IError iError){
        this.mError = iError;
        return this;
    }
    public final RestClientBuild loader(Context context ,LoaderStyles styles){
        this.mContext = context;
        this.mLoaderStyles = styles;
        return this;
    }
    public final RestClientBuild loader(Context context){
        this.mContext = context;
        this.mLoaderStyles = LoaderStyles.BallClipRotatePulseIndicator;
        return this;
    }


    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mRequest,mSuccess,mFailure,mError,mBody,mLoaderStyles, mContext);
    }
}
