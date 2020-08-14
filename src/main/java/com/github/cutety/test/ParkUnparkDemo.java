package com.github.cutety.test;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;

import java.util.concurrent.locks.LockSupport;

import static com.github.cutety.utils.LogUtil.log;

public class ParkUnparkDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("准备调用park");
            Sleeper.sleep(1);
            LockSupport.park();
            log.debug("park结束，现在继续执行");
        });
        t1.start();
        Sleeper.sleep(2);
        log.debug("主线程准备unpark t1线程");
        LockSupport.unpark(t1);
        log.debug("unpark过了");
    }
}
