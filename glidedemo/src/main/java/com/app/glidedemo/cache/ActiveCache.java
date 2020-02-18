package com.app.glidedemo.cache;

import com.app.glidedemo.Tool;
import com.app.glidedemo.resource.Value;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ActiveCache {

    private Map<String, WeakReference<Value>> mapList = new HashMap<>();
    private ReferenceQueue<Value> queue;
    private boolean isCloseThread;
    private Thread thread;
    private boolean isShoudonRemove;


    public void put(String key, Value value) {
        Tool.checkNotEmpty(key);
        mapList.put(key, new CustomWeakReference(value, getQueue(), key));
    }

    public Value get(String key) {
        WeakReference<Value> valueWeakReference = mapList.get(key);
        if (null != valueWeakReference) {
            return valueWeakReference.get();
        }
        return null;
    }

    public Value remove(String key) {
        isShoudonRemove = true;
        WeakReference<Value> valueWeakReference = mapList.remove(key);
        isShoudonRemove = false;
        if (null != valueWeakReference) {
            return valueWeakReference.get();
        }
        return null;
    }

    public void closeThread() {
        isCloseThread = true;
        if (null != thread) {
            thread.interrupt();
            try {
                thread.join(TimeUnit.SECONDS.toMillis(5));
                if (thread.isAlive()) { // 证明线程还是没有结束
                    throw new IllegalStateException("活动缓存中 关闭线程 线程没有停止下来...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class CustomWeakReference extends WeakReference<Value> {

        private String key;

        public CustomWeakReference(Value referent, ReferenceQueue<? super Value> q, String key) {
            super(referent, q);
            this.key = key;
        }
    }


    private ReferenceQueue<Value> getQueue() {
        if (queue == null) {
            queue = new ReferenceQueue<>();

            thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    while (!isCloseThread) {
                        try {
                            Reference<? extends Value> remove = queue.remove();
                            CustomWeakReference weakReference = (CustomWeakReference) remove;
                            if (mapList != null && !mapList.isEmpty() && !isShoudonRemove) {
                                mapList.remove(weakReference.key);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            thread.start();
        }
        return queue;
    }

}
