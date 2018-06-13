package com.kcr.login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kcr.common.base.BasicActivity;
@Route(path = "/login/TestActivity")
public class TestActivity extends BasicActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView test = new TextView(this);
        test.setText("xxxxxxxxxxx");
        setContentView(test);
    }
}
