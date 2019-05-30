package com.kcr.login.presenter;

import android.content.Context;

import com.kcr.common.base.BasePresenter;
import com.dtr.network.bean.LoginUserBean;
import com.kcr.common.util.RxObservable;
import com.kcr.login.contract.CLogin;
import com.kcr.login.model.MLoginImpl;


/**
 * Created by Liang_Lu on 2017/12/21.
 * P层 此类只用于处理业务逻辑 然后把最终的结果回调给V层
 */

public class PLoginImpl extends BasePresenter<CLogin.IVLogin, MLoginImpl> implements CLogin.IPLogin {


    public PLoginImpl(Context mContext, CLogin.IVLogin mView) {
        super(mContext, mView, new MLoginImpl());
    }


    @Override
    public void pLogin(String userNameStr, String passwordStr) {
        mView.showLoading();
        mModel.mLogin(userNameStr,passwordStr,new RxObservable<LoginUserBean>() {

            @Override
            public void onSuccess(LoginUserBean bean) {
                mView.hideLoading();
                mView.vLoginSuccess(bean);
            }

            @Override
            public void onFail(String reason) {
                mView.hideLoading();
                mView.vLoginError(reason);
            }
        });
    }
}
