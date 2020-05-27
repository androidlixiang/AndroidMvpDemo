package com.common.util;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/10/21 20:00
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/10/21 20:00
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */

public class ThreadManager {

    private static volatile ThreadPool mThreadPool;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));

    public static ThreadPool getThreadPool() {
        if (mThreadPool == null) {
            synchronized (ThreadManager.class) {
                if (mThreadPool == null) {
//                    和AsyncTask设置成一样
                    mThreadPool = new ThreadPool(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS);
                }
            }
        }

        return mThreadPool;
    }

    // 线程池
    public static class ThreadPool {

        private int corePoolSize;// 核心线程数
        private int maximumPoolSize;// 最大线程数
        private long keepAliveTime;// 休息时间

        private ThreadPoolExecutor executor;

        private ThreadPool(int corePoolSize, int maximumPoolSize,
                           long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        // 线程池几个参数的理解:
        // 比如去火车站买票, 有10个售票窗口, 但只有5个窗口对外开放. 那么对外开放的5个窗口称为核心线程数,
        // 而最大线程数是10个窗口.
        // 如果5个窗口都被占用, 那么后来的人就必须在后面排队, 但后来售票厅人越来越多, 已经人满为患, 就类似于线程队列已满.
        // 这时候火车站站长下令, 把剩下的5个窗口也打开, 也就是目前已经有10个窗口同时运行. 后来又来了一批人,
        // 10个窗口也处理不过来了, 而且售票厅人已经满了, 这时候站长就下令封锁入口,不允许其他人再进来, 这就是线程异常处理策略.
        // 而线程存活时间指的是, 允许售票员休息的最长时间, 以此限制售票员偷懒的行为.
        public void execute(Runnable r) {
            if (executor == null) {
                executor = new ThreadPoolExecutor(corePoolSize,
                        maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>(),
                        Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
                // 参1:核心线程数，除非allowCoreThreadTimeOut被设置为true，否则它闲着也不会死
                // 参2:最大线程数,活动线程数量超过它，后续任务就会排队  ;
                // 参3:超时时长，作用于非核心线程（allowCoreThreadTimeOut被设置为true时也会同时作用于核心线程），闲置超时便被回收 ;
                // 参4:枚举类型，设置keepAliveTime的单位;
                // 参5:缓冲任务队列，线程池的execute方法会将Runnable对象存储起来  ;
                // 参6:线程工厂接口，只有一个new Thread(Runnable r)方法，可为线程池创建新线程  ;
                // 参7:线程异常处理策略
            }

            // 线程池执行一个Runnable对象, 具体运行时机线程池说了算
            executor.execute(r);
        }

        // 取消任务
        public void cancel(Runnable r) {
            if (executor != null) {
                // 从线程队列中移除对象
                executor.getQueue().remove(r);
            }
        }
    }
}
