package com.pull2me.android.click;

import android.os.SystemClock;

/**
 * Created by drz on 2015/12/29.
 */
public class ClickUtils {
    /* 双击事件、多击事件
    */
    //存储时间的数组
    private static long[] mHits = new long[2];
    //三击事件
    private static long[] tripleHits = new long[3];

    /**
     * 双击事件
     *
     * @param runnable 双击事件发生时执行的逻辑
     */
    public static void doubleClick(Runnable runnable) {
        // 双击事件响应
        /**
         * arraycopy,拷贝数组
         * src 要拷贝的源数组
         * srcPos 源数组开始拷贝的下标位置
         * dst 目标数组
         * dstPos 开始存放的下标位置
         * length 要拷贝的长度（元素的个数）
         *
         */
        //实现数组的移位操作，点击一次，左移一位，末尾补上当前开机时间（cpu的时间）
        if (mHits == null) {
            mHits = new long[2];
        }
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        //双击事件的时间间隔500ms
        if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
            //双击后具体的操作
            //do
            runnable.run();
        }
    }

    /**
     * 双击事件
     *
     * @param runnable 双击事件发生时执行的逻辑
     * @param unDouble 非双击事件的逻辑
     */
    public static void doubleClick(Runnable runnable, Runnable unDouble) {
        // 双击事件响应
        /**
         * arraycopy,拷贝数组
         * src 要拷贝的源数组
         * srcPos 源数组开始拷贝的下标位置
         * dst 目标数组
         * dstPos 开始存放的下标位置
         * length 要拷贝的长度（元素的个数）
         *
         */
        //实现数组的移位操作，点击一次，左移一位，末尾补上当前开机时间（cpu的时间）
        if (mHits == null) {
            mHits = new long[2];
        }
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        //双击事件的时间间隔500ms
        if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
            //双击后具体的操作
            //do
            runnable.run();
        } else {
            unDouble.run();
        }
    }

    /**
     * 双击事件
     *
     * @param interval 双击间隔时间
     * @param runnable 双击事件发生时执行的逻辑
     * @param unDouble 非双击事件的逻辑
     */
    public static void doubleClick(long interval, Runnable runnable, Runnable unDouble) {
        // 双击事件响应
        /**
         * arraycopy,拷贝数组
         * src 要拷贝的源数组
         * srcPos 源数组开始拷贝的下标位置
         * dst 目标数组
         * dstPos 开始存放的下标位置
         * length 要拷贝的长度（元素的个数）
         *
         */
        //实现数组的移位操作，点击一次，左移一位，末尾补上当前开机时间（cpu的时间）
        if (mHits == null) {
            mHits = new long[2];
        }
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        //双击事件的时间间隔intervalms
        if (mHits[0] >= (SystemClock.uptimeMillis() - interval)) {
            //双击后具体的操作
            //do
            runnable.run();
        } else {
            unDouble.run();
        }
    }

    /**
     * 三击事件
     *
     * @param runnable
     */
    public static void tribleClick(Runnable runnable) {
        if (tripleHits == null) {
            tripleHits = new long[3];
        }
        //数组移位
        System.arraycopy(tripleHits, 1, tripleHits, 0, tripleHits.length - 1);
        //填充数组最后一位
        tripleHits[tripleHits.length - 1] = SystemClock.uptimeMillis();
        //判断时间间隔
        if (SystemClock.uptimeMillis() - tripleHits[0] <= 500) {
            runnable.run();
        }
    }

    /**
     * 释放资源
     */
    public static void release() {
        mHits = null;
        tripleHits = null;
    }
}
