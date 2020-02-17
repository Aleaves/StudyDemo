package com.app.glidedemo.resource;

import android.graphics.Bitmap;
import android.util.Log;

import com.app.glidedemo.Tool;

public class Value {

    private static final String TAG = Value.class.getSimpleName();

    private static Value value;

    public static Value getInstance(){
        if(null == value){
            synchronized (Value.class){
                if(null == value){
                    value = new Value();
                }
            }
        }
        return value;
    }


    private Bitmap mBitmap;

    private int count;

    private ValueCallback callback;

    private String key;

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ValueCallback getCallback() {
        return callback;
    }

    public void setCallback(ValueCallback callback) {
        this.callback = callback;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void useAction(){

        Tool.checkNotEmpty(mBitmap);

        if(mBitmap.isRecycled()){
            Log.d(TAG, "useAction: 已经被回收了");
            return;
        }

        Log.d(TAG, "useAction: 加一 count:" + count);

        count ++;

    }

    public void nonUseAction(){
        if (count -- <= 0 && callback != null) {
            // 回调告诉外界，不再使用了
            callback.valueNonUseListener(key, this);
        }
        Log.d(TAG, "useAction: 减一 count:" + count);
    }

    public void recycleBitmap(){
        if(count >0){
            Log.d(TAG, "recycleBitmap: 引用计数大于0，证明还在使用中，不能去释放...");
            return;
        }
        if(mBitmap.isRecycled()){
            Log.d(TAG, "recycleBitmap: mBitmap.isRecycled() 已经被释放了...");
            return;
        }
        mBitmap.recycle();

        value = null;

        System.gc();

    }

}
