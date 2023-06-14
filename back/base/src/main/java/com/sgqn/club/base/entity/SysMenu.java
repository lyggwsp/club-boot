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
     * 权限
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

    /**
     * 创建者,用户ID
     */
    @TableField("creator")
    private Long creator;

    /**
     * 修改者,用户ID
     */
    @TableField("updater")
    private Long updater;


}
