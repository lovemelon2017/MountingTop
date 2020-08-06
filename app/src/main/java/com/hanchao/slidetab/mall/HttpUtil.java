package com.hanchao.slidetab.mall;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求.
 */

public class HttpUtil {

    private static OkHttpClient mOkHttpClient = null;
    private static Retrofit mRetrofit;

    static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            //增加头部信息
            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request build = original.newBuilder()
                                .header("Content-Type", "application/x-www-form-urlencoded")
                                .build();
                        return chain.proceed(build);


                }
            };


            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(25, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(headerInterceptor);

            mOkHttpClient = builder.build();
        }
        return mOkHttpClient;
    }


    /**
     * retrofit init
     *
     * @return
     */
    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://test.maiduo.com/")
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public static <T> T getApiClass(Class<T> tClass) {
        return getRetrofit().create(tClass);
    }


}
