package com.github.cutety.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BalanceSimulation {
    public static void main(String[] args) {
        AccountSimulation sa = new SafeAccount(10000);
        AccountSimulation.simulation(sa);
    }
}
class UnsafeAccount implements AccountSimulation{
    private Integer balance;

    public UnsafeAccount(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public void withdraw(Integer amount) {
        this.balance -= amount;
    }
}
class SafeAccount implements AccountSimulation{
    private final AtomicInteger balance;

    public SafeAccount(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        while(true) {
            int prev = balance.get();
            int next =prev - amount;
            if(balance.compareAndSet(prev,next)) {
                break;
            }
        }
    }
}
interface AccountSimulation {
    Integer getBalance();
    void withdraw(Integer amount);
    static void simulation(AccountSimulation account) {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            threadList.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }
        long startTime = System.currentTimeMillis();
        threadList.forEach(Thread::start);
        threadList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime+"balance:"+ account.getBalance());
    }
}