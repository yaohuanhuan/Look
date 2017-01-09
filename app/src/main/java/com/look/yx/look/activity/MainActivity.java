package com.look.yx.look.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.look.yx.look.R;
import com.look.yx.look.fragment.MeiziFragment;
import com.look.yx.look.fragment.TopNewsFragment;
import com.look.yx.look.fragment.ZhihuFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yx on 2016/11/16.
 * QQ邮箱1107129170@qq.com
 * 微信18815289611
 */

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.bottomNavigationBar)
    BottomNavigationBar bottomNavigationBar;

    private ZhihuFragment zhihuFragment = null;
    private TopNewsFragment topNewsFragment = null;
    private MeiziFragment meiziFragment = null;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "zhihu")).setActiveColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "news"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp, "meizi"))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                showFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        showFragment(0);
    }

    private void showFragment(int index) {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fTransaction);
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (index) {
            case 0:
                if (zhihuFragment == null && fragmentManager.findFragmentByTag("zhihu") == null) {
                    zhihuFragment = new ZhihuFragment();
                    fTransaction.add(R.id.fragment_container, zhihuFragment, "zhihu");
                } else {
                    zhihuFragment = (ZhihuFragment) fragmentManager.findFragmentByTag("zhihu");
                    fTransaction.show(zhihuFragment);
                }
                break;
            case 1:
                if (topNewsFragment == null && fragmentManager.findFragmentByTag("news") == null) {
                    topNewsFragment = new TopNewsFragment();
                    fTransaction.add(R.id.fragment_container, topNewsFragment, "news");
                } else {
                    topNewsFragment = (TopNewsFragment) fragmentManager.findFragmentByTag("news");
                    fTransaction.show(topNewsFragment);
                }
                break;
            case 2:
                if (meiziFragment == null && fragmentManager.findFragmentByTag("meizi") == null) {
                    meiziFragment = new MeiziFragment();
                    fTransaction.add(R.id.fragment_container, meiziFragment, "meizi");
                } else {
                    meiziFragment = (MeiziFragment) fragmentManager.findFragmentByTag("meizi");
                    fTransaction.show(meiziFragment);
                }
                break;
        }

        fTransaction.commit();
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (zhihuFragment != null) fragmentTransaction.hide(zhihuFragment);
        if (topNewsFragment != null) fragmentTransaction.hide(topNewsFragment);
        if (meiziFragment != null) fragmentTransaction.hide(meiziFragment);

    }

    public interface LoadingMore {

        void loadingStart();

        void loadingfinish();
    }

}
