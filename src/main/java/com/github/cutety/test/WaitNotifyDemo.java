package com.github.cutety.test;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;

import static com.github.cutety.utils.LogUtil.*;

public class WaitNotifyDemo {
    public static final Object obj = new Object();

    public static void main(String[] args) {
        Thread  t1 = new Thread( () -> {
            synchronized (obj){
                log.debug("oops...轮到我一号执行了");
                try {
                    obj.wait(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("我一号又回来了！");

            }
        });

        Thread t2 = new Thread( () -> {
            synchronized (obj){
                log.debug("oops...轮到我二号执行了");
                try {
                    obj.wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("我二号又回来了！");

            }
        });
        t1.start();
        t2.start();
        Sleeper.sleep(2);
        log.debug("随意抽一个幸运观众，让他起床");
        synchronized (obj) {
            obj.notify();
        }
    }
}
