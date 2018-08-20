package com.example.lenovo.myapplication.request;

/**
 * Created by lenovo on 2018/8/14.
 * auther:lenovo
 * Date：2018/8/14
 */

import android.support.annotation.NonNull;

import com.example.lenovo.myapplication.App;
import com.example.lenovo.myapplication.request.APIException;
import com.example.lenovo.myapplication.utils.CheckNet;
import com.example.lenovo.myapplication.utils.LogUtil;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

public final class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    Level level = Level.BASIC;

    //设置拦截级别，枚举4种
    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Level level = this.level;
        Request request = chain.request();
        if (level == Level.NONE) {
            //不拦截，直接返回
            return chain.proceed(request);
        }
        boolean logBody = level == Level.BODY;
        boolean logHeaders = logBody || level == Level.HEADERS;
        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;
        //建立连接
        Connection connection = chain.connection();
        //拿到连接协议，如果连接不存在就直接用http_1_1协议
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
        //如果设置的级别是base，就打印头部分
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        LogUtil.d(requestStartMessage);
        //如果设置的级别是头，就打印头部分
        if (logHeaders) {
            //如果有请求体，就将请求体的长度和类型打印
            if (hasRequestBody) {
                //请求头的值，存在就拦截
                if (requestBody.contentType() != null) {
                    LogUtil.d("Content-Type: " + requestBody.contentType());
                }
                //-1代表请求数据长度0
                if (requestBody.contentLength() != -1) {
                    LogUtil.d("Content-Length: " + requestBody.contentLength());
                }
            }
            //请求头部分，遍历打印
            Headers headers = request.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                // 这里因为上面已经打印了，所以就过滤一下
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    LogUtil.d(name + ": " + headers.value(i));
                }
            }
            //没有请求体，或者等级没有设置为打印请求体，结束打印
            if (!logBody || !hasRequestBody) {
                LogUtil.d("--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
                //有请求体或者log等级设置为body，打印请求头中设置的编码
                LogUtil.d("--> END " + request.method() + " (encoded body omitted)");
            } else {
                //将请求体数据给写进缓存流
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                //设置编码为utf-8
                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                //判断是否是人类可读的字符，是就打印
                if (isPlaintext(buffer)) {
                    assert charset != null;
                    LogUtil.d(buffer.readString(charset));
                    LogUtil.d("--> END " + request.method()
                            + " (" + requestBody.contentLength() + "-byte body)");
                } else {
                    //人类不懂，就用二进制读出来
                    LogUtil.d("--> END " + request.method() + " (binary "
                            + requestBody.contentLength() + "-byte body omitted)");
                }
            }
        }

        long startNs = System.nanoTime();
        Response response;
        try {
            //请求开始
            response = chain.proceed(request);
        } catch (Exception e) {
            //请求异常
            LogUtil.d("<-- HTTP FAILED: " + e);
            if (CheckNet.isVpnUsed() || CheckNet.isWifiProxy(App.getInstance())) {
                throw new APIException("0x3009", "VPN is used");
            }
            throw e;
        }
        //请求花费时间
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        //响应体了
        ResponseBody responseBody = response.body();
        assert responseBody != null;
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        //打印长度和响应码，响应信息，响应体对应请求体（这里要考虑重定向url），请求耗费时间，响应体长度
        LogUtil.d("<-- " + response.code() + ' ' + response.message() + ' '
                + response.request().url() + " (" + tookMs + "ms" + (!logHeaders ? ", "
                + bodySize + " body" : "") + ')');
        //打印响应头
        if (logHeaders) {
            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                LogUtil.d(headers.name(i) + ": " + headers.value(i));
            }
            //响应体不存在，等级不为body
            if (!logBody || !HttpHeaders.hasBody(response)) {
                LogUtil.d("<-- END HTTP");
            } else if (bodyEncoded(response.headers())) {
                //响应体编码不对称
                LogUtil.d("<-- END HTTP (encoded body omitted)");
            } else {
                //source，响应体来了
                BufferedSource source = responseBody.source();
                //设置最大缓存大小，当然是缓存整个body喽，全吃
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.buffer();
                Charset charset = null;
                //拿到media类型对应的字符集
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    try {
                        charset = contentType.charset(UTF8);
                    } catch (UnsupportedCharsetException e) {
                        LogUtil.d("");
                        LogUtil.d("Couldn't decode the response body; charset is likely malformed.");
                        LogUtil.d("<-- END HTTP");
                        return response;
                    }
                }
                //如果不是人类能看懂的，就不打印string形式的喽
                if (!isPlaintext(buffer)) {
                    LogUtil.d("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
                    return response;
                }
                //是人类懂的，就开始读string喽
                if (contentLength != 0) {
                    assert charset != null;
                    LogUtil.d(buffer.clone().readString(charset));
                }
                //最后打印body的长度
                LogUtil.d("<-- END HTTP (" + buffer.size() + "-byte body)");
            }
        }

        return response;
    }

    /**
     * 判断缓存流的数据是否是人类能读懂的，哈哈，也就是abc123呗
     */
    private static boolean isPlaintext(Buffer buffer) {
        //先判断是不是123abc能看懂的
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                //再判断是不是iso8859-1之类的，当然不是能懂的啊
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    //返回头中的编码是否存在并且不是identity，一般都是gzip,deflate,compress之中一个
    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}