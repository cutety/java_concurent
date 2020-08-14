package com.github.cutety.philosopher;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;

public class Philosopher implements Runnable {
    private final Object left;
    private final Object right;

    public Philosopher(Object left, Object right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (left) {
                synchronized (right) {
                    eat();
                    Sleeper.sleep(1);
                }
            }
        }
    }

    public void eat() {
        LogUtil.log.debug("Yummy...");
    }
}
