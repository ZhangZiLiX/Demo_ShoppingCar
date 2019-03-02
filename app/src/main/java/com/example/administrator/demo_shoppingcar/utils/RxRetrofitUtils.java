package com.example.administrator.demo_shoppingcar.utils;

import android.content.Context;
import android.util.Log;

import com.bwie.myutilsclass.MyUtils;
import com.example.administrator.demo_shoppingcar.R;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author : 张自力
 * Created on time.
 *
 * 网络请求工具类
 *
 */

public class RxRetrofitUtils {

    //使用单例模式创建对象
    private static volatile RxRetrofitUtils instance;
    private final HttpLoggingInterceptor httpLoggingInterceptor;
    private final Retrofit.Builder retrofit;
    public int urserId;
    public String sessionIds;

    public void getUserIDAndSessionID(int userId,String sessionId){
        urserId = userId;
        sessionIds = sessionId;
    }

    //构造方法
    private RxRetrofitUtils() {
        //使用拦截器
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("http打印日志: ", "log: " + message);
            }
        });
        httpLoggingInterceptor.setLevel(level);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();


        //方式二: 带请求头的OKHttpClient
        //添加请求头的httpClient 带userid 和 sessionid
       /* OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("userId", "52")//添加请求头
                        .addHeader("sessionId", "155149963938152")//添加请求头
                        .build();

                return chain.proceed(request);
            }
        }).build();*/


        //创建Retrofitduixiang
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    }

    //双重锁机制
    public static RxRetrofitUtils getInstance(){
       if(instance==null){
                   synchronized (RxRetrofitUtils.class){
                       if(instance==null){
                   instance = new RxRetrofitUtils();
               }
           }
       }
       return instance;
    }

    private RetrofitIView getRetrofitIV(String url) {
        Retrofit retrofitBuild = retrofit.baseUrl(url)
                .build();
        RetrofitIView retrofitIView = retrofitBuild.create(RetrofitIView.class);
        return retrofitIView;
    }

    //创建一个dopost方法  需要拼接的url
    public RxRetrofitUtils doPostDataU(String url,String phone,String pwd,RxRetrofitListener rxRetrofitListener){
        //得到对象
        RetrofitIView retrofitIV = getRetrofitIV(API.APIBaseURL);
        //调用方法
        retrofitIV.doPostDataRIV(url,phone,pwd)//需要拼接的url
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getObserver(rxRetrofitListener));
        return instance;
    }

    //创建一个doget方法  需要拼接的url
    public RxRetrofitUtils doGetDataU(String url,int userId,String sessionId,RxRetrofitListener rxRetrofitListener){
        //得到对象
        RetrofitIView retrofitIV = getRetrofitIV(API.APIBaseURL);
        //调用方法
        retrofitIV.doGetDataRIV(url,userId,sessionId)//需要拼接的url
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getObserver(rxRetrofitListener));
        return instance;
    }


    //创建一个方法
    private Observer getObserver(final RxRetrofitListener rxRetrofitListener){
        Observer observer = new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String result = responseBody.string();
                    if(rxRetrofitListener!=null){
                        rxRetrofitListener.onSuccessRXREU(result);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if(rxRetrofitListener!=null){
                        rxRetrofitListener.onFailedRXREU(e.getMessage());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
               if(rxRetrofitListener!=null){
                   rxRetrofitListener.onFailedRXREU(e.getMessage());
               }
            }

            @Override
            public void onComplete() {

            }
        };
        return observer;
    }


    //定义一个接口
    public interface RxRetrofitListener{
        void onSuccessRXREU(String data);
        void onFailedRXREU(String message);
    }

}
