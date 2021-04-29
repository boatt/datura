package com.damai.chatroom.bean;

import com.dtr.network.bean.LoginUserBean;

import java.io.Serializable;

public class BaseMessage implements Serializable {
    /**
     * 会话类型
     */
    public int categoryId;
    public int type;
    public String targetId;
    public String sendUserId;
    public long sendTime;
    public LoginUserBean sendUser;
    //重发次数
    public int resendTime;
    //是否阅读
    private boolean isRead;
    //消息的唯一标识
    public String msgIdentifier;

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public int getResendTime() {
        return resendTime;
    }

    public void setResendTime(int resendTime) {
        this.resendTime = resendTime;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }


    public LoginUserBean getSendUser() {
        return sendUser;
    }

    public void setSendUser(LoginUserBean sendUser) {
        this.sendUser = sendUser;
    }

    public void addResendTime() {
        this.resendTime++;
    }

    public String getMsgIdentifier() {
        return msgIdentifier;
    }

    public void setMsgIdentifier(String msgIdentifier) {
        this.msgIdentifier = msgIdentifier;
    }
}
