package com.sgqn.club.base.mq.producer.permission;

import com.sgqn.club.base.mq.message.SysMenuRefreshMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author wspstart
 * @description
 * @create 2023-06-30 11:23
 */
@Component
public class PermissionProducer {

    @Autowired
    protected ApplicationEventPublisher applicationEventPublisher;

    /**
     * 发送 {@link SysMenuRefreshMessage} 消息
     */
    public void sendMenuRefreshMessage() {
        applicationEventPublisher.publishEvent(new SysMenuRefreshMessage());
    }
}
