package com.example.administrator.demo_shoppingcar.item;

/**
 * Author : 张自力
 * Created on time.
 *
 * 接口回调
 */

public interface ICallBack {
    //成功和 失败的两个方法
    void onSuccessIC(Object o);
    void onFailed(String message);

}
