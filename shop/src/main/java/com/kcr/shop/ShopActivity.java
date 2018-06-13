package com.kcr.shop;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kcr.common.base.BasicActivity;
import com.kcr.common.widget.AdImageViewVersion1;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/shop/shopActivity")
public class ShopActivity extends BasicActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        mRecyclerView = findViewById(R.id.id_recyclerview);

        final List<String> mockDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mockDatas.add(i + "");
        }
        final int[] imgs = {R.mipmap.img_shop_01, R.mipmap.img_shop_02, R.mipmap.img_shop_03};
        mRecyclerView.setLayoutManager(mLinearLayoutManager = new GridLayoutManager(this, 2));

        mRecyclerView.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_goods, mockDatas) {
            int temp = 0;

            @Override
            protected void convert(BaseViewHolder holder, String item) {
                temp++;
                ImageView img = holder.getView(R.id.iv_goodImg);
                loadImage(img, imgs[temp % 3]);
            }

        });

    }
}
