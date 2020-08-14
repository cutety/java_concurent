package com.github.cutety.test;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import static com.github.cutety.utils.LogUtil.*;
@Slf4j
public class SellingTickets {

    public static void main(String[] args) throws InterruptedException {
        //Selling Tickets
        TicketWindow ticketWindow = new TicketWindow();
        List<Thread> thread_list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Thread thread = new  Thread(() -> {

                ticketWindow.sellAction(randomAmount());
            });
            thread_list.add(thread);
            thread.start();
        }
        for (Thread thread : thread_list) {
            thread.join();
        }
        log.debug("余票:{}",ticketWindow.getCount());
    }

    static Random random = new Random();
    public static int randomAmount() {
        return random.nextInt(5)+1;
    }
}
class TicketWindow {
    private static int count =100;

    public int getCount() {
        return count;
    }

   /* public synchronized  void sellAction(int amount) {
        while(true) {
            if( count >= amount) {
                count -= amount;
                log.debug("卖出{}张票，余票{}",amount,count);
            }else{
                break;
            }
        }
    }*/
    public  void sellAction(int amount) {
        while(true) {
            synchronized (this){
                if( count >= amount) {
                    count --;
                    log.debug("卖出{}张票，余票{}",amount,count);
                }else{
                    break;
                }
            }
        }
    }
}
