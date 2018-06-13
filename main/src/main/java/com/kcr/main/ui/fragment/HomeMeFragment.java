package com.kcr.main.ui.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kcr.common.base.BasicFragment;
import com.kcr.common.util.layoutmanager.layoutmanagergroup.skidright.SkidRightLayoutManager;
import com.kcr.main.R;
import com.kcr.main.bean.HomeMeBean;
import com.kcr.main.ui.activity.CapitalDetailedActivity;
import com.kcr.main.ui.activity.CapitalDetailedFormActivity;
import com.kcr.main.ui.activity.CoinDetailedActivity;
import com.kcr.main.ui.activity.GroundActivity;
import com.kcr.main.ui.activity.MedalActivity;
import com.kcr.main.ui.activity.setting.SettingActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

public class HomeMeFragment extends BasicFragment {

    RecyclerView rvList;
    ImageView ivbg;
    private int mType;
    private boolean flag;
    private boolean isSearch;
    private int pageIndex = 1;
    private int PAGE_SIZE = 10;
    private BaseQuickAdapter mAdapter;

    public static HomeMeFragment newInstance(int type) {
        HomeMeFragment fragment = new HomeMeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_me;
    }

    @Override
    protected void initData() {

        mType = getArguments().getInt("type");
        ivbg = mRootView.findViewById(R.id.iv_bg);
        rvList = mRootView.findViewById(R.id.rv_list);
        mRootView.findViewById(R.id.rl_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build("/login/loginActivity")
                        .navigation();

            }
        });

//        Glide.with(ivbg.getContext()).load(R.mipmap.skid_right_5).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivbg);
        initAdapter();
    }


    private void initAdapter() {//BaseQuickAdapter
        rvList.setLayoutManager(new GridLayoutManager(mContext, 3));

        mAdapter = new BaseQuickAdapter<HomeMeBean, BaseViewHolder>(R.layout.item_home_me) {
            @Override
            protected void convert(BaseViewHolder helper, HomeMeBean item) {
                helper.setText(R.id.tv_title, item.getTitle());
                ImageView ivIcon = helper.getView(R.id.iv_icon);
                Glide.with(ivIcon.getContext()).load(item.getImage()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivIcon);
                if (item.getTitle().equals("捐物品")) {
                    TextView title = helper.getView(R.id.tv_title);
                    title.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showToast("未开放");
                        }
                    });
                }
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
                switch (position) {
                    case 0:
                        jumpActivity(CapitalDetailedFormActivity.class);
                        break;
                    case 1:
                        jumpActivity(MedalActivity.class);
                        break;
                    case 2:
                        jumpActivity(CoinDetailedActivity.class);
                        break;
                    case 3:
                        jumpActivity("/news/newsActivity");
                        break;
                    case 4:
                        jumpActivity("/shop/shopActivity");

                        break;
                    case 5:
//                        jumpActivity("/news/shopActivity");
                        break;
                    case 6:
                        jumpActivity(GroundActivity.class);
                        break;
                    case 7:
                        jumpActivity(SettingActivity.class);
                        break;
                }
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
//        String route;
//        if (mType == 0) {
//            route = WebApi.Person.ORDERALLNO;
//        } else {
//            route = WebApi.Person.ORDERALLOK;
//        }
//        RequestPackage.PersonPackage.orderall(mContext, route, pageIndex, new JsonCallBackWrapper(this, true) {
//            @Override
//            public void onSuccess(String response) {
//                srlRefresh.setRefreshing(false);
//                if (StringUtils.isEmpty(response)) return;
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    String json = jsonObject.getString("data");
//                    Type type = new TypeToken<ArrayList<OrderBean>>() {
//                    }.getType();
//                    ArrayList<OrderBean> data = GsonUtils.fromJson(json, type);
//                    setData(flag, data);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        ArrayList<HomeMeBean> data = new ArrayList<>();
        data.add(new HomeMeBean(R.mipmap.icon_main_detailed, "捐助明细"));
        data.add(new HomeMeBean(R.mipmap.icon_main_medal, "徽章"));
        data.add(new HomeMeBean(R.mipmap.icon_main_coin, "花币"));
        data.add(new HomeMeBean(R.mipmap.icon_main_news, "新闻"));
        data.add(new HomeMeBean(R.mipmap.icon_main_store, "商城"));
        data.add(new HomeMeBean(R.mipmap.icon_main_goods, "捐物品"));
        data.add(new HomeMeBean(R.mipmap.icon_main_activity, "线下活动"));
        data.add(new HomeMeBean(R.mipmap.icon_main_setting, "设置"));
        setData(flag, data);
    }

    private void setData(boolean isRefresh, List<HomeMeBean> data) {
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