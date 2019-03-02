package com.example.administrator.demo_shoppingcar.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Author : 张自力
 * Created on time.
 * 全局初始化
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Fresco
        initFrasco();
    }

    //初始化Fresco
    private void initFrasco() {
        Fresco.initialize(this);
    }
}
