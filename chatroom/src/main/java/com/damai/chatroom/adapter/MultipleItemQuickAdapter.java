package com.damai.chatroom.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.damai.chatroom.R;
import com.damai.chatroom.bean.MessageDtrBean;
import com.damai.chatroom.bean.MultipleItem;

import java.util.List;

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MessageDtrBean, BaseViewHolder> {

    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        addItemType(MultipleItem.GRAVITY_LEFT, R.layout.item_left_message);
        addItemType(MultipleItem.GRAVITY_RIGHT, R.layout.item_right_message);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageDtrBean item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.GRAVITY_LEFT:
                helper.setText(R.id.tv_message, item.getContent());
                break;
            case MultipleItem.GRAVITY_RIGHT:
                helper.setText(R.id.tv_message, item.getContent());
                break;

        }
    }

}