package com.kcr.main.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kcr.common.base.BasicActivity;
import com.kcr.common.util.AppCache;

public class SplashActivity extends BasicActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCache.isLogin()) {
            jumpActivity(MainActivity.class);

        } else {
            jumpActivity("/login/loginActivity");
        }
        finish();
    }
}
