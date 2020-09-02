package com.github.cutety;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(5);
        Integer res = pool.invoke(new MyTask(5));
        System.out.println(res);
    }
}
class MyTask extends RecursiveTask<Integer> {
    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if(n == 1) {
            return 1;
        }
        MyTask t1 = new MyTask(n - 1);
        t1.fork();
        int result = n + t1.join();
        return result;
    }
}