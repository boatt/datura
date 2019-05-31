package com.damai.chatroom;


import android.text.TextUtils;

import com.damai.chatroom.bean.ChatRoomMessageBean;
import com.kcr.common.constant.Constants;
import com.kcr.common.util.AppCache;
import com.kcr.common.util.GsonUtils;
import com.kcr.common.util.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;

public class WebSocketClient extends org.java_websocket.client.WebSocketClient {
    private static WebSocketClient mWebSocketClient;
    private static Queue<String> mMessagePool = new LinkedList<String>();
    //    public static final String ADDRESS = "ws://47.92.69.201:8033/chat";
    public static final String ADDRESS = "ws://172.16.90.87:80";

    public WebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public void sendNewMessage(String newMessage) {
        mMessagePool.add(newMessage);
        if (mWebSocketClient.isOpen()) {
            try {
                String poll = mMessagePool.poll();
                LogUtils.d("发送消息: " + poll);
                send(poll);
            } catch (WebsocketNotConnectedException e) {
                e.printStackTrace();
            }
        } else {
            init();
        }
    }

    public static WebSocketClient getmWebSocketClient() {
        return mWebSocketClient;
    }


    public static WebSocketClient init() {
        try {
            URI uri = new URI(ADDRESS);
            mWebSocketClient = new WebSocketClient(uri);
            mWebSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return mWebSocketClient;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        LogUtils.d("连接聊天服务: ");
//        String data = SocketDataGenerateUtil.generateLoginData();
//        send(data);
//        Log.i(TAG, "onOpen: "+data);
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        super.onMessage(bytes);
        LogUtils.d("收到消息ByteBuffer: " + bytes);
    }

    @Override
    public void onMessage(String data) {
        LogUtils.d("收到消息String: " + data);
        if (!mMessagePool.isEmpty()) {
            send(mMessagePool.poll());
        }
        ChatRoomMessageBean message = GsonUtils.parseJsonToBean(data, ChatRoomMessageBean.class);

        if (message != null && !TextUtils.equals(message.getSendUserId(), AppCache.getUserId())) {
            message.setOrientation(Constants.GRAVITY_LEFT);
            EventBus.getDefault().post(message);
        }

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        LogUtils.i("连接关闭: ");

    }

    @Override
    public void onError(Exception e) {
        LogUtils.i("连接错误: ");
    }
}
