package com.look.yx.look.api;


import com.look.yx.look.bean.zhihu.ZhihuDaily;
import com.look.yx.look.bean.zhihu.ZhihuStory;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Observer;

/**
 * Created by yx on 2016/11/9.
 */

public interface ZhihuApi {

    @GET("/api/4/news/latest")
    Observable<ZhihuDaily> getLastDaily();

    @GET("/api/4/news/before/{date}")
    Observable<ZhihuDaily> getTheDaily(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Observable<ZhihuStory> getZhihuStory(@Path("id") String id);

}
