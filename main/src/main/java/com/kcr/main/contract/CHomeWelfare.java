package com.kcr.main.contract;

import com.dtr.network.bean.BannerBean;
import com.kcr.common.base.IBasePresenter;
import com.kcr.common.base.IBaseView;
import com.kcr.main.bean.HomeWelfareBean;

import java.util.List;

public interface CHomeWelfare {
    interface IPHomeWelfare extends IBasePresenter {
        void pHomeWelfare();

        void pHomeBanner();
    }

    interface IVHomeWelfare extends IBaseView {
        void vOnHomeWelfareSuccess(List<HomeWelfareBean> bean);

        void vOnBannerSuccess(List<BannerBean> bean);

        void vOnHomeWwlafareFail(String fail);

        void vOnBannerFail(String fail);
    }
}
