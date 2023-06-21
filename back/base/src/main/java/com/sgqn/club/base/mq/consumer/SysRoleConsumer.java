package com.sgqn.club.base.mq.consumer;

import com.sgqn.club.base.mq.message.SysRoleRefreshMessage;
import com.sgqn.club.base.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wspstart
 * @description
 * @create 2023-06-21 0:33
 */
@Component
@Slf4j
public class SysRoleConsumer {
    @Autowired
    private SysRoleService sysRoleService;

    @EventListener
    public void execute(SysRoleRefreshMessage message) {
        log.info("[execute][收到 Role 刷新消息]");
        sysRoleService.initLocalCache();
    }

}
