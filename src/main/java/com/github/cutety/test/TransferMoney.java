package com.github.cutety.test;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;

import java.util.Random;

public class TransferMoney {
    public static void main(String[] args) throws InterruptedException {
        Account account1 = new Account(1000, "a1");
        Account account2 = new Account(3000, "a2");
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account1.transfer(randomMoney(), account2);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account2.transfer(randomMoney(), account1);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        LogUtil.log.debug("转账结束，总金额{}", account1.getBalance() + account2.getBalance());
    }

    static Random random = new Random();

    public static int randomMoney() {
        return random.nextInt(100) + 1;
    }
}

class Account {
    private int balance;
    private final String name;

    public Account(int balance, String name) {
        this.balance = balance;
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void transfer(int amount, Account target) {
        synchronized (Account.class) {
            if (amount <= this.balance) {
                this.setBalance(this.balance - amount);
                target.setBalance(target.balance + amount);
                LogUtil.log.debug("{}给{}转账{},总金额{}", this.getName(), target.getName(), amount,this.balance + target.balance);
            }
        }

    }
}
