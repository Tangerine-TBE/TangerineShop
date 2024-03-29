package com.tangerine.eccore.EcShopping.Net;

import com.tangerine.eccore.EcShopping.StartApp.ConfigureType;
import com.tangerine.eccore.EcShopping.StartApp.EcStart;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCreator {
    private static final class ParamsHolder{
        public static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }
    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }
    public static RestService getRestService(){
        return RetrofitServiceHolder.REST_SERVICE;
    }
    private static final class RetrofitHolder{
        private static final String BASE_URl = (String) EcStart.getConfigurations().get(ConfigureType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URl)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
    private static final class OkHttpHolder{
        private static final int TIME_OUT = 80;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    private static final class RetrofitServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

}
