package com.look.yx.look.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.look.yx.look.R;
import com.look.yx.look.presenter.implView.IMeiziFragment;

/**
 * Created by yx on 2016/10/28.
 */

public class MeiziFragment extends BaseFragment implements IMeiziFragment {

    View view = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.meizi_fragment_layout,container,false);
        return view;
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
}
