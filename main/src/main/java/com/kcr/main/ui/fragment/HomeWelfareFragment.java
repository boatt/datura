package com.kcr.main.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kcr.common.bean.BannerBean;
import com.kcr.common.base.BasicFragment;
import com.kcr.common.util.layoutmanager.layoutmanagergroup.skidright.SkidRightLayoutManager;
import com.kcr.common.widget.NoticeView;
import com.kcr.main.R;
import com.kcr.main.bean.HomeWelfareBean;
import com.kcr.main.contract.CHomeWelfare;
import com.kcr.main.presenter.PHomeWelfareImpl;
import com.kcr.main.ui.activity.DetailsWelfareActivirt;

import java.util.List;


public class HomeWelfareFragment extends BasicFragment<PHomeWelfareImpl> implements CHomeWelfare.IVHomeWelfare {

    RecyclerView rvList;
    private int mType;
    private boolean flag;
    private boolean isSearch;
    private int pageIndex = 1;
    private int PAGE_SIZE = 10;
    private BaseQuickAdapter mAdapter;
    NoticeView nvBanner;

    public static HomeWelfareFragment newInstance(int type) {
        HomeWelfareFragment fragment = new HomeWelfareFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_welfare;
    }

    @Override
    protected void initData() {
        mType = getArguments().getInt("type");
        rvList = mRootView.findViewById(R.id.rv_list);
        nvBanner = mRootView.findViewById(R.id.nv_banner);
        initAdapter();
        initBanner();
    }

    private void initBanner() {
        nvBanner.setOnNoticeClickListener(new NoticeView.OnNoticeClickListener() {
            @Override
            public void onNotieClick(int position, BannerBean notice) {
//                startActivity(new Intent(getActivity(), PublicUseWebActivity.class).putExtra("url", notice.getActivityUrl() + "/share?id=" + HttpUtil.getUserid()));
            }
        });
        mPresenter.pHomeBanner();
    }

    private void initAdapter() {//BaseQuickAdapter
        rvList.setLayoutManager(new SkidRightLayoutManager(1.5f, 0.85f));

        mAdapter = new BaseQuickAdapter<HomeWelfareBean, BaseViewHolder>(R.layout.item_welfare) {

            @Override
            protected void convert(final BaseViewHolder helper, final HomeWelfareBean item) {
                ImageView imgBg = helper.getView(R.id.img_bg);
                Glide.with(imgBg.getContext()).load(item.getImage()) .animate( android.R.anim.slide_in_left ) .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imgBg);
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_bottom, item.getDesc());
                imgBg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, DetailsWelfareActivirt.class);
                        intent.putExtra("img", item.getImage());
                        intent.putExtra("title", item.getTitle());
                        intent.putExtra("desc", item.getDesc());
                        Pair<View, String> p1 = Pair.create((View) helper.getView(R.id.img_bg), "img_view_1");
                        Pair<View, String> p2 = Pair.create((View) helper.getView(R.id.tv_title), "title_1");
                        Pair<View, String> p3 = Pair.create((View) helper.getView(R.id.tv_bottom), "tv_bottom");
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(getActivity(), p1, p2, p3);
                        startActivity(intent, options.toBundle());
                    }
                });
            }


        };
        rvList.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                flag = false;
                getData();
            }
        }, rvList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        refresh();
    }

    private void refresh() {
        flag = true;
        pageIndex = 1;
//        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        getData();
    }

    private void getData() {
        mPresenter.pHomeWelfare();
    }

    private void setData(boolean isRefresh, List<HomeWelfareBean> data) {
        pageIndex++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            mAdapter.loadMoreEnd(isRefresh);
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void createPresenter() {
        mPresenter = new PHomeWelfareImpl(mContext, this);
    }


    @Override
    public void vOnHomeWelfareSuccess(List<HomeWelfareBean> bean) {
        setData(flag, bean);
    }

    @Override
    public void vOnBannerSuccess(List<BannerBean> list) {
        if (nvBanner != null && list != null) {
            nvBanner.addNotice(list);
            nvBanner.startFlipping();
        }
    }

    @Override
    public void vOnHomeWwlafareFail(String fail) {

    }

    @Override
    public void vOnBannerFail(String fail) {

    }


}