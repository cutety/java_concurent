package com.github.cutety.test;

import com.github.cutety.utils.Sleeper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.github.cutety.utils.LogUtil.log;

public class CigaretteInTheRoom2 {
    static final ReentrantLock room = new ReentrantLock();
    static boolean hasCigarette = false;
    static boolean hasTakeOut = false;
    static Condition smokingRoom = room.newCondition();
    static Condition restRoom = room.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            room.lock();
            try {
                log.debug("有烟没[{}]", hasCigarette);
                while (!hasCigarette) {
                    try {
                        log.debug("没烟先歇会");
                        smokingRoom.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟了开始干活");
            } finally {
                room.unlock();
            }
        }, "小南").start();

        new Thread(() -> {
            room.lock();
            try {
                log.debug("外卖到了吗[{}]",hasTakeOut);
                while (!hasTakeOut) {
                    log.debug("等吃完外卖再干活");
                    try {
                        restRoom.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("饭到了吃完就干活");
            } finally {
                room.unlock();
            }
        }, "小女").start();


        Sleeper.sleep(2);
        new Thread(() -> {
            room.lock();
            try {
                hasCigarette = true;
                smokingRoom.signal();
                log.debug("给小南送过烟了让小南继续排队");
            } finally {
                room.unlock();
            }
        }, "送烟人").start();

        new Thread(() -> {
            room.lock();
            try {
                hasTakeOut = true;
                restRoom.signal();
                log.debug("给小女送过外卖了，请给我五星好评");
            } finally {
                room.unlock();
            }
        }, "外卖小哥").start();
    }
}
