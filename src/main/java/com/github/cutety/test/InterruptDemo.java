package com.github.cutety.test;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseInterruption tpi = new TwoPhaseInterruption();
        tpi.start();
        Thread.sleep(3000);
        tpi.stop();
    }
}

    class TwoPhaseInterruption {
        final Logger log = LoggerFactory.getLogger(InterruptDemo.class);
        private Thread monitor;
        public void start() {
            monitor = new Thread(() -> {
                while(true) {
                    Thread current = Thread.currentThread();
                    boolean interrupted = current.isInterrupted();
                    if(interrupted) {
                        log.debug("oops!I was interrupted...");
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                        log.debug("record the result of the monitor.");
                    } catch (InterruptedException e) {
                        monitor.interrupt();
                        e.printStackTrace();
                    }
                }
            });
            monitor.start();
        }
        public void stop() {
            monitor.interrupt();
        }
    }

