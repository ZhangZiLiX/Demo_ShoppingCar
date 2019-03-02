package com.example.administrator.demo_shoppingcar.model;

import com.example.administrator.demo_shoppingcar.item.ICallBack;
import com.example.administrator.demo_shoppingcar.utils.RxRetrofitUtils;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Author : 张自力
 * Created on time.
 *
 * model层
 *
 */

public class Model {

    //post
    public void doPostDataM(String url,String phone,String pwd, final ICallBack iCallBack, final Type type){
        RxRetrofitUtils.getInstance().doPostDataU(url,phone,pwd, new RxRetrofitUtils.RxRetrofitListener() {
            @Override
            public void onSuccessRXREU(String data) {
                Object o = new Gson().fromJson(data, type);
                if(iCallBack!=null){
                    iCallBack.onSuccessIC(o);
                }
            }

            @Override
            public void onFailedRXREU(String message) {
                if(iCallBack!=null){
                    iCallBack.onFailed(message);
                }
            }
        });
    }

    //doget
    public void doGetDataM(String url,int userId,String sessionId, final ICallBack iCallBack, final Type type){
        RxRetrofitUtils.getInstance().doGetDataU(url,userId,sessionId, new RxRetrofitUtils.RxRetrofitListener() {
            @Override
            public void onSuccessRXREU(String data) {
                Object o = new Gson().fromJson(data, type);
                if(iCallBack!=null){
                    iCallBack.onSuccessIC(o);
                }
            }

            @Override
            public void onFailedRXREU(String message) {
                if(iCallBack!=null){
                    iCallBack.onFailed(message);
                }
            }
        });
    }


}
