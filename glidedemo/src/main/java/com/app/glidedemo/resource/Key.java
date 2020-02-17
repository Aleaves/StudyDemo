package com.app.glidedemo.resource;

import com.app.glidedemo.Tool;

public class Key {

    private String key;

    public Key(String key) {
        this.key = Tool.getSHA256StrJava(key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
