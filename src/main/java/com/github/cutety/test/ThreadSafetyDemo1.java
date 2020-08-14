package com.github.cutety.test;

import com.github.cutety.utils.LogUtil;

public class ThreadSafetyDemo1 {
    static int number = 0;
    static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    number++;
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock){
                    number--;
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        LogUtil.log.debug("{}",number);
    }
}
