package com.kcr.main.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jrummyapps.android.animations.Skill;
import com.jrummyapps.android.animations.Technique;
import com.kcr.common.base.BasicActivity;
import com.kcr.common.util.layoutmanager.layoutmanagergroup.slide.ItemConfig;
import com.kcr.common.util.layoutmanager.layoutmanagergroup.slide.ItemTouchHelperCallback;
import com.kcr.common.util.layoutmanager.layoutmanagergroup.slide.OnSlideListener;
import com.kcr.common.util.layoutmanager.layoutmanagergroup.slide.SlideLayoutManager;
import com.kcr.common.widget.SmileView;
import com.kcr.main.R;
import com.kcr.main.bean.HomeWelfareBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/21/021.
 */

public class GroundActivity extends BasicActivity {
    RecyclerView rvList;
    SmileView mSmileView;
    LinearLayout targetView;
    private int mType;
    private boolean flag;
    private boolean isSearch;
    private int pageIndex = 1;
    private int PAGE_SIZE = 10;
    private BaseQuickAdapter mAdapter;
    private int mLikeCount = 50;
    private int mDislikeCount = 50;
    ItemTouchHelperCallback mItemTouchHelperCallback;
    ItemTouchHelper mItemTouchHelper;
    SlideLayoutManager mSlideLayoutManager;
    List<HomeWelfareBean> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground);
        ButterKnife.bind(this);
        rvList=  findViewById(R.id.rv_list);
        mSmileView=  findViewById(R.id.smile_view);
        targetView=  findViewById(R.id.ll_hint);
        initView();
        initHint();

    }

    private void initHint() {
        Technique technique = Technique.values()[2];
        technique.playOn(targetView);
    }

    private void initView() {

        mSmileView.setLike(mLikeCount);
        mSmileView.setDisLike(mDislikeCount);
        mAdapter = new BaseQuickAdapter<HomeWelfareBean, BaseViewHolder>(R.layout.item_ground_activity) {
            @Override
            protected void convert(BaseViewHolder helper, HomeWelfareBean item) {
                ImageView imgBg = helper.getView(R.id.img_bg);
                Glide.with(imgBg.getContext()).load(item.getImage()).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imgBg);
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_user_say, item.getDesc());
            }
        };
        rvList.setAdapter(mAdapter);
        mItemTouchHelperCallback = new ItemTouchHelperCallback(rvList.getAdapter(), data);
        mItemTouchHelper = new ItemTouchHelper(mItemTouchHelperCallback);
        mSlideLayoutManager = new SlideLayoutManager(rvList, mItemTouchHelper);
        mItemTouchHelper.attachToRecyclerView(rvList);

        rvList.setLayoutManager(mSlideLayoutManager);

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                flag = false;
                getData();
            }
        }, rvList);
        refresh();
        initListener();
    }

    private void initListener() {
        mItemTouchHelperCallback.setOnSlideListener(new OnSlideListener() {
            @Override
            public void onSliding(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                if (direction == ItemConfig.SLIDING_LEFT) {
                } else if (direction == ItemConfig.SLIDING_RIGHT) {
                }
            }

            @Override
            public void onSlided(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                if (direction == ItemConfig.SLIDED_LEFT) {
                    mDislikeCount--;
                    mSmileView.setDisLike(mDislikeCount);
                    mSmileView.disLikeAnimation();
                } else if (direction == ItemConfig.SLIDED_RIGHT) {
                    mLikeCount++;
                    mSmileView.setLike(mLikeCount);
                    mSmileView.likeAnimation();
                }
                int position = viewHolder.getAdapterPosition();
            }

            @Override
            public void onClear() {
//                addData();
            }
        });
    }


    private void refresh() {
        flag = true;
        pageIndex = 1;
//        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        getData();
    }

    private void getData() {

        data.add(new HomeWelfareBean(R.mipmap.img_main_orphan, "贫困孤儿助养", "旨在帮助中国贫困地区6周岁以上的孤儿，为他们提供基本生活和学习补助，使他们能和其他孩子一样享有受教育的权利，得到关怀和照顾，快乐健康成长。"));
        data.add(new HomeWelfareBean(R.mipmap.img_main_book, "益童书屋", "项目旨在为贫困山区、留守儿童等一些渴望读书的孩子筹集课外读物，协助孩子所在的学校筹建起自己的阅览室，并且协助学校的老师进行阅读推广。"));
        data.add(new HomeWelfareBean(R.mipmap.skid_right_5, "贫困白内障的光明", "您的善款将用于为贫困地区的白内障患者提供治疗的手术费，通过手术可以帮助他们重见光明。"));
        data.add(new HomeWelfareBean(R.mipmap.icon_main_emptynest, "空巢不空银天使计划", "空巢老人，一个不起眼的群体，但或许其中就有你我最亲近的人。病痛的折磨只是他们生活里的插曲，无人问津的寂寞和“被遗弃”的孤独感才是最大的伤害。"));
        data.add(new HomeWelfareBean(R.mipmap.img_main_orphan, "贫困孤儿助养", "旨在帮助中国贫困地区6周岁以上的孤儿，为他们提供基本生活和学习补助，使他们能和其他孩子一样享有受教育的权利，得到关怀和照顾，快乐健康成长。"));
        data.add(new HomeWelfareBean(R.mipmap.img_main_book, "益童书屋", "项目旨在为贫困山区、留守儿童等一些渴望读书的孩子筹集课外读物，协助孩子所在的学校筹建起自己的阅览室，并且协助学校的老师进行阅读推广。"));
        data.add(new HomeWelfareBean(R.mipmap.skid_right_5, "贫困白内障的光明", "您的善款将用于为贫困地区的白内障患者提供治疗的手术费，通过手术可以帮助他们重见光明。"));
        data.add(new HomeWelfareBean(R.mipmap.icon_main_emptynest, "空巢不空银天使计划", "空巢老人，一个不起眼的群体，但或许其中就有你我最亲近的人。病痛的折磨只是他们生活里的插曲，无人问津的寂寞和“被遗弃”的孤独感才是最大的伤害。"));

        setData(flag, data);
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


}
