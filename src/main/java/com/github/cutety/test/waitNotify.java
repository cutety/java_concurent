package com.github.cutety.test;

public class waitNotify {
    private int flag;
    private final int loopNumber;

    public waitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(String content, int waitFlag, int nextFlag) {
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                while(flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(content);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        waitNotify wn = new waitNotify(1,5);
        Thread t1 = new Thread(() -> {
            wn.print("a",1,2);
        },"t1");
        Thread t2 = new Thread(() -> {
            wn.print("b",2,3);
        },"t2");
        Thread t3 = new Thread(() -> {
            wn.print("c",3,1);
        },"t3");
        t1.start();
        t2.start();
        t3.start();
    }


}
