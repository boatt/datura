package com.kcr.main.bean;

public class HomeWelfareBean {
    int image;
    String title;
    String desc;

    public HomeWelfareBean() {
        super();
    }

    public HomeWelfareBean(int image, String title, String desc) {
        super();
        this.image = image;
        this.title = title;
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
