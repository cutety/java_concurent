package com.github.cutety.ThreadPoolDemo;

import com.github.cutety.utils.LogUtil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class BlockingQueue<T> {
    private Deque<T> queue = new ArrayDeque<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition emptyWaitSet = lock.newCondition();
    private Condition fullWaitSet = lock.newCondition();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }
    //advanced get
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while(queue.isEmpty()) {
                try {
                    if(nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        lock.lock();
        try {
            while(queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public void put(T t) {
        lock.lock();
        try {
            while(queue.size() == capacity) {
                try {
                    LogUtil.log.debug("任务队列已满，等待加入任务队列{}",t);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LogUtil.log.debug("任务过多无法执行，加入任务队列{}",t);
            queue.addLast(t);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }
}
