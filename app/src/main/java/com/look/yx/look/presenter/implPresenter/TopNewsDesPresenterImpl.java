package com.look.yx.look.presenter.implPresenter;


import com.look.yx.look.bean.news.NewsDetailBean;
import com.look.yx.look.presenter.INewTopDescriblePresenter;
import com.look.yx.look.presenter.implView.ITopNewsDesFragment;
import com.look.yx.look.util.NewsJsonUtils;
import com.look.yx.look.util.OkHttpUtils;
import com.look.yx.look.util.Urls;

/**
 * Created by 蔡小木 on 2016/4/26 0026.
 */
public class TopNewsDesPresenterImpl extends BasePresenterImpl implements INewTopDescriblePresenter {

    private ITopNewsDesFragment mITopNewsFragment;

    public TopNewsDesPresenterImpl(ITopNewsDesFragment topNewsFragment) {
        if (topNewsFragment == null)
            throw new IllegalArgumentException(" must not be null");
        mITopNewsFragment = topNewsFragment;
    }
    private String getDetailUrl(String docId) {
        StringBuffer sb = new StringBuffer(Urls.NEW_DETAIL);
        sb.append(docId).append(Urls.END_DETAIL_URL);
        return sb.toString();
    }

    @Override
    public void getDescrible(final String docid) {
        mITopNewsFragment.showProgressDialog();
        String url = getDetailUrl(docid);
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, docid);
               mITopNewsFragment.upListItem(newsDetailBean);
            }

            @Override
            public void onFailure(Exception e) {
                mITopNewsFragment.showError(e.toString());
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);

    }
}
