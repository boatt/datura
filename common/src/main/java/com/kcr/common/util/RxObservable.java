package com.kcr.common.util;


import com.kcr.common.base.IDevMvpCallBack;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Liang_Lu on 2017/12/21.
 */

public abstract class RxObservable<T> implements Observer<T>, IDevMvpCallBack<T> {

    public RxObservable() {
    }


//    /**
//     * 失败回调
//     *
//     * @param errorMsg 错误信息
//     */
//    protected abstract void onError(String errorMsg);
//
//    /**
//     * 成功回调
//     *
//     * @param data 结果
//     */
//    protected abstract void onSuccess(T data);
//


    @Override
    public void onSubscribe(Disposable d) {
        LogUtils.print("RxObservable", d + "");
    }

    @Override
    public void onError(Throwable e) {
        String error =  e.getMessage();
        ToastUtils.show(error);
//        onError(error);
        this.onFail(error);
    }


    @Override
    public void onNext(T value) {
        //可以根据需求对code统一处理
//        onSuccess(value);
        this.onSuccess(value);
    }


    @Override
    public void onComplete() {
    }
}
