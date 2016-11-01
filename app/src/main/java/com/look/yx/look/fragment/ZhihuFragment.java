package com.look.yx.look.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.look.yx.look.R;
import com.look.yx.look.bean.zhihu.ZhihuDaily;
import com.look.yx.look.presenter.implView.IZhihuFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yx on 2016/10/28.
 */

public class ZhihuFragment extends BaseFragment implements IZhihuFragment {

    View view = null;
    @BindView(R.id.test)
    TextView myname;

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
    public void updateList(ZhihuDaily zhihuDaily) {

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

    public void initialDate(){

    }

    public void initialView(){

    }
}
