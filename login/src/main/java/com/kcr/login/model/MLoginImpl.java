package com.kcr.login.model;


import com.dtr.network.LoginHttpConstants;
import com.dtr.network.bean.LoginUserBean;
import com.kcr.common.base.BaseModel;
import com.kcr.common.util.RxObservable;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.GsonDiskConverter;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.exception.ApiException;

/**
 * Created by Liang_Lu on 2017/12/21.
 * model层用于处理数据,例：网络数据 数据库缓存数据 在此类处理以后返回给P层
 */

public class MLoginImpl extends BaseModel {
    private static final String TAG = "MLoginImpl";

    public void mLogin(String userNameStr, String passwordStr, final RxObservable rxObservable) {
        EasyHttp.get(LoginHttpConstants.urlLogin)
                .params("phone", userNameStr)//支持添加多个参数同时添加
//                .params("param2", passwordStr)//支持添加多个参数同时添加
                //.addCookie(new CookieManger(this).addCookies())//支持添加Cookie
                .cacheTime(300)//缓存300s 单位s
                .cacheKey("cachekey")//缓存key
                .cacheMode(CacheMode.CACHEANDREMOTE)//设置请求缓存模式
                //.okCache()//使用模式缓存模式时，走Okhttp缓存
                .cacheDiskConverter(new GsonDiskConverter())//GSON-数据转换器
                //.certificates()添加证书
                .retryCount(2)//本次请求重试次数
                .retryDelay(500)//本次请求重试延迟时间500ms
                .execute(new CallBack<LoginUserBean>() {
                    @Override
                    public void onStart() {
                        //开始请求
                    }

                    @Override
                    public void onCompleted() {
                        //请求完成
                    }

                    @Override
                    public void onError(ApiException e) {
                        //请求错误
                        rxObservable.onError(e);
                    }
                    @Override
                    public void onSuccess(LoginUserBean response) {
                        //请求成功
                        rxObservable.onNext(response);
                    }
                });
    }

}

