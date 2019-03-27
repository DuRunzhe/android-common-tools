package com.pull2me.android.tool.thread;

import android.util.Log;

import com.pull2me.android.app.AppUtils;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 下面都假设任务队列没有大小限制：
 * <p>
 * 如果线程数量<=核心线程数量，那么直接启动一个新的核心线程来执行任务，不会放入队列中。
 * 如果线程数量>核心线程数，但<=最大线程数，并且任务队列是LinkedBlockingDeque的时候，超过核心线程数量的任务会放在任务队列中排队。
 * 如果线程数量>核心线程数，但<=最大线程数，并且任务队列是SynchronousQueue的时候，线程池会创建新线程执行任务，这些任务也不会被放在任务队列中。这些线程属于非核心线程，在任务完成后，闲置时间达到了超时时间就会被清除。
 * 如果线程数量>核心线程数，并且>最大线程数，当任务队列是LinkedBlockingDeque，会将超过核心线程的任务放在任务队列中排队。也就是当任务队列是LinkedBlockingDeque并且没有大小限制时，线程池的最大线程数设置是无效的，也就是并发的线程数最多不会超过核心线程数。
 * 如果线程数量>核心线程数，并且>最大线程数，当任务队列是SynchronousQueue的时候，会因为线程池拒绝添加任务而抛出异常。
 * 任务队列大小有限时
 * <p>
 * 当LinkedBlockingDeque塞满时，新增的任务会直接创建新线程来执行，当创建的线程数量超过最大线程数量时会抛异常。
 * SynchronousQueue没有数量限制。因为他根本不保持这些任务，而是直接交给线程池去执行。当任务数量超过最大线程数时会直接抛异常。
 * 总结：
 * 1.任务数量少于corePoolSize时，一定创建新的的核心线程来执行任务
 * 2.任务数量达到corePoolSize后，任务会被放入任务队列。
 * 3.任务队列也放满之后，在正在运行的任务线程数量小于maxPoolSize之前，仍会创建新的线程执行任务。
 * 4.一旦线程数量超过最大值maximumPoolSize之后，再调用excute方法添加任务时就会抛出异常。
 * 特殊情况：
 * 1.当人物阻塞队列是LinkedBlockingQueue且队列大小没有限制时，线程数达到corePoolSize之后，都会添加到任务阻塞队列，而不会再创建新的线程。
 * 2.SynchronousQueue不保持任务，所以如果此种情况下的任务队列是SynchronousQueue，线程数达到maximumPoolSize之前同上，
 * 达到maximumPoolSize之后，会抛异常。
 * <p>
 * Created by Administrator on 2017/2/19 0019.
 */

public class DefaultThreadPool {
    private DefaultThreadPool() {
        Log.d("debug", "- - -corePoolSize:" + corePoolSize + ", BLOCKING_QUEUE_SIZE" + BLOCKING_QUEUE_SIZE);
    }

    /**
     * 阻塞队列最大任务数量
     */
    static final int BLOCKING_QUEUE_SIZE = 256;

    /**
     * //核心线程数量。
     */
    static final int corePoolSize = AppUtils.getCpuCoreNumber() + 1;
    /**
     * //线程池中线程的最大数量，包括在队列里面的。
     */
    static final int maximumPoolSize = corePoolSize * 2;
    /**
     * //当线程数量超过核心线程数量之后，非核心空闲线程关闭前等待时间
     */
    static final long keepAlive = 10L;
    /**
     * 超出 corePoolSize 大小后，线程空闲的时间到达给定时间后将会关闭的时间单位
     */
    static final TimeUnit unit = TimeUnit.SECONDS;
    static final ArrayBlockingQueue<Runnable> requestQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_SIZE);
    static AbstractExecutorService threadPool = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAlive,
            unit,
            requestQueue,
            new ThreadPoolExecutor.DiscardOldestPolicy()//失败关闭最旧的任务任务（一般倒数队列长度个任务最终还会被执行）
//            new ThreadPoolExecutor.CallerRunsPolicy()//失败自动重试当前任务（这样重试会导致在主线程执行任务然后异常）
    );

    public static DefaultThreadPool getInstance() {
        Log.i("debug", "核心线程的最大数量：corePoolSize=" + corePoolSize
                + " 线程池中并发线程的最大数量:maximumPoolSize=" + maximumPoolSize
                + " 阻塞队列最大任务数量:BLOCKING_QUEUE_SIZE=" + BLOCKING_QUEUE_SIZE);
        return PoolHolder.instance;
    }

    /**
     * 等待任务队列的任务执行结束后关闭
     */
    public void shutDownAfterExecuted() {
        if (!threadPool.isShutdown()) {
            threadPool.shutdown();
        }
    }

    /**
     * 关闭，立即关闭，并挂起所有正在执行的线程，不接受新任务
     */
    public void shutdownRightnow() {
        if (threadPool != null) {
            DefaultThreadPool.threadPool.shutdownNow();
            try {
                // 设置超时极短，强制关闭所有任务
                threadPool.awaitTermination(1,
                        TimeUnit.MICROSECONDS);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void execute(Runnable runnable) {
        if (runnable != null) {
            try {
                threadPool.execute(runnable);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("debug", "- - - - - - - -执行线程异常：" + e.getMessage());
            }
        }
    }

    private static class PoolHolder {
        private static final DefaultThreadPool instance = new DefaultThreadPool();
    }
}
