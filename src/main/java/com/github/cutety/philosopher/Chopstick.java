package com.github.cutety.philosopher;

import java.util.concurrent.locks.ReentrantLock;

public class Chopstick extends ReentrantLock {
    private final String name;

    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "name='" + name + '\'' +
                '}';
    }
}
