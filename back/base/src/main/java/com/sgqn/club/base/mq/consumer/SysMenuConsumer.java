package com.sgqn.club.base.mq.consumer;

import com.sgqn.club.base.mq.message.SysMenuRefreshMessage;
import com.sgqn.club.base.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wspstart
 * @description
 * @create 2023-06-21 8:37
 */
@Component
@Slf4j
public class SysMenuConsumer {
    @Resource
    private SysMenuService menuService;

    @EventListener
    public void execute(SysMenuRefreshMessage menuRefreshMessage) {
        log.info("[execute][收到 Menu 刷新消息]");
        menuService.initLocalCache();
    }
}
