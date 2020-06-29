package com.app.filedownload;

import android.app.Application;
import android.os.Debug;
import android.util.Log;

import java.io.File;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        File file = new File(getExternalCacheDir(),"app.trace");
        Log.i("===========",file.getAbsolutePath());
        Debug.startMethodTracing(file.getAbsolutePath());
        Debug.stopMethodTracing();
    }
}
