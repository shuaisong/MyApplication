package com.example.lenovo.myapplication.request;

import com.example.lenovo.myapplication.Contant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class RetrofitUtil {
    private RetrofitUtil() {

    }

    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;

    public static Retrofit getRetrofit() {
        return Instanace.retrofit;
    }

    private static class Instanace {
        private final static Retrofit retrofit = getInstanace();

        private static Retrofit getInstanace() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
//                    .retryOnConnectionFailure(true)
                    .build();
            return new Retrofit.Builder()
                    .client(client)
                    .baseUrl(Contant.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
    }

    public static RequestApi createAPIService() {
        return getRetrofit().create(RequestApi.class);
    }
}
