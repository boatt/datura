package com.damai.chatroom.bean;

import java.io.Serializable;



public class MessageUserBean implements Serializable {

    private String customerId;//用户id

    private String uid;//uid

    private String name;//用户名

    private String portrait;//用户头像

    private String headFrameUrl;//用户头像框

    private int grade;//用户等级

    private int identity;//用户身份 （0=普通观众，1=房管，5=主播，9=超管）

    private int anchorLevel;//主播等级

    private int bubbleColor; //汽包颜色

    private String fansMedalUrl;//粉丝勋章背景图

    private String fansAlias;//粉丝勋章别名


    public MessageUserBean() {
    }

    public MessageUserBean(String customerId, String uid, String name, String portrait, int grade, int identity, int anchorLevel) {
        this.customerId = customerId;
        this.uid = uid;
        this.name = name;
        this.portrait = portrait;
        this.grade = grade;
        this.identity = identity;
        this.anchorLevel = anchorLevel;
    }

    public String getCustomerId() {
        //return customerId;
        return customerId == null ? "" : customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public int getAnchorLevel() {
        return anchorLevel;
    }

    public void setAnchorLevel(int anchorLevel) {
        this.anchorLevel = anchorLevel;
    }

    public String getHeadFrameUrl() {
        return headFrameUrl;
    }

    public void setHeadFrameUrl(String headFrameUrl) {
        this.headFrameUrl = headFrameUrl;
    }

    public int getBubbleColor() {
        return bubbleColor;
    }

    public void setBubbleColor(int bubbleColor) {
        this.bubbleColor = bubbleColor;
    }

    public String getFansMedalUrl() {
        return fansMedalUrl;
    }

    public void setFansMedalUrl(String fansMedalUrl) {
        this.fansMedalUrl = fansMedalUrl;
    }

    public String getFansAlias() {
        return fansAlias;
    }

    public void setFansAlias(String fansAlias) {
        this.fansAlias = fansAlias;
    }
}
