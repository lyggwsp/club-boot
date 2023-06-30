package com.sgqn.club.base.mq.consumer;

import com.sgqn.club.base.mq.message.SysRoleRefreshMessage;
import com.sgqn.club.base.service.permisson.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author wspstart
 * @description
 * @create 2023-06-30 11:27
 */
@Component
@Slf4j
public class PermissionConsumer {

    @Autowired
    private PermissionService permissionService;

    @EventListener
    public void execute(SysRoleRefreshMessage sysRoleRefreshMessage) {
        log.error("[execute][收到 sysRoleRefreshMessage 刷新消息],待实现！！");
    }
}
