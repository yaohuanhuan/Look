package com.look.yx.look.presenter.implPresenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.look.yx.look.api.ApiManage;
import com.look.yx.look.bean.meizhi.Meizi;
import com.look.yx.look.bean.meizhi.MeiziData;
import com.look.yx.look.bean.meizhi.VedioData;
import com.look.yx.look.config.Config;
import com.look.yx.look.presenter.IMeiziPresenter;
import com.look.yx.look.presenter.implView.IMeiziFragment;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yx on 2016/10/28.
 */

public class MeiziPresenterImpl extends BasePresenterImpl implements IMeiziPresenter {

    private IMeiziFragment mMeiziFragment;
    private Gson gson = new Gson();

    public MeiziPresenterImpl(Context context, IMeiziFragment mMeiziFragment) {

        this.mMeiziFragment = mMeiziFragment;
    }

    @Override
    public void getMeiziData(int t) {
        mMeiziFragment.showProgressDialog();
        Subscription subscription = ApiManage.getInstence().getGankService().getMeizhiData(t)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MeiziData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mMeiziFragment.hidProgressDialog();
                        mMeiziFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(MeiziData meiziData) {
                        mMeiziFragment.hidProgressDialog();
                        mMeiziFragment.updateMeiziData(meiziData.getResults());
                    }
                });
        addSubscription(subscription);
    }



    @Override
    public void getVedioData(int t) {
        Subscription subscription = ApiManage.getInstence().getGankService().getVedioData(t)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VedioData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mMeiziFragment.hidProgressDialog();
                        mMeiziFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(VedioData vedioData) {
                        mMeiziFragment.hidProgressDialog();
                        mMeiziFragment.updateVedioData(vedioData.getResults());
                    }
                });
        addSubscription(subscription);
    }




}
