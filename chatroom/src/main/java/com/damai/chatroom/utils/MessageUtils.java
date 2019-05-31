package com.damai.chatroom.utils;

import com.damai.chatroom.WebSocketClient;
import com.damai.chatroom.bean.ChatRoomMessageBean;
import com.kcr.common.constant.Constants;
import com.kcr.common.util.AppCache;
import com.kcr.common.util.GsonUtils;

/**
 * Desc:
 * <p>
 *
 * @author: ZhouTao
 * Date: 2019/5/31
 * Copyright: Copyright (c) 2016-2020
 * Company: @小牛科技
 * Update
 */
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
