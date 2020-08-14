package com.github.cutety.protectiveSuspension;

import com.github.cutety.utils.Sleeper;

import java.util.Set;

public class App {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Person()).start();
        }
        Sleeper.sleep(1);
        Set<Integer> ids = Mailbox.getIds();
        for (Integer id : ids) {
            new Thread(new Postman(id,"content:"+id)).start();
        }
    }
}
