package com.kcr.main.bean;

public class HomeMeBean {
    int image;
    String title;

    public HomeMeBean() {
        super();
    }

    public HomeMeBean(int image, String title) {
        super();
        this.image = image;
        this.title = title;
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

}
