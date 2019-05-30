package com.kcr.main.bean;

public class HomeMeBean {
    int image;
    int position;
    String title;

    public HomeMeBean() {
        super();
    }

    public HomeMeBean(int image, String title,int position) {
        super();
        this.image = image;
        this.title = title;
        this.position = position;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
