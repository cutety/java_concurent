package com.github.cutety.test;

import com.github.cutety.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.cutety.utils.LogUtil.log;

@Slf4j(topic =  "c.Join")
public class JoinTest {
    private static int x;
    static final Logger LOGGER = LoggerFactory.getLogger(JoinTest.class);
    public static void main(String[] args) throws InterruptedException {

        Runnable r = () -> {
            x = 10;
        };
        Thread t1 = new Thread(r);
        t1.start();
        t1.join();
        LOGGER.debug("{}",x);
        log.debug("test ooo");
    }

}
