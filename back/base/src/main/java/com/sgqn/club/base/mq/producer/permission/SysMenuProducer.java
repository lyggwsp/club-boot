package com.sgqn.club.base.mq.producer.permission;

import com.sgqn.club.base.mq.message.SysMenuRefreshMessage;
import com.sgqn.club.base.mq.message.SysRoleRefreshMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author wspstart
 * @description
 * @create 2023-06-21 8:36
 */
@Component
public class SysMenuProducer {
    @Autowired
    protected ApplicationEventPublisher applicationEventPublisher;

    /**
     * 发送 {@link SysMenuRefreshMessage} 消息
     */
    public void sendMenuRefreshMessage() {
        applicationEventPublisher.publishEvent(new SysMenuRefreshMessage());
    }
}
