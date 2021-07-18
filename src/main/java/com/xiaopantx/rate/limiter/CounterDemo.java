package com.xiaopantx.rate.limiter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数器限流
 *  有明显缺点: 时间窗临界值的地方有可能会出现突发流量
 *
 * @author xiaopantx
 */
public class CounterDemo {

    /**
     * 时间窗左边界
     */
    private static long timeStamp = System.currentTimeMillis();

    /**
     * 时间窗右边界
     */
    private static long interval = 1000;

    /**
     * 限流次数 每秒100次请求
     *
     */
    private static long limitCount = 100;

    /**
     * 时间窗内请求数量
     */
    private static long reqCount = 0;

    public static boolean grant() {
        long now = System.currentTimeMillis();
        if (now < timeStamp + interval) {
            // 一个时间窗之内
            if (reqCount < limitCount) {
                //
                reqCount++;
                return true;
            }else {
                return false;
            }
        }else { // 下一个时间窗开始
            timeStamp = System.currentTimeMillis();
            reqCount = 0;
            return false;
        }
    }

    private static AtomicInteger pass = new AtomicInteger(0);

    public static void main(String[] args) {
        for(int i = 0; i < 1000; i++) {
            new Thread(() -> {
                if (CounterDemo.grant()) {
                    System.out.println("通过");
                    pass.incrementAndGet();
                }else {
                    System.out.println("限流");
                }
            }).start();
        }

        System.out.println("------------------------------------------");

        int count = pass.get();
        System.out.println("count = " + count);
    }
}
