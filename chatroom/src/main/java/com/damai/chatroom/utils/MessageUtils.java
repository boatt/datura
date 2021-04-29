package com.damai.chatroom.utils;

import com.damai.chatroom.WebSocketClient;
import com.damai.chatroom.bean.ChatRoomMessageBean;
import com.kcr.common.constant.Constants;
import com.kcr.common.util.AppCache;
import com.kcr.common.util.GsonUtils;


public class MessageUtils {
    public static ChatRoomMessageBean sendTextMessage(String content, String roomId) {

        ChatRoomMessageBean message = new ChatRoomMessageBean();
        message.setTargetId(roomId);
        message.setContent(content);

        message.setCategoryId(1);
        wap(message);

        WebSocketClient.getmWebSocketClient().sendNewMessage(GsonUtils.toJson(message));
        return message;
    }

    /**
     * 发送端固定参数
     * @param message
     */
    private static void wap(ChatRoomMessageBean message) {
        message.setSendTime(System.currentTimeMillis());
        message.setOrientation(Constants.GRAVITY_RIGHT);
        message.setSendUser(AppCache.getUser());
        message.setSendUserId(AppCache.getUserId());
    }
}
