package com.kcr.login.view;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kcr.common.base.BasicActivity;
import com.kcr.login.R;
import com.kcr.common.bean.LoginBean;
import com.kcr.login.contract.CLogin;
import com.kcr.login.presenter.PLoginImpl;

@Route(path = "/login/loginActivity")
public class LoginActivity extends BasicActivity<PLoginImpl> implements CLogin.IVLogin {
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.pLogin();

            }
        });

    }

    @Override
    public void createPresenter() {
        mPresenter = new PLoginImpl(mContext, this);
    }


    @Override
    public void vLoginSuccess(LoginBean bean) {
        jumpActivity("/main/mainActivity");
        finish();
    }

    @Override
    public void vLoginError(String reason) {

    }


}

