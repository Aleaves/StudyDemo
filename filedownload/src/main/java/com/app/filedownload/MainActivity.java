package com.app.filedownload;

import android.app.DownloadManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void download1(View view) {

        String path = getExternalCacheDir().getAbsolutePath();
        String filePath = path + "/a.apk";

        File file = new File(filePath);

        DownloadTask task = new DownloadTask.Builder("https://imtt.dd.qq.com/16891/apk/E3BF46DA1270ECA18762DD8AB1B01D9E.apk", file)
                .setMinIntervalMillisCallbackProcess(100)
                .setPassIfAlreadyCompleted(false)
                .build();

        task.enqueue(new DownloadListener3() {
            @Override
            protected void started(@NonNull DownloadTask task) {
                Log.i("============", "started");
            }

            @Override
            protected void completed(@NonNull DownloadTask task) {
                Log.i("============", "completed");
            }

            @Override
            protected void canceled(@NonNull DownloadTask task) {

            }

            @Override
            protected void error(@NonNull DownloadTask task, @NonNull Exception e) {

            }

            @Override
            protected void warn(@NonNull DownloadTask task) {

            }

            @Override
            public void retry(@NonNull DownloadTask task, @NonNull ResumeFailedCause cause) {

            }

            @Override
            public void connected(@NonNull DownloadTask task, int blockCount, long currentOffset, long totalLength) {

            }

            @Override
            public void progress(@NonNull DownloadTask task, long currentOffset, long totalLength) {
                Log.i("============", task.getFilename() + "===" + currentOffset + "===" + totalLength);
            }
        });

    }

    public void download2(View view) {

        String path = getExternalCacheDir().getAbsolutePath();
        String filePath = path + "/b.apk";

        File file = new File(filePath);

        DownloadTask task = new DownloadTask.Builder("https://imtt.dd.qq.com/16891/apk/181B12E390A0553052FE97DF0DF09F60.apk", file)
                .setMinIntervalMillisCallbackProcess(100)
                .setPassIfAlreadyCompleted(false)
                .build();

        task.enqueue(new DownloadListener3() {
            @Override
            protected void started(@NonNull DownloadTask task) {
                Log.i("============", "started");
            }

            @Override
            protected void completed(@NonNull DownloadTask task) {
                Log.i("============", "completed");
            }

            @Override
            protected void canceled(@NonNull DownloadTask task) {

            }

            @Override
            protected void error(@NonNull DownloadTask task, @NonNull Exception e) {

            }

            @Override
            protected void warn(@NonNull DownloadTask task) {

            }

            @Override
            public void retry(@NonNull DownloadTask task, @NonNull ResumeFailedCause cause) {

            }

            @Override
            public void connected(@NonNull DownloadTask task, int blockCount, long currentOffset, long totalLength) {

            }

            @Override
            public void progress(@NonNull DownloadTask task, long currentOffset, long totalLength) {
                Log.i("============", task.getFilename() + "===" + currentOffset + "===" + totalLength);
            }
        });
    }
}
