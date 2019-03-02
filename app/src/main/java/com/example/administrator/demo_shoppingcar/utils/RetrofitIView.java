package com.example.administrator.demo_shoppingcar.utils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Author : 张自力
 * Created on time.
 *
 *
 * Retrofit接口
 */

public interface RetrofitIView {

    //doPost
    @POST
    Observable<ResponseBody> doPostDataRIV(@Url String url, @Query("phone") String phone,@Query("pwd") String pwd);

    //doGet
    @GET
    Observable<ResponseBody> doGetDataRIV(@Url String url, @Header("userId") int userId,@Header("sessionId") String sessionId);

}
