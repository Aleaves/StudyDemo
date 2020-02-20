package com.app.glidedemo.g;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.app.glidedemo.fragment.ActivityFragmentManager;
import com.app.glidedemo.fragment.FragmentActivityFragmentManager;

public class RequestManager {


    private final String FRAGMENT_ACTIVITY_NAME = "Fragment_Activity_NAME";
    private final String ACTIVITY_NAME = "activity_name";
    private final int NEXT_HANDLER_MSG = 995465;
    private Context requestManagerContext;

    private static RequestTargetEngine requestTargetEngine;

    // 构造代码块，不用再所有的构造方法里面去实例化了，统一的去写
    {
        if (requestTargetEngine == null) {
            requestTargetEngine = new RequestTargetEngine();
        }
    }


    public RequestManager(FragmentActivity fragmentActivity) {
        this.requestManagerContext = fragmentActivity;

        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        Fragment fragment = supportFragmentManager.findFragmentByTag(FRAGMENT_ACTIVITY_NAME);
        if (null == fragment) {
            fragment = new FragmentActivityFragmentManager(requestTargetEngine);
            supportFragmentManager.beginTransaction().add(fragment, FRAGMENT_ACTIVITY_NAME).commitAllowingStateLoss();
        }

        // 发送一次Handler
        mHandler.sendEmptyMessage(NEXT_HANDLER_MSG);

    }


    public RequestManager(Activity activity) {
        this.requestManagerContext = activity;

        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        android.app.Fragment fragment = fragmentManager.findFragmentByTag(ACTIVITY_NAME);
        if (null == fragment) {
            fragment = new ActivityFragmentManager(requestTargetEngine);
            fragmentManager.beginTransaction().add(fragment, ACTIVITY_NAME).commitAllowingStateLoss();
        }

        // 发送一次Handler
        mHandler.sendEmptyMessage(NEXT_HANDLER_MSG);



    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //Fragment fragment2 = fragmentActivity.getSupportFragmentManager().findFragmentByTag(FRAGMENT_ACTIVITY_NAME);
            //Log.d(TAG, "Handler: fragment2" + fragment2); // 有值 ： 不在排队中，所以有值
            return false;
        }
    });

    public RequestManager(Context context) {
        this.requestManagerContext = context;
    }

    public RequestTargetEngine load(String s){
        mHandler.removeMessages(NEXT_HANDLER_MSG);
        requestTargetEngine.loadValueInitAction(s,requestManagerContext);
        return requestTargetEngine;
    }
}
