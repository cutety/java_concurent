package com.github.cutety.Delivery;

import com.github.cutety.utils.LogUtil;

import java.util.LinkedList;
import java.util.Queue;

import static com.github.cutety.utils.LogUtil.*;

public class MessageQueue {
    private LinkedList<Message> list = new LinkedList<>();
    private final int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }


    public Message take() {
        synchronized (list) {
            while(list.isEmpty()) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = list.removeFirst();
            log.debug("送出一封信,{}",message);
            list.notifyAll();
            return message;
        }
    }

    public void put(Message message) {
        synchronized (list) {
            while(list.size() == capacity) {
                try {
                    log.debug("队列已满，生产者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.addLast(message);
            log.debug("放进去一封信,{}",message);
            list.notifyAll();
        }
    }
}
