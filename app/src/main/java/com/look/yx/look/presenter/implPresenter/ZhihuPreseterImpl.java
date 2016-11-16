package com.look.yx.look.presenter.implPresenter;

import android.util.Log;

import com.look.yx.look.api.ApiManage;
import com.look.yx.look.bean.zhihu.ZhihuDaily;
import com.look.yx.look.bean.zhihu.ZhihuDailyItem;
import com.look.yx.look.fragment.ZhihuFragment;
import com.look.yx.look.presenter.IZhihuPresenter;

import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yx on 2016/10/28.
 */

public class ZhihuPreseterImpl extends BasePresenterImpl implements IZhihuPresenter {

    private ZhihuFragment mZhihuFragment;

    public ZhihuPreseterImpl(ZhihuFragment zhihuFragment){
        mZhihuFragment = zhihuFragment;
    }

    @Override
    public void getLastZhihuNews() {
        mZhihuFragment.showProgressDialog();
        Subscription subscription = ApiManage.getInstence().getZhihuApiService().getLastDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuDaily>() {
                    @Override
                    public void onCompleted() {
                        Log.e("test", "onCompleted");
                        mZhihuFragment.hidProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("test", "错误 === " + e.getMessage());
                        mZhihuFragment.hidProgressDialog();
                    }

                    @Override
                    public void onNext(ZhihuDaily zhihuDaily) {
                        Log.e("test", zhihuDaily.toString());
                        mZhihuFragment.updateList(zhihuDaily);
                        mZhihuFragment.hidProgressDialog();
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void getTheDaily(String date) {
        mZhihuFragment.showProgressDialog();
        Subscription subscription = ApiManage.getInstence().getZhihuApiService().getTheDaily(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuDaily>() {
                    @Override
                    public void onCompleted() {
                        Log.e("test", "onCompleted");
                        mZhihuFragment.hidProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("test", "错误 === " + e.getMessage());
                        mZhihuFragment.showError(e.getMessage());
                        mZhihuFragment.hidProgressDialog();
                    }

                    @Override
                    public void onNext(ZhihuDaily zhihuDaily) {
                        Log.e("test", zhihuDaily.toString());
                        mZhihuFragment.updateList(zhihuDaily);
                        mZhihuFragment.hidProgressDialog();
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void getLastFromCache() {

    }
}
