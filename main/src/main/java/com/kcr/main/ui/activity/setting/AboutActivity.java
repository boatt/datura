package com.kcr.main.ui.activity.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kcr.common.base.BasicActivity;
import com.kcr.main.R;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/5/005.
 */

public class AboutActivity extends BasicActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strConstellationlist[] = {"reactivex.rxjava2:rxjava:2.0.1", "qmuiteam:qmui:1.0.6", "bumptech.glide:glide:3.7.0",
                        "alibaba:arouter-api:1.3.1", "squareup.okhttp3:okhttp:3.9.1", "CymChad:BaseRecyclerViewAdapterHelper:2.9.28",
                        "jaredrummler:android-animations:1.0.0", "jbox2d:1.0.0", "PhilJay:MPAndroidChart:v3.0.2",};

                new QMUIDialog.MenuDialogBuilder(mContext)
                        .addItems(strConstellationlist, null)
                        .show();
            }
        });


    }

}
