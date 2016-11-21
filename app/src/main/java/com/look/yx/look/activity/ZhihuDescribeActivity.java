package com.look.yx.look.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.look.yx.look.bean.zhihu.ZhihuStory;
import com.look.yx.look.presenter.implView.IZhihuStory;

/**
 * Created by yx on 2016/11/21.
 */

public class ZhihuDescribeActivity extends AppCompatActivity implements IZhihuStory {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void showError(String error) {

    }

    @Override
    public void showZhihuStory(ZhihuStory zhihuStory) {

    }


}
