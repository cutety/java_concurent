package com.github.cutety.test;


import com.github.cutety.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j(topic = "c.TestDemo1")
public class TestDemo1 {
    static final Logger LOGGER = LoggerFactory.getLogger(TestDemo1.class);
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("test");
        });
        t1.start();
        LOGGER.debug("test2");
    }
}
