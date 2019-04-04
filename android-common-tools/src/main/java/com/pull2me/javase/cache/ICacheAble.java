package com.pull2me.javase.cache;

/**
 * Cache Able Object
 *
 * @Author: runzhedu {durunzhe666@gmail.com}
 * @Time: 2019/4/4 下午2:45
 **/
public interface ICacheAble<T> {
    /**
     * 获取实例并且不存在就实例化
     *
     * @return
     */
    T getAndInitialIfAbsent();

    /**
     * 清空缓存对象
     */
    void clear();
}
