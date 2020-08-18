package com.github.cutety.test;

import com.github.cutety.utils.Sleeper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitSignal extends ReentrantLock {
    private  int loopTimes;

    public AwaitSignal(int loopTimes) {
        this.loopTimes = loopTimes;
    }

    public void print(String content, Condition current, Condition next) {
        for (int i = 0; i < loopTimes; i++) {
            lock();
            try {
                try {
                    current.await();
                    System.out.print(content);
                    next.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                unlock();
            }
        }
    }
    public static void main(String[] args) {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition restRoom1 = awaitSignal.newCondition();
        Condition restRoom2 = awaitSignal.newCondition();
        Condition restRoom3 = awaitSignal.newCondition();
        new Thread(() -> {
            awaitSignal.print("a",restRoom1,restRoom2);
        }).start();
        new Thread(() -> {
            awaitSignal.print("b",restRoom2,restRoom3);
        }).start();new Thread(() -> {
            awaitSignal.print("c",restRoom3,restRoom1);
        }).start();
        Sleeper.sleep(1);
        awaitSignal.lock();
        try {
            restRoom1.signal();
        } finally {
            awaitSignal.unlock();
        }

    }
}

