package com.look.yx.look.presenter;

/**
 * Created by yx on 2016/10/28.
 */

public interface IZhihuPresenter extends BasePresenter {

    void getLastZhihuNews();

    void getTheDaily(String date);

    void getLastFromCache();
}
