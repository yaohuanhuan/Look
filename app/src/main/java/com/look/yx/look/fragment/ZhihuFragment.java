package com.look.yx.look.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.look.yx.look.R;
import com.look.yx.look.adapter.ZhihuAdapter;
import com.look.yx.look.bean.zhihu.ZhihuDaily;
import com.look.yx.look.presenter.implPresenter.ZhihuPreseterImpl;
import com.look.yx.look.presenter.implView.IZhihuFragment;
import com.look.yx.look.util.NetWorkUtil;
import com.look.yx.look.view.GridItemDividerDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yx on 2016/10/28.
 */

public class ZhihuFragment extends BaseFragment implements IZhihuFragment {

    View view = null;
    public ZhihuPreseterImpl zhihuPreseter;
    private ZhihuAdapter zhihuAdapter;
    private String currentLoadDate;
    private LinearLayoutManager mLinearLayoutManager;
    boolean loading;

    @BindView(R.id.recycle_zhihu)
    RecyclerView recycle;
    @BindView(R.id.prograss)
    ProgressBar prograss;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.zhihu_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialDate();
        initialView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        zhihuPreseter.unsubcrible();
    }

    @Override
    public void updateList(ZhihuDaily zhihuDaily) {
        loading = false;
        currentLoadDate = zhihuDaily.getDate();
        zhihuAdapter.addItems(zhihuDaily.getStories());
        //要是数据不够多，判断view是否能够滑动，不能的话就再加载一次数据
        if (!recycle.canScrollVertically(View.SCROLL_INDICATOR_BOTTOM)) {
            loadMoreDate();
        }
    }

    @Override
    public void showProgressDialog() {
        if (prograss != null) {
            prograss.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hidProgressDialog() {
        if (prograss != null) {
            prograss.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String error) {
        if (recycle != null) {
            Snackbar.make(recycle, getString(R.string.snack_infor), Snackbar.LENGTH_LONG).setAction("重试", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentLoadDate.equals("0")) {
                        loading = false;
                        zhihuPreseter.getLastZhihuNews();
                    } else {
                        loading = false;
                        zhihuPreseter.getTheDaily(currentLoadDate);
                    }
                }
            }).show();

        }
    }

    public void initialDate() {
        zhihuPreseter = new ZhihuPreseterImpl(ZhihuFragment.this,getActivity(),getActivity().getApplicationContext());
        zhihuAdapter = new ZhihuAdapter(getContext());
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
                            zhihuPreseter.getLastZhihuNews();
                        } else {
                            loading = false;
                            zhihuPreseter.getTheDaily(currentLoadDate);
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
        recycle.setAdapter(zhihuAdapter);
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

                if (!loading && lastVisibleItem + childCount > ItemCount){
                    loadMoreDate();
                }
            }
        });
        loadDate();
    }

    public void loadDate() {
        loading = true;
        zhihuPreseter.getLastZhihuNews();
    }

    public void loadMoreDate() {
        loading = true;
        zhihuPreseter.getTheDaily(currentLoadDate);
    }
}
