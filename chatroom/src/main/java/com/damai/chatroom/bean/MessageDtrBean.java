package com.damai.chatroom.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MessageDtrBean implements MultiItemEntity {
    public static final int GRAVITY_LEFT = 1;
    public static final int GRAVITY_RIGHT = 2;
    int type;
    String userid;
    String userHead;
    String toUserid;
    String toUserHead;
    String content;
    String desc;
    long time;
    String attachment;//附件

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getToUserid() {
        return toUserid;
    }

    public void setToUserid(String toUserid) {
        this.toUserid = toUserid;
    }

    public String getToUserHead() {
        return toUserHead;
    }

    public void setToUserHead(String toUserHead) {
        this.toUserHead = toUserHead;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Override
    public int getItemType() {
        return GRAVITY_RIGHT;
    }
}
