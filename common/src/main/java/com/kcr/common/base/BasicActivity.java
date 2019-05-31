package com.kcr.common.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kcr.common.R;
import com.kcr.common.util.UIUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/10/010.
 */

public class BasicActivity<T extends BasePresenter> extends AppCompatActivity {
    protected AppCompatActivity mContext;
    protected QMUITipDialog tipDialog;
    private Unbinder bind;
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        overridePendingTransition(R.anim.a_to_b_of_in, R.anim.a_to_b_of_out);
        createPresenter();
    }

    public void createPresenter() {
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.a_back_b_of_in, R.anim.a_back_b_of_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();//页面销毁 网络请求同销毁
        }
        if (bind != null) {
            bind.unbind();
        }
    }

    public void loadImage(ImageView img ,String url) {
        Glide.with(img.getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img);
    }
    public void loadImage(ImageView img ,int res) {
        Glide.with(img.getContext()).load(res).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img);
    }
    @Override
    public void setContentView(int layoutResId) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        super.setContentView(layoutResId);
        bind = ButterKnife.bind(this);
    }

    public void jumpActivity(Class clz) {
        startActivity(new Intent(mContext, clz));
    }

    public void jumpActivity(String path) {
        ARouter.getInstance().build(path).navigation();
    }

    public void showToast(String msg) {
        UIUtils.showMessage(this, msg);
    }

    public void showLoading() {
        if (tipDialog == null) {
            tipDialog = new QMUITipDialog.Builder(this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord("请求网络中...")
                    .create();
            tipDialog.show();
        } else {
            tipDialog.show();
        }
        mTipDialog.sendEmptyMessageDelayed(1, 5000);
    }

    Handler mTipDialog = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dismissLoading();
        }
    };

    public void dismissLoading() {
        if (tipDialog != null && tipDialog.isShowing()) {
            tipDialog.dismiss();
        }
    }
    public void hideLoading() {
        if (tipDialog != null && tipDialog.isShowing()) {
            tipDialog.dismiss();
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            if (tipDialog != null) {
//                tipDialog.dismiss();
//                finish();
//            }
//            return false;
//        } else {
//            return super.onKeyDown(keyCode, event);
//        }
//
//    }
}
