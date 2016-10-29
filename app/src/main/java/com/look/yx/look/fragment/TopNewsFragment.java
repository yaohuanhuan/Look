package com.look.yx.look.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.look.yx.look.R;
import com.look.yx.look.presenter.implView.IZhihuFragment;

/**
 * Created by yx on 2016/10/28.
 */

public class TopNewsFragment extends BaseFragment implements IZhihuFragment {

    View view = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_fragment_layout,container,false);
        return view;
    }
}
