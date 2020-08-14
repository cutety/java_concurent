package com.github.cutety.test;

import com.github.cutety.utils.LogUtil;
import com.github.cutety.utils.Sleeper;

public class MultiLocks {
    public static void main(String[] args) {
        BigRoom bigRoom = new BigRoom();
        new Thread(bigRoom::study).start();
        new Thread(bigRoom::rest).start();
    }
}
class BigRoom {
    private final Object studyRoom = new Object();
    private final Object restRoom = new Object();
    public void study() {
        synchronized (studyRoom) {
            LogUtil.log.debug("学习");
            Sleeper.sleep(1);
        }
    }

    public void rest() {
        synchronized (restRoom) {
            LogUtil.log.debug("休息");
            Sleeper.sleep(2);
        }
    }
}
