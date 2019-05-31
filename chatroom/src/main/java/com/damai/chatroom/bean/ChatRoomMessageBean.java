package com.damai.chatroom.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dtr.network.bean.LoginUserBean;

/**
 * @author zhoutao
 */
public class ChatRoomMessageBean<T> extends BaseMessage implements MultiItemEntity {

    //朝向,左右
    private int orientation;
    public LoginUserBean toUser;
    private String content;
    private String desc;
    //附件
    private T attachment;


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


    public T getAttachment() {
        return attachment;
    }

    public void setAttachment(T attachment) {
        this.attachment = attachment;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public int getItemType() {
        return orientation;
    }

    public LoginUserBean getToUser() {
        return toUser;
    }

    public void setToUser(LoginUserBean toUser) {
        this.toUser = toUser;
    }
}
