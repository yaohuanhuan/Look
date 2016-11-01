package com.look.yx.look.presenter.implView;

/**
 * 所有implView的基类接口，子类需要实现的方法
 * Created by yx on 2016/10/28.
 */

public interface IBaseFragment {
    void showProgressDialog();

    void hidProgressDialog();

    void showError(String error);
}
