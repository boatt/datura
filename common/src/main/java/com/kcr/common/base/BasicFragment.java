package com.kcr.common.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kcr.common.util.UIUtils;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/10/010.
 */

public abstract class BasicFragment<T extends BasePresenter> extends Fragment {
    protected QMUITipDialog tipDialog;
    protected FragmentActivity mContext;
    private boolean isInitData = false; /*标志位 判断数据是否初始化*/
    private boolean isVisibleToUser = false; /*标志位 判断fragment是否可见*/
    private boolean isPrepareView = false; /*标志位 判断view已经加载完成 避免空指针操作*/
    private Unbinder mBind;
    protected Bundle mBundle;
    protected View mRootView;
    public T mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        mBundle = savedInstanceState;
        mRootView = inflater.inflate(getLayoutId(), container, false);
        mBind = ButterKnife.bind(this, mRootView);
        createPresenter();
        EventBus.getDefault().register(this);
        isVisibleToUser = true;
        return mRootView;
    }

    public void createPresenter() {
    }

    ;

    public void jumpActivity(Class clz) {
        getActivity().startActivity(new Intent(mContext, clz));
    }
    public void jumpActivity(String path) {
        ARouter.getInstance()
                .build(path)
                .navigation();
    }
    public abstract int getLayoutId();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventReceiver(BasicEventBean data) {
        if (data != null) {
            onReceiverData(data);
        }
    }

    public void onReceiverData(BasicEventBean data) {

    }

    /*懒加载方法*/
    private void lazyInitData() {
        if (!isInitData && isVisibleToUser && isPrepareView) {
            isInitData = true;
            initData();
        } else if (isInitData && isVisibleToUser && isPrepareView) {
            reLoadData();
        }
    }

    public void reLoadData() {

    }

    @Override
    public void onDestroyView() {
        mBind.unbind();
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    /*加载数据的方法,由子类实现*/
    protected abstract void initData();

    /*当fragment由可见变为不可见和不可见变为可见时回调*/

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        lazyInitData();
    }

    /*fragment生命周期中onViewCreated之后的方法 在这里调用一次懒加载 避免第一次可见不加载数据*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyInitData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isPrepareView = true;
    }

    public void showToast(String msg) {
        UIUtils.showMessage(getActivity(), msg);
    }

    public void showLoading() {
        if (tipDialog == null) {
            tipDialog = new QMUITipDialog.Builder(getActivity())
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord("请求网络中...")
                    .create();
            tipDialog.show();
        } else {
            tipDialog.show();
        }
        mRootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoading();
            }
        }, 5000);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

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

}
