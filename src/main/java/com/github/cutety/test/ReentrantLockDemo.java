package com.github.cutety.test;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            method1();
        } finally {
            lock.unlock();
        }

    }

    public static void method1() {
        lock.lock();
        try {
            LogUtil.log.debug("method1 has been initialized.");
            method2();
        } finally {
            lock.unlock();
        }
    }

    public static void method2() {
        lock.lock();
        try {
            LogUtil.log.debug("entering method2...");
        } finally {
            lock.unlock();
        }
    }
}
