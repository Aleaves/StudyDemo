package com.app.glidedemo.g;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.app.glidedemo.Tool;
import com.app.glidedemo.cache.ActiveCache;
import com.app.glidedemo.cache.MemoryCache;
import com.app.glidedemo.cache.MemoryCacheCallback;
import com.app.glidedemo.cache.disk.DiskLruCacheImpl;
import com.app.glidedemo.fragment.LifecycleCallback;
import com.app.glidedemo.load_data.LoadDataManager;
import com.app.glidedemo.load_data.ResponseListener;
import com.app.glidedemo.resource.Key;
import com.app.glidedemo.resource.Value;
import com.app.glidedemo.resource.ValueCallback;

public class RequestTargetEngine implements LifecycleCallback, ValueCallback, MemoryCacheCallback, ResponseListener {

    private final String TAG = RequestTargetEngine.class.getSimpleName();

    @Override
    public void glideInitAction() {
        Log.d(TAG, "glideInitAction: Glide生命周期之 已经开启了 初始化了....");
    }

    @Override
    public void glideStopAction() {
        Log.d(TAG, "glideInitAction: Glide生命周期之 已经停止中 ....");
    }

    @Override
    public void glideRecycleAction() {
        Log.d(TAG, "glideInitAction: Glide生命周期之 进行释放操作 缓存策略释放操作等 >>>>>> ....");
        if(activeCache != null){
            activeCache.closeThread();
        }

    }

    private ActiveCache activeCache;
    private MemoryCache memoryCache;
    private DiskLruCacheImpl diskLruCache;

    private final int MEMORY_MAX_SIZE = 1024 * 1024 * 60;

    public RequestTargetEngine() {
        if (activeCache == null) {
            activeCache = new ActiveCache(this);
        }
        if (memoryCache == null) {
            memoryCache = new MemoryCache(MEMORY_MAX_SIZE);
            memoryCache.setMemoryCacheCallback(this);
        }
        diskLruCache = new DiskLruCacheImpl();

    }


    private String path;
    private Context glideContext;
    private String key; // ac037ea49e34257dc5577d1796bb137dbaddc0e42a9dff051beee8ea457a4668
    private ImageView imageView; // 显示的目标

    public void loadValueInitAction(String path, Context glideContext) {
        this.path = path;
        this.glideContext = glideContext;
        key = new Key(path).getKey();
    }

    public void into(ImageView imageView) {
        this.imageView = imageView;
        Tool.checkNotEmpty(imageView);
        Tool.assertMainThread();

        Value value = cacheAction();

        if (null != value) {
            value.nonUseAction();
            imageView.setImageBitmap(value.getmBitmap());
        }
    }

    private Value cacheAction() {
        Value value = activeCache.get(key);
        if (null != value) {
            Log.d(TAG, "cacheAction: 本次加载是在(活动缓存)中获取的资源>>>");
            value.useAction();
            return value;
        }

        value = memoryCache.get(key);

        if (null != value) {
            memoryCache.shoudonRemove(key);
            activeCache.put(key, value);
            Log.d(TAG, "cacheAction: 本次加载是在(内存缓存)中获取的资源>>>");
            value.useAction();
            return value;
        }

        value = diskLruCache.get(key);

        if (null != value) {
            activeCache.put(key, value);
            memoryCache.put(key, value);

            Log.d(TAG, "cacheAction: 本次加载是在(磁盘缓存)中获取的资源>>>");

            // 返回 代表 使用了一次 Value
            value.useAction(); // 使用了一次 加一
            return value;
        }

        value = new LoadDataManager().loadResource(path, this, glideContext);
        if (value != null)
            return value;

        return null;


    }


    @Override
    public void valueNonUseListener(String key, Value value) {
        // 把活动缓存操作的Value资源 加入到 内存缓存
        if (key != null && value != null) {
            memoryCache.put(key, value);
        }
    }

    @Override
    public void entryRemovedMemoryCache(String key, Value oldValue) {

    }

    @Override
    public void responseSuccess(Value value) {
        if (null != value) {
            saveCache(key,value);
            imageView.setImageBitmap(value.getmBitmap());
        }
    }

    @Override
    public void responseException(Exception e) {
        Log.d(TAG, "responseException: 加载外部资源失败 e:" + e.getMessage());
    }

    private void saveCache(String key, Value value) {
        value.setKey(key);
        if (diskLruCache != null) {
            diskLruCache.put(key, value);
        }
    }

}
