package com.github.cutety.protectiveSuspension;

import com.github.cutety.utils.LogUtil;

public class Person implements Runnable
{

    @Override
    public void run() {
        GuardObject guardObject = Mailbox.createGuardObject();
        LogUtil.log.debug("收信id{}",guardObject.getId());
        Object mail = guardObject.get(5000);
        LogUtil.log.debug("收到信{},内容{}",guardObject.getId(),mail);
    }
}
