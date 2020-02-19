package com.app.glidedemo.g;

public class GlideBuilder {

    public Glide build(){
        RequestManagerRetriver requestManagerRetriver = new RequestManagerRetriver();
        Glide glide = new Glide(requestManagerRetriver);
        return glide;
    }

}
