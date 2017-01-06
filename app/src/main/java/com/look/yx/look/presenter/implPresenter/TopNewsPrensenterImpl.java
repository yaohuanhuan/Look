package com.look.yx.look.presenter.implPresenter;

import android.content.Context;
import android.util.Log;

import com.look.yx.look.api.ApiManage;
import com.look.yx.look.bean.news.NewsList;
import com.look.yx.look.bean.zhihu.ZhihuDaily;
import com.look.yx.look.fragment.TopNewsFragment;
import com.look.yx.look.fragment.ZhihuFragment;
import com.look.yx.look.presenter.INewTopPresenter;
import com.look.yx.look.presenter.implView.ITopNewsFragment;
import com.look.yx.look.util.NetWorkUtil;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yx on 2016/10/28.
 */

public class TopNewsPrensenterImpl extends BasePresenterImpl implements INewTopPresenter {

    private TopNewsFragment topNewsFragment;
    private Context mContext;
    private Context mAppContext;

    public TopNewsPrensenterImpl(TopNewsFragment topNewsFragment, Context context,Context appContext){
        this.topNewsFragment = topNewsFragment;
        mContext = context;
        mAppContext = appContext;
    }

    @Override
    public void getNewsList(int t) {
        if (NetWorkUtil.isNetWorkAvailable(mAppContext)){
            topNewsFragment.showProgressDialog();
            Subscription subscription = ApiManage.getInstence().getTopNewsService().getNews(t)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<NewsList>() {
                        @Override
                        public void onCompleted() {
                            Log.e("test", "onCompleted");
                            topNewsFragment.hidProgressDialog();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("test", "错误 === " + e.getMessage());
                            topNewsFragment.showError(e.getMessage());
                            topNewsFragment.hidProgressDialog();
                        }

                        @Override
                        public void onNext(NewsList newsList) {

                            Log.e("test", newsList.getNewsList().toString());
                            topNewsFragment.upListItem(newsList);
                            topNewsFragment.hidProgressDialog();
                        }
                    });
            addSubscription(subscription);
        }else {
            topNewsFragment.showError("");
            topNewsFragment.hidProgressDialog();
        }

    }
}
