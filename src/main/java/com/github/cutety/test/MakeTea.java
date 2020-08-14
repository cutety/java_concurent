package com.github.cutety.test;

import com.github.cutety.utils.Sleeper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.cutety.utils.Sleeper.sleep;

@Slf4j
public class MakeTea {
    static Logger log = LoggerFactory.getLogger(MakeTea.class);
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("洗水壶");
            sleep(1);
            log.debug("烧开水");
            sleep(5);
        },"爸爸");
        Thread t2 = new Thread(() -> {
            log.debug("洗茶壶");
            sleep(1);
            log.debug("洗茶杯");
            sleep(1);
            log.debug("拿茶杯");
            sleep(1);
        },"儿子");
        t1.start();
        t2.start();
    }

}
