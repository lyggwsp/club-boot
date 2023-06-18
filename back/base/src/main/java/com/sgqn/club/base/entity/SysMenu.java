package com.sgqn.club.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sgqn.club.base.bean.BaseEntity;
import lombok.*;

/**
 * <p>
 *
 * </p>
 *
 * @author wspstart
 * @since 2023-06-13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 菜单类型表示，菜单权限，操作权限 (目录、菜单、按钮)
     */
    @TableField("menu_type")
    private Integer menuType;

    /**
     * 菜单页面标题
     */
    @TableField("title")
    private String title;

    /**
     * 前端菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 菜单徽标
     */
    @TableField("badge")
    private String badge;

    /**
     * 页面打开窗口
     */
    @TableField("target")
    private String target;

    /**
     * 菜单对应的前端路由路径
     */
    @TableField("path")
    private String path;

    /**
     * 对应的组件
     */
    @TableField("component")
    private String component;

    /**
     * 是否渲染菜单
     */
    @TableField("renderMenu")
    private String renderMenu;

    /**
     * 是否开启缓存
     */
    @TableField("cacheable")
    private String cacheable;

    /**
     * 权限标识
     * <p>
     * 一般格式为：${系统}:${模块}:${操作}
     * 例如说：system:admin:add，即 system 服务的添加管理员。
     * <p>
     * 当我们把该 SysMenu 赋予给角色后，意味着该角色有该资源：
     * - 对于后端，配合 @PreAuthorize 注解，配置 API 接口需要该权限，从而对 API 接口进行权限控制。
     * - 对于前端，配合前端标签，配置按钮是否展示，避免用户没有该权限时，结果可以看到该操作。
     */
    @TableField("permission")
    private String permission;

    /**
     * 页面是否可关闭
     */
    @TableField("closeable")
    private String closeable;

    /**
     * 父菜单标签
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 前端排序
     */
    @TableField("sort")
    private Integer sort;


}
