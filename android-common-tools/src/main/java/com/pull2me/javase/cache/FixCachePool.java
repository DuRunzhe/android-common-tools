package com.pull2me.javase.cache;


import java.util.ArrayList;
import java.util.List;

/**
 * 固定大小缓存对象池
 *
 * @Author: runzhedu {durunzhe666@gmail.com}
 * @Time: 2019/4/4 下午2:50
 **/
public abstract class FixCachePool<T> implements ICacheAble<T> {
    /**
     * 缓存池大小
     */
    private int cacheSize;
    /**
     * 缓存池
     */
    private final List<T> cachePool;
    /**
     * 当前使用的缓存对象的索引编号，从0开始
     */
    private int currentIndex;

    public FixCachePool(int cacheSize) {
        if (cacheSize <= 0) {
            throw new IllegalArgumentException("cache size must above of zero!");
        }
        this.cacheSize = cacheSize;
        cachePool = new ArrayList<>(cacheSize);
    }

    @Override
    public final T getAndInitialIfAbsent() {
        if (cachePool.size() < cacheSize) {
            T instance = initialInstance();
            if (instance == null) {
                throw new IllegalArgumentException("initialInstance() can not return null!");
            }
            cachePool.add(instance);
            currentIndex++;
            return instance;
        } else {
            return cachePool.get(currentIndex++ % cacheSize);
        }
    }

    /**
     * 实例化对象
     *
     * @return
     */
    public abstract T initialInstance();

    @Override
    public final void clear() {
        cachePool.clear();
    }
}
