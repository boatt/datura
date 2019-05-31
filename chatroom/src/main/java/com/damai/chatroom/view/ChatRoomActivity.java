package com.damai.chatroom.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.damai.chatroom.R;
import com.damai.chatroom.WebSocketClient;
import com.damai.chatroom.adapter.MultipleItemQuickAdapter;
import com.damai.chatroom.bean.ChatRoomMessageBean;
import com.damai.chatroom.utils.MessageUtils;
import com.kcr.common.base.BasicActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/chat/chatActivity")
public class ChatRoomActivity extends BasicActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private EditText mEtContent;
    private Button mBtSend;
    private List<ChatRoomMessageBean> mMessageDatas;
    private MultipleItemQuickAdapter mMessageAdapter;
    private String roomId = "10000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
        EventBus.getDefault().register(this);
        WebSocketClient.init();
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerView = findViewById(R.id.id_recyclerview);
        mMessageDatas = new ArrayList<>();
        mRecyclerView.setLayoutManager(mLinearLayoutManager = new LinearLayoutManager(this));

        mMessageAdapter = new MultipleItemQuickAdapter(mContext, mMessageDatas);
        mRecyclerView.setAdapter(mMessageAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onJump(ChatRoomMessageBean messageEvent) {
        addMessage(messageEvent);
    }

    private void addMessage(ChatRoomMessageBean messageEvent) {
        mMessageDatas.add(messageEvent);
        mMessageAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(mMessageDatas.size() - 1);
    }

    private void initView() {
        mEtContent = (EditText) findViewById(R.id.et_content);
        mBtSend = (Button) findViewById(R.id.bt_send);
        mBtSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_send) {
            String content = mEtContent.getText().toString().trim();
            if (!TextUtils.isEmpty(content)) {
                mEtContent.setText("");
                ChatRoomMessageBean message = MessageUtils.sendTextMessage(content,roomId);
                addMessage(message);
            }
        } else {
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        WebSocketClient.getmWebSocketClient().close();
        super.onDestroy();
    }
}
