package com.github.cutety.test;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;

public class DealLockDemo extends BigRoom{
    public static void main(String[] args) {
        BigRoom bigRoom = new DealLockDemo();
        new Thread(bigRoom::study,"t1").start();
        new Thread(bigRoom::rest,"t2").start();
    }

    private final Object studyRoom = new Object();
    private final Object restRoom = new Object();
    @Override
    public void study() {
        synchronized (studyRoom) {
            LogUtil.log.debug("学习");
            Sleeper.sleep(1);
            synchronized (restRoom) {
                LogUtil.log.debug("休息");
            }
        }
    }
    @Override
    public void rest() {
        synchronized (restRoom) {
            LogUtil.log.debug("休息");
            synchronized (studyRoom) {
                LogUtil.log.debug("学习");
            }
        }
    }
}
