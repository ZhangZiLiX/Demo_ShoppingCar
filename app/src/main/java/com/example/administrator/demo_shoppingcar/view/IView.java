package com.example.administrator.demo_shoppingcar.view;

/**
 * Author : 张自力
 * Created on time.
 *
 * 接口
 *
 */

public interface IView<T> {
    //成功失败的方法
    void onSuccessIV(T t);
    void onFailed(String message);

}
