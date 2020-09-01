package com.github.cutety.ThreadPoolDemo;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;

import java.util.concurrent.TimeUnit;

public class ThreadPoolApp {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(2,1000, TimeUnit.MILLISECONDS,10);
        for (int i = 0; i < 15; i++) {
            int j = i;
            pool.execute(() -> {
                Sleeper.sleep(10);
                LogUtil.log.debug("{}",j);
            });
        }
    }
}
