package com.github.cutety.test;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTryLockDemo {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
            LogUtil.log.debug("尝试获得锁");
            try {
                if(!lock.tryLock(1, TimeUnit.SECONDS)) {
                    LogUtil.log.debug("获取锁失败");
                    return;
                }
            } catch (InterruptedException e) {
                LogUtil.log.debug("获取不到所");
                e.printStackTrace();
            }
            try {
                LogUtil.log.debug("获取到锁。。");
            } finally {
                lock.unlock();
            }
        },"t1");
        lock.lock();;
        LogUtil.log.debug("主线程获得到锁");
        t1.start();
        try {
            Sleeper.sleep(0.5);
        } finally {
            lock.unlock();
        }
    }
}
