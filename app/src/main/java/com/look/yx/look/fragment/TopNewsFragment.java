package com.look.yx.look.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.look.yx.look.R;
import com.look.yx.look.adapter.TopNewsAdapter;
import com.look.yx.look.adapter.ZhihuAdapter;
import com.look.yx.look.bean.news.NewsList;
import com.look.yx.look.presenter.implPresenter.TopNewsPrensenterImpl;
import com.look.yx.look.presenter.implPresenter.ZhihuPreseterImpl;
import com.look.yx.look.presenter.implView.ITopNewsFragment;
import com.look.yx.look.view.GridItemDividerDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yx on 2016/10/28.
 */

public class TopNewsFragment extends BaseFragment implements ITopNewsFragment {

    View view = null;
    @BindView(R.id.recycle_top)
    RecyclerView recycle;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.prograss)
    ProgressBar prograss;

    public TopNewsPrensenterImpl topNewsPrensenter;
    private TopNewsAdapter topNewsAdapter;
    private String currentLoadDate;
    private LinearLayoutManager mLinearLayoutManager;
    boolean loading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialDate();
        initialView();

    }

    public void initialDate() {
        topNewsPrensenter = new TopNewsPrensenterImpl(TopNewsFragment.this,getActivity(),getActivity().getApplicationContext());
        topNewsAdapter = new TopNewsAdapter(getContext());
        currentLoadDate = "0";
    }

    public void initialView() {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mSwipeRefreshLayout.setProgressViewOffset(false, -50, 150);
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // 刷新动画开始后回调到此方法
                        if (currentLoadDate.equals("0")) {
                            loading = false;
                            topNewsPrensenter.getNewsList(0);
                        } else {
                            loading = false;
                            topNewsPrensenter.getNewsList(0);
                        }
                        mSwipeRefreshLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //让刷新飘一会，爽一爽
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        },800);

                    }
                }
        );
        recycle.setLayoutManager(mLinearLayoutManager);
        recycle.setHasFixedSize(true);
        recycle.addItemDecoration(new GridItemDividerDecoration(getContext(), R.dimen.divider_height, R.color.divider));
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(topNewsAdapter);
        recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                int childCount = mLinearLayoutManager.getChildCount();
                int ItemCount = mLinearLayoutManager.getItemCount();

//                if (!loading && lastVisibleItem + childCount > ItemCount){
//                    loadMoreDate();
//                }
            }
        });
        loadDate();
    }

    public void loadDate() {
        loading = true;
        topNewsPrensenter.getNewsList(0);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidProgressDialog() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void upListItem(NewsList newsList) {
        topNewsAdapter.addItems(newsList.getNewsList());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        topNewsPrensenter.unsubcrible();
    }
}
