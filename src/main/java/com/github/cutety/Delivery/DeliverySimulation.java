package com.github.cutety.Delivery;

import com.github.cutety.utils.Sleeper;

public class DeliverySimulation {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);
        for (int i = 0; i < 3; i++) {
            int id = i ;
            new Thread(() -> {
                Message message = new Message(id, "package" + id);
                queue.put(message);
            },"producer"+i).start();
        }

        new Thread(() -> {
            while(true) {
                Sleeper.sleep(1);
                Message message = queue.take();
            }
        },"consumer").start();
    }
}

