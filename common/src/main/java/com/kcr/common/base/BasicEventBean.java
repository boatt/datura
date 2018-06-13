package com.kcr.common.base;

public abstract class BasicEventBean {
    public int eventType = 0;

    public BasicEventBean(int type) {
        eventType = type;
    }
}
