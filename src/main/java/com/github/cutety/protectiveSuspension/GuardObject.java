package com.github.cutety.protectiveSuspension;



import static com.github.cutety.utils.LogUtil.*;

public class GuardObject {
    private Object response;
    private int id;

    public GuardObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object get(long timeout) {
        synchronized (this) {
            long beginTime = System.currentTimeMillis();
            long passTime = 0;
            while(response == null) {
                long waitTime = timeout - passTime;
                if(waitTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passTime = System.currentTimeMillis() - beginTime;
            }
        }
        return response;
    }

    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}
