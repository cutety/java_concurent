package com.github.cutety.test;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInterruptable {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                LogUtil.log.debug("t1线程尝试获得锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                LogUtil.log.debug("被打断了,返回");
                e.printStackTrace();
                return;
            }
            lock.lock();
            try {
                LogUtil.log.debug("获得到锁");
            } finally {
                lock.unlock();
            }
        },"t1");

        lock.lock();
        LogUtil.log.debug("主线程获得锁");
        t1.start();
        Sleeper.sleep(2); //请求2s资源
        lock.unlock();
        LogUtil.log.debug("打断{}线程",t1.getName());
        t1.interrupt(); //2s过后打断


    }
}
