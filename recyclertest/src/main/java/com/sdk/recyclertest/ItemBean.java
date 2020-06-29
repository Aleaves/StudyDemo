package com.sdk.recyclertest;

public class ItemBean {

    private int viewType;

    private String title;

    public ItemBean(int viewType, String title) {
        this.viewType = viewType;
        this.title = title;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
