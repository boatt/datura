package com.kcr.login.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dtr.network.bean.LoginUserBean;
import com.kcr.common.base.BasicActivity;
import com.kcr.common.util.AppCache;
import com.kcr.login.R;
import com.kcr.login.contract.CLogin;
import com.kcr.login.presenter.PLoginImpl;

@Route(path = "/login/loginActivity")
public class LoginActivity extends BasicActivity<PLoginImpl> implements CLogin.IVLogin {
    private static final String TAG = "LoginActivity";
    EditText username;
    EditText password;
    TextView verifyMode;
    TextView tourist_mode;
    TextView forget;
    TextView getCode;
    private boolean isVerifyMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        verifyMode = findViewById(R.id.verify_mode);
        tourist_mode = findViewById(R.id.tourist_mode);
        forget = findViewById(R.id.forget);
        getCode = findViewById(R.id.getCode);

        verifyMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchModeLogin();
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameStr = username.getText().toString().trim();
                String passwordStr = password.getText().toString().trim();
                if (!TextUtils.isEmpty(userNameStr) && !TextUtils.isEmpty(passwordStr)) {
                    mPresenter.pLogin(userNameStr, passwordStr);
                }

            }
        });

    }

    private void switchModeLogin() {
        isVerifyMode = !isVerifyMode;
        Drawable drawable = null;
        Drawable drawablePasswordHint = null;
        String mode;
        String passwordHint;
        if (isVerifyMode) {
            drawable = getResources().getDrawable(R.mipmap.icon_c_pass_word);
            drawablePasswordHint = getResources().getDrawable(R.mipmap.ic_login_password);
            mode = "密码模式";
            passwordHint = getString(R.string.login_password);
        } else {
            drawable = getResources().getDrawable(R.mipmap.ic_login_phone);
            drawablePasswordHint = getResources().getDrawable(R.mipmap.icon_phone_code);
            mode = "验证码模式";
            passwordHint = getString(R.string.login_v_hint);
        }
        drawablePasswordHint.setBounds(0, 0, drawablePasswordHint.getMinimumWidth(), drawablePasswordHint.getMinimumHeight());
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        password.setCompoundDrawables(drawablePasswordHint, null, null, null);
        password.setHint(passwordHint);
        verifyMode.setCompoundDrawables(null, drawable, null, null);
        verifyMode.setText(mode);

        if (isVerifyMode) {
            getCode.setVisibility(View.GONE);
            forget.setVisibility(View.VISIBLE);
        }else{
            forget.setVisibility(View.GONE);
            getCode.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void createPresenter() {
        mPresenter = new PLoginImpl(mContext, this);
    }


    @Override
    public void vLoginSuccess(LoginUserBean bean) {
        AppCache.setUser(bean);
        jumpActivity("/main/mainActivity");
        finish();
    }

    @Override
    public void vLoginError(String reason) {

    }


}

