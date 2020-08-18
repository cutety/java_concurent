package com.github.cutety.test;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;

public class VolatileDemo {
    public volatile static boolean run = true;

    public static void main(String[] args) {
        new Thread(() -> {
            while (run) {
                //do something...
            }
        }).start();
        Sleeper.sleep(1);
        LogUtil.log.debug("停止");
        run = false;
    }
}
