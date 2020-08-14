package com.github.cutety.protectiveSuspension;

import com.github.cutety.utils.LogUtil;

public class Postman implements Runnable
{
    private int id;
    private String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardObject guardObject = Mailbox.getGuardObject(id);
        LogUtil.log.debug("开始送信{}，内容{}",id,mail);
        guardObject.complete(mail);
    }
}
