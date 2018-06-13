package com.kcr.main.presenter;

import android.content.Context;

import com.kcr.common.bean.BannerBean;
import com.kcr.common.base.BasePresenter;
import com.kcr.common.util.RxObservable;
import com.kcr.main.bean.HomeWelfareBean;
import com.kcr.main.contract.CHomeWelfare;
import com.kcr.main.moudle.MHomeWelfareImpl;

import java.util.List;

public class PHomeWelfareImpl extends BasePresenter<CHomeWelfare.IVHomeWelfare, MHomeWelfareImpl> implements CHomeWelfare.IPHomeWelfare {
    public PHomeWelfareImpl(Context mContext, CHomeWelfare.IVHomeWelfare mView) {
        super(mContext, mView, new MHomeWelfareImpl());
    }

    @Override
    public void pHomeWelfare() {
//        mView.showLoading();
        mModel.mHomeWelfare(new RxObservable<List<HomeWelfareBean>>() {
            @Override
            public void onSuccess(List<HomeWelfareBean> list) {
                mView.vOnHomeWelfareSuccess(list);
            }

            @Override
            public void onFail(String reason) {
                mView.vOnHomeWwlafareFail(reason);
            }
        });
    }

    @Override
    public void pHomeBanner() {
        mModel.mBanner(new RxObservable<List<BannerBean>>() {
            @Override
            public void onSuccess(List<BannerBean> list) {
                mView.vOnBannerSuccess(list);
            }

            @Override
            public void onFail(String reason) {
                mView.vOnBannerFail(reason);
            }
        });
    }
}
