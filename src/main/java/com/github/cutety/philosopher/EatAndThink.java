package com.github.cutety.philosopher;

public class EatAndThink {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("c1");
        Chopstick c2 = new Chopstick("c2");
        Chopstick c3 = new Chopstick("c3");
        Chopstick c4 = new Chopstick("c4");
        Chopstick c5 = new Chopstick("c5");
        new Thread(new Philosopher(c1,c2),"Confucius").start();
        new Thread(new Philosopher(c2,c3),"Socrates").start();
        new Thread(new Philosopher(c3,c4),"Plato").start();
        new Thread(new Philosopher(c4,c5),"Aristotle").start();
        new Thread(new Philosopher(c1,c5),"Dante").start();
    }
}
