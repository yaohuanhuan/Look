package com.look.yx.look.activity;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by yx on 2016/11/15.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
