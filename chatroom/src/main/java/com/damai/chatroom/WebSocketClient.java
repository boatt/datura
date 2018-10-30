package com.damai.chatroom;

import android.util.Log;


import com.damai.chatroom.bean.MessageDtrBean;

import org.greenrobot.eventbus.EventBus;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;

public class WebSocketClient extends org.java_websocket.client.WebSocketClient {
    private static final String TAG = "WebSocketClient";
    private static WebSocketClient mWebSocketClient;
    private static Queue<String> mMessagePool = new LinkedList<String>();
    public static final String ADDRESS = "ws://47.92.69.201:8033/chat";

    public WebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public void sendNewMessage(String newMessage) {
        mMessagePool.add(newMessage);
        if (mWebSocketClient.isOpen()) {
            try {
                String poll = mMessagePool.poll();
                Log.d(TAG, "sendNewMessage: " + poll);
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
//        String data = SocketDataGenerateUtil.generateLoginData();
//        send(data);
//        Log.i(TAG, "onOpen: "+data);
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        super.onMessage(bytes);
        Log.i(TAG, "onMessageByteBuffer: " + bytes);
    }

    @Override
    public void onMessage(String data) {
        Log.i(TAG, "onMessageString: " + data);
        if (!mMessagePool.isEmpty()) {
            send(mMessagePool.poll());
        }
        MessageDtrBean event = new MessageDtrBean();
        event.setContent(data);
        EventBus.getDefault().post(event);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        Log.i(TAG, "onClose: ");

    }

    @Override
    public void onError(Exception e) {
        Log.i(TAG, "onError: ");
    }
}
