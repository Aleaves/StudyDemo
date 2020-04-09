package com.sdk.studydemo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RemoteViews;
import android.widget.Toast;

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
                //alertNotify();
                alertNotify26();
                //alertNotify2();
            }
        });
        initNotify();

        handler.sendEmptyMessageDelayed(1,3000);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            alertNotify26();
        }
    };

    /**
     * 正常通知  >26则无法弹出  需要添加渠道号
     */
    private void alertNotify(){
        NotificationManager notificationManager = (NotificationManager) getSystemService
                (NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        //设置标题
        mBuilder.setContentTitle("我是标题")
                //设置内容
                .setContentText("我是内容")
                //设置大图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                //设置小图标
                .setSmallIcon(R.mipmap.ic_launcher_round)
                //设置通知时间
                .setWhen(System.currentTimeMillis())
                //首次进入时显示效果
                .setTicker("我是测试内容")
                //设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音
                .setDefaults(Notification.DEFAULT_ALL);
        //发送通知请求
        notificationManager.notify(10, mBuilder.build());
    }



    private void initNotify(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "MyCustomChannel";
            String channelName = "自定义Channel";
            int importance = NotificationManager.IMPORTANCE_MAX;
            createNotificationChannel(channelId, channelName, importance);
            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);

        }
    }

    private void alertNotify26(){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel("MyCustomChannel");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                startActivity(intent);
                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
            }
        }

        RemoteViews mRemoteViews = new RemoteViews(getPackageName(),R.layout.activity_notification);

        Notification notification = new NotificationCompat.Builder(this, "MyCustomChannel")
                .setContentTitle("正在下载...")
                //.setContentText("这是我自定义的聊天内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                // 这里需要用drawable例的图片
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))

                .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的优先级：最大
                .setProgress(100,50,false)
                .setContentText("下载进度:"+"50%")
                // 设置显示角标的数量（注：如果需要显示出来，需要长按图标）
                .setNumber(2)
                .setAutoCancel(false).build();
        //设置通知不被清除
        notification.flags |= Notification.FLAG_NO_CLEAR;
        manager.notify(1, notification);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        // 是否显示角标
        channel.setShowBadge(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }


    private void alertNotify2(){NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, "subscribe")
                .setContentTitle("收到一条订阅消息")
                .setContentText("北京市教委：明天要放假了~")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true).build();
        manager.notify(2, notification);

    }


}
