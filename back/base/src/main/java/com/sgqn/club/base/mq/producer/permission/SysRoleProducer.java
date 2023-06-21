package com.sgqn.club.base.mq.producer.permission;

import com.sgqn.club.base.mq.message.SysRoleRefreshMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author wspstart
 * @description
 * @create 2023-06-21 0:30
 */
@Component
public class SysRoleProducer {

    @Autowired
    protected ApplicationEventPublisher applicationEventPublisher;

    public void sendRoleRefreshMessage(){
        applicationEventPublisher.publishEvent(new SysRoleRefreshMessage());
    }
}
