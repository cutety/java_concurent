package com.github.cutety.ThreadPoolDemo;

import com.github.cutety.utils.LogUtil;
import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    private BlockingQueue<Runnable> taskQueue;
    private final HashSet<Worker> workers = new HashSet();
    private int coreSize;
    private long timeout;
    private TimeUnit timeUnit;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit,int queueCapacity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
    }
    public void execute(Runnable task) {
        synchronized (workers) {
            if(workers.size() < this.coreSize) {
                Worker worker = new Worker(task);
                LogUtil.log.debug("新增worker{},{}",worker,task);
                workers.add(worker);
                worker.start();
            } else {
                taskQueue.put(task);
            }
        }
    }
    class Worker extends Thread{
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //while(task !=null || (task = taskQueue.take()) != null) {
            while(task !=null || (task = taskQueue.poll(timeout,timeUnit)) != null) {
                try {
                    LogUtil.log.debug("正在执行任务{}",task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                workers.remove(this);
                LogUtil.log.debug("worker{}被移除",this);
            }
        }
    }

}
