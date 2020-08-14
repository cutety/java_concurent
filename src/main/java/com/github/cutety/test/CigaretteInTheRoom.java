package com.github.cutety.test;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;

import static com.github.cutety.utils.LogUtil.*;

public class CigaretteInTheRoom {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeOut = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (room) {
                log.debug("有烟没[{}]",hasCigarette);
                while(!hasCigarette) {
                    try {
                        log.debug("没烟先歇会");
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟了开始干活");
            }
        },"小南").start();

        new Thread(() -> {
            synchronized (room) {
                log.debug("等个外卖，好饿呀");
                while(!hasTakeOut) {
                    log.debug("等吃完外卖再干活");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("饭到了吃完就干活");
            }
        },"小女").start();


        Sleeper.sleep(2);
        new Thread(() -> {
            synchronized (room) {
                hasCigarette = true;
                room.notifyAll();
                log.debug("给小南送过烟了让小南继续排队");
            }
        },"送烟人").start();

        new Thread(() -> {
            synchronized (room) {
                hasTakeOut = true;
                room.notifyAll();
                log.debug("给小女送过外卖了，请给我五星好评");
            }
        },"外卖小哥").start();
    }
}
