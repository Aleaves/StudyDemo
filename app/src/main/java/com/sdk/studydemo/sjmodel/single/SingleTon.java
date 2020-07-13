package com.sdk.studydemo.sjmodel.single;

public class SingleTon {

    private SingleTon(){}

    private static class SingleTonInstance{
        private static final SingleTon instance = new SingleTon();
    }

    public static SingleTon getInstance(){
        return SingleTonInstance.instance;
    }

}
