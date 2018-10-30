package com.kcr.login.model;


import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.kcr.common.base.BaseBean;
import com.kcr.common.base.BaseModel;
import com.kcr.common.bean.LoginBean;
import com.kcr.common.util.GsonUtils;
import com.kcr.common.util.RxObservable;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Liang_Lu on 2017/12/21.
 * model层用于处理数据,例：网络数据 数据库缓存数据 在此类处理以后返回给P层
 */

public class MLoginImpl extends BaseModel {
    private static final String TAG = "MLoginImpl";

    public void mLogin(final RxObservable rxObservable) {
        String url = "http://www.wanandroid.com/tools/mockapi/6557/login";
        Observable.timer(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
//                Type type = new TypeToken<BaseBean<LoginBean>>() {
//                }.getType();
//                BaseBean<LoginBean> bean = GsonUtils.parseJsonToBean(result, type);
//                if (bean.getCode() == 200) {
//                    rxObservable.onNext(bean.getData());公益类别
//                }
                rxObservable.onNext(new LoginBean());
            }
        });
    }

}

