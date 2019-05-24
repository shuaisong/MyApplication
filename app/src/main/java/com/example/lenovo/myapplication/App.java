package com.example.lenovo.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.StrictMode;
import android.util.Log;

import com.example.lenovo.myapplication.component.AppComponent;
import com.example.lenovo.myapplication.component.DaggerAppComponent;
import com.example.lenovo.myapplication.module.AppModule;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.vise.xsnow.common.ViseConfig;
import com.vise.xsnow.http.ViseHttp;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

//import com.example.lenovo.myapplication.request.HttpLoggingInterceptor;

/**
 * Created by lenovo on 2018/4/13.
 * auther:lenovo
 * Date：2018/4/13
 */

public class App extends Application {
    private static App mApp;
    private RefWatcher refWatcher;
    private AppComponent appComponent;

    private boolean isDebug() {
        ApplicationInfo mInfo = getApplicationInfo();
        return (mInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: " + isDebug());
        /*if (isDebug()) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }*/

        mApp = this;
       // refWatcher = stepLeakCanary();
        initCompoent();
        initXSnow();
        initOKGO();
    }

    private void initOKGO() {
        HttpHeaders mHttpHeaders = new HttpHeaders();
        LinkedHashMap<String, String> headers = new LinkedHashMap<>();
        headers.put("appkey", "06E0C9C16A169AEC");
        headers.put("api_version", "v20");
        headers.put("applicationId", "1");
        headers.put("channel", "GW_3");
        headers.put("client_version", "1.6.3");
        headers.put("refer", "http://mmapi.yomei.tv/");
        headers.put("did", "865166020148113,460005611084481,fee69b33c464d6e2");
        mHttpHeaders.headersMap =  headers;
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        mBuilder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
        //超时时间设置，默认60秒
        mBuilder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        mBuilder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        mBuilder.connectTimeout(20*1000, TimeUnit.MILLISECONDS);   //全局的连接超时时间
        OkGo.getInstance().init(this)
                .addCommonHeaders(mHttpHeaders)
                .setOkHttpClient(mBuilder.build())
                .setRetryCount(3)
                .setCacheMode(CacheMode.DEFAULT)
                .setCacheTime(100*60*60*24);
    }


    private void initXSnow() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("appkey", "06E0C9C16A169AEC");
        headers.put("api_version", "v20");
        headers.put("applicationId", "1");
        headers.put("channel", "GW_3");
        headers.put("client_version", "1.6.3");
        headers.put("refer", "http://mmapi.yomei.tv/");
        headers.put("did", "865166020148113,460005611084481,fee69b33c464d6e2");
        File cacheFile = new File(this.getCacheDir(), ViseConfig.CACHE_HTTP_DIR);
        ViseHttp.init(this);
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("xsnow");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);
        ViseHttp.CONFIG()
                //配置请求主机地址
                .baseUrl(Contant.baseUrl)
                //配置全局请求头
                .globalHeaders(headers)
                //配置全局请求参数
                //.globalParams(new HashMap<String, String>())
                //配置读取超时时间，单位秒
                .readTimeout(10)
                //配置写入超时时间，单位秒
                .writeTimeout(10)
                //配置连接超时时间，单位秒
                .connectTimeout(6)
                //配置请求失败重试次数
                .retryCount(3)
                //配置请求失败重试间隔时间，单位毫秒
                .retryDelayMillis(1000)
                //配置是否使用cookie
              //  .setCookie(true)
                //配置自定义cookie
                //.apiCookie(new ApiCookie(this))
                //配置是否使用OkHttp的默认缓存
                .setHttpCache(true)
                //配置OkHttp缓存路径
                .setHttpCacheDirectory(cacheFile)
                //配置自定义OkHttp缓存
                .httpCache(new Cache(cacheFile, ViseConfig.CACHE_MAX_SIZE))
                //配置自定义离线缓存
                .cacheOffline(new Cache(cacheFile, ViseConfig.CACHE_MAX_SIZE))
                //配置自定义在线缓存
                .cacheOnline(new Cache(cacheFile, ViseConfig.CACHE_MAX_SIZE))
                //配置开启Gzip请求方式，需要服务器支持
                .postGzipInterceptor()
                //配置应用级拦截器
               /* .interceptor(new HttpLogInterceptor()
                        .setLevel(HttpLogInterceptor.Level.BODY))*/
                .interceptor(loggingInterceptor)
                //配置网络拦截器
              //  .networkInterceptor(new NoCacheInterceptor())
                //配置转换工厂
                .converterFactory(GsonConverterFactory.create())
                //配置适配器工厂
                .callAdapterFactory(RxJava2CallAdapterFactory.create())
               /* //配置请求工厂
                .callFactory(new Call.Factory() {
                    @Override
                    public Call newCall(Request request) {
                        return null;
                    }
                })
                //配置连接池
                .connectionPool(new ConnectionPool())
                //配置主机证书验证
                .hostnameVerifier(new SSLUtil.UnSafeHostnameVerifier(Contant.baseUrl))
                //配置SSL证书验证
                .SSLSocketFactory(SSLUtil.getSslSocketFactory(null, null, null))
                //配置主机代理
                .proxy(new Proxy(Proxy.Type.HTTP, new SocketAddress() {
                }))*/;
    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private RefWatcher stepLeakCanary() {
        return LeakCanary.install(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static RefWatcher getRefWatcher(Context context) {
        App mApp = (App) context.getApplicationContext();
        return mApp.refWatcher;
    }

    public static App getInstance() {
        return mApp;
    }

}
