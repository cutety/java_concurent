package com.github.cutety.philosopher;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;

import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {
    private final ReentrantLock left;
    private final ReentrantLock right;

    public Philosopher(ReentrantLock left, ReentrantLock right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while(true) {
            if(left.tryLock()) {
                try {
                    if(right.tryLock()) {
                        try {
                            eat();
                        }
                        finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock();
                }
            }
        }
    }

    public void eat() {
        LogUtil.log.debug("Yummy...");
    }
}
