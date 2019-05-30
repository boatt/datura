package com.kcr.main.ui.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kcr.common.base.BasicActivity;
import com.kcr.common.util.AppCache;
import com.kcr.main.R;

/**
 *
 */

public class SettingActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, FeedbackActivity.class));
            }
        });
        findViewById(R.id.tv_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, AboutActivity.class));
            }
        });
        findViewById(R.id.tv_clear_cache).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("缓存已清除");
            }
        });
        findViewById(R.id.tv_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("退出");
                AppCache.loginOut();
            }
        });
    }

}
