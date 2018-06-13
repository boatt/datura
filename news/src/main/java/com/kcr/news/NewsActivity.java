package com.kcr.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kcr.common.base.BasicActivity;
import com.kcr.common.widget.AdImageViewVersion1;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/news/newsActivity")
public class NewsActivity extends BasicActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mRecyclerView = findViewById(R.id.id_recyclerview);

        final List<String> mockDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mockDatas.add(i + "");
        }

        mRecyclerView.setLayoutManager(mLinearLayoutManager = new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_new, mockDatas) {
            @Override
            protected void convert(BaseViewHolder holder, String item) {
                int position = mockDatas.indexOf(item);
                if (position > 0 && position % 7 == 0) {
                    holder.setVisible(R.id.id_tv_title, false);
                    holder.setVisible(R.id.id_tv_desc, false);
                    holder.setVisible(R.id.id_time, false);
                    holder.setVisible(R.id.id_iv_ad, true);
                } else {
                    holder.setVisible(R.id.id_tv_title, true);
                    holder.setVisible(R.id.id_tv_desc, true);
                    holder.setVisible(R.id.id_time, true);
                    holder.setVisible(R.id.id_iv_ad, false);
                }
            }

        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int fPos = mLinearLayoutManager.findFirstVisibleItemPosition();
                int lPos = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                for (int i = fPos; i <= lPos; i++) {
                    View view = mLinearLayoutManager.findViewByPosition(i);
                    AdImageViewVersion1 adImageView = view.findViewById(R.id.id_iv_ad);
                    if (adImageView.getVisibility() == View.VISIBLE) {
                        adImageView.setDy(mLinearLayoutManager.getHeight() - view.getTop());
                    }
                }
            }
        });
    }
}
