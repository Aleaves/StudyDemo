package com.app.glidedemo.cache;

import com.app.glidedemo.resource.Value;

public interface MemoryCacheCallback {

    /**
     * 内存缓存中移除的 key--value
     * @param key
     * @param oldValue
     */
    public void entryRemovedMemoryCache(String key, Value oldValue);


}
