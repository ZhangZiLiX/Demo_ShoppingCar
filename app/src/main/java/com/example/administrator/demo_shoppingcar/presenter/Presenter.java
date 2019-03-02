package com.example.administrator.demo_shoppingcar.presenter;

import android.support.v4.app.NavUtils;

import com.example.administrator.demo_shoppingcar.fragment.adapter.ByCarAdapter;
import com.example.administrator.demo_shoppingcar.item.ICallBack;
import com.example.administrator.demo_shoppingcar.loginpage.bean.LoginBean;
import com.example.administrator.demo_shoppingcar.model.Model;
import com.example.administrator.demo_shoppingcar.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Author : 张自力
 * Created on time.
 *
 */

public class Presenter {

    private IView iView;
    private Model model;
    //关联
    public void  attach(IView miview) {
        iView = miview;
        model = new Model();
    }


    //定义一个方法
    public void doPostDataP(String url,String phone,String pwd){
        //定义泛型
        Type type = new TypeToken<LoginBean>(){}.getType();
        model.doPostDataM(url,phone,pwd,new ICallBack() {
            @Override
            public void onSuccessIC(Object o) {
                if(!o.equals("")){
                    iView.onSuccessIV(o);
                }
            }

            @Override
            public void onFailed(String message) {
                if(iView!=null){
                    iView.onFailed(message);
                }
            }
        },type);


    }


    //定义一个方法
    public void doGetDataP(String url,int userId,String sessionId){
        //定义泛型
        Type type = new TypeToken<ByCarAdapter>(){}.getType();
        model.doGetDataM(url,userId,sessionId ,new ICallBack() {
            @Override
            public void onSuccessIC(Object o) {
                if(!o.equals("")&& o!= null){
                    iView.onSuccessIV(o);
                }
            }

            @Override
            public void onFailed(String message) {
                if(iView!=null){
                    iView.onFailed(message);
                }
            }
        },type);
    }





    //取消关联
    public void  dattach() {
        if(iView!=null){
            iView=null;
        }
        if(model!=null){
            model=null;
        }
    }

}
