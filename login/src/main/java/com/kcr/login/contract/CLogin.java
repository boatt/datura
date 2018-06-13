package com.kcr.login.contract;


import com.kcr.common.base.IBasePresenter;
import com.kcr.common.base.IBaseView;
import com.kcr.common.bean.LoginBean;

/**
 * Created by Liang_Lu on 2017/12/22.
 * Contract用于存放mvp三层接口类  放在一起便于管理，不用生成太多的类（因为model没有需要处理的数据，所以没有使用接口，可自行生成实现）
 */

public interface CLogin {

    interface IPLogin extends IBasePresenter {
        void pLogin();
    }

    interface IVLogin extends IBaseView {

        void vLoginSuccess(LoginBean bean);

        void vLoginError(String reason);

    }
}
