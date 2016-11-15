package com.look.yx.look.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yx on 2016/11/8.
 */

public class ApiManage {

//    //私有构造方法
//    private ApiManage(){}
    //ApiManage实例
    public static ApiManage apiManage;
    //ZhihuApi引用
    public ZhihuApi zhihuApi;
    //OkHttp打印日志类，可以打印http请求信息
    public static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    //OkHttpClient实例
    public static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build();

    private Object zhihuMonitor = new Object();

    /**
     * 拿到ApiManage实例
     * @return
     */
    public static ApiManage getInstence() {
        if (apiManage == null) {
            synchronized (ApiManage.class) {
                if (apiManage == null) {
                    apiManage = new ApiManage();
                }
            }
        }
        return apiManage;
    }

    /**
     * 拿到ZhihuApi引用
     * @return
     */
    public ZhihuApi getZhihuApiService(){
        if (zhihuApi == null){
            synchronized (zhihuMonitor){
                if (zhihuApi == null){
                    zhihuApi = new Retrofit.Builder()
                            .baseUrl("http://news-at.zhihu.com")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build().create(ZhihuApi.class);
                }
            }
        }
        return zhihuApi;
    }
}
