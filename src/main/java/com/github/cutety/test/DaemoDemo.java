package com.github.cutety.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DaemoDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        });
        //t1.setDaemon(true); //设置守护线程之后可以关闭java进程
        t1.start();
        t1.interrupt();

    }
}
