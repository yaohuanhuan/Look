package com.look.yx.look.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.look.yx.look.R;
import com.look.yx.look.adapter.ZhihuAdapter;
import com.look.yx.look.bean.zhihu.ZhihuDaily;
import com.look.yx.look.presenter.implPresenter.ZhihuPreseterImpl;
import com.look.yx.look.presenter.implView.IZhihuFragment;

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
        //要是数据不够多，判断view是否能够滑动，不能的话就在加载一次数据
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

    }

    public void initialDate() {
        zhihuPreseter = new ZhihuPreseterImpl(ZhihuFragment.this);
        zhihuAdapter = new ZhihuAdapter(getContext());
        currentLoadDate = "0";
    }

    public void initialView() {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        recycle.setLayoutManager(mLinearLayoutManager);
        recycle.setHasFixedSize(true);
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
                int a = lastVisibleItem + childCount;
                Log.e("test", "" + a+"    "+ItemCount);
                if (!loading && lastVisibleItem + childCount > ItemCount){
                    loading = true;
                    Log.e("test", "加载了！");
                    loadMoreDate();
                }


            }
        });
        loadDate();
    }

    public void loadDate() {
        zhihuPreseter.getLastZhihuNews();
    }

    public void loadMoreDate() {

        zhihuPreseter.getTheDaily(currentLoadDate);
    }
}
