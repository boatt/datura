package com.kcr.main.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kcr.common.base.BasicActivity;
import com.kcr.main.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/21/021.
 */

public class CoinDetailedActivity extends BasicActivity {
    RecyclerView rvList;
    SwipeRefreshLayout srlRefresh;
    TextView tvBalance;
    private int mType;
    private boolean flag;
    private boolean isSearch;
    private int pageIndex = 1;
    private int PAGE_SIZE = 10;
    private BaseQuickAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detailed);
        ButterKnife.bind(this);
        rvList=  findViewById(R.id.rv_list);
        srlRefresh=  findViewById(R.id.srl_refresh);
        tvBalance=  findViewById(R.id.tv_balance);
        initAdapter();
    }


    private void initAdapter() {//BaseQuickAdapter
        rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_coin_detailed) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
//                helper.setText(R.id.tv_time, TimeUtils.date2String(new Date(item.getTime())));
//                helper.setText(R.id.tv_amount, item.getMoney());
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
        srlRefresh.setColorSchemeResources(R.color.md_divider_black);
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
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
        List<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        setData(flag,data);
    }

    private void setData(boolean isRefresh, List<String> data) {
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
