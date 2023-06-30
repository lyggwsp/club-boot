CREATE TABLE `sys_menu`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`        varchar(255) NULL COMMENT '菜单名称',
    `menu_type`   tinyint      NULL COMMENT '菜单类型表示，菜单权限，操作权限 (目录、菜单、按钮)',
    `title`       varchar(255) NULL COMMENT '菜单页面标题',
    `icon`        varchar(255) NULL COMMENT '前端菜单图标',
    `badge`       varchar(255) NULL COMMENT '菜单徽标',
    `target`      varchar(255) NULL COMMENT '页面打开窗口',
    `path`        varchar(255) NULL COMMENT '菜单对应的前端路由路径',
    `component`   varchar(255) NULL COMMENT '对应的组件',
    `renderMenu`  varchar(255) NULL COMMENT '是否渲染菜单',
    `cacheable`   varchar(255) NULL COMMENT '是否开启缓存',
    `permission`  varchar(100) NULL COMMENT '权限',
    `closeable`   varchar(255) NULL COMMENT '页面是否可关闭',
    `parent_id`   bigint       NULL COMMENT '父菜单标签',
    `sort`        int          NULL COMMENT '前端排序',
    `creator`     bigint       NULL COMMENT '创建者,用户ID',
    `updater`     bigint       NULL COMMENT '修改者,用户ID',
    `create_time` datetime     NULL COMMENT '创建时间',
    `update_time` datetime     NULL COMMENT '修改时间',
    `deleted`     tinyint      NULL COMMENT '是否删除',
    PRIMARY KEY (`id`)
);

CREATE TABLE `sys_role`
(
    `id`          bigint       NOT NULL COMMENT '主键ID',
    `name`        varchar(30)  NOT NULL COMMENT '角色名称',
    `description` varchar(255) NULL COMMENT '角色描述（存储有关角色的详细说明或相关信息）',
    `sort`        tinyint      NOT NULL COMMENT '前端排序',
    `code`        varchar(255) NOT NULL COMMENT '角色代码',
    `status`      tinyint      NOT NULL COMMENT '角色状态(0表示禁用，1表示启用)',
    `creator`     int          NOT NULL COMMENT '角色创建者，userID',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    `deleted`     tinyint      NOT NULL COMMENT '是否删除',
    PRIMARY KEY (`id`)
);

CREATE TABLE `sys_role_menu`
(
    `id`          bigint   NOT NULL COMMENT '主键ID',
    `role_id`     bigint   NOT NULL COMMENT '角色编号',
    `menu_id`     bigint   NOT NULL COMMENT '菜单编号',
    `creator`     bigint   NOT NULL COMMENT '创建者ID',
    `updater`     bigint   NOT NULL COMMENT '更新者ID',
    `create_time` datetime NOT NULL COMMENT '信息创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `deleted`     tinyint  NOT NULL COMMENT '信息是否删除',
    PRIMARY KEY (`id`)
);

CREATE TABLE `sys_user_role_club`
(
    `id`          bigint   NOT NULL COMMENT '主键ID',
    `user_id`     bigint   NOT NULL COMMENT '用户编号',
    `club_id`     bigint   NOT NULL COMMENT '社团编号',
    `role_id`     bigint   NOT NULL COMMENT '角色编号',
    `join_time`   datetime NULL COMMENT '加入时间',
    `leave_time`  datetime NULL COMMENT '离开社团时间',
    `dept_id`     bigint   NULL COMMENT '加入社团的部门编号',
    `create_time` datetime NULL COMMENT '信息创建时间',
    `update_time` datetime NULL COMMENT '信息修改时间',
    `deleted`     tinyint  NULL COMMENT '信息是否删除',
    PRIMARY KEY (`id`)
);
drop table t_user;
CREATE TABLE `t_user`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username`    varchar(60) NULL COMMENT '用于登录的用户名',
    `password`    varchar(60) NULL COMMENT '用户登录的密码',
    `nickname`    varchar(50) NULL COMMENT '昵称',
    `email`       varchar(30) NULL COMMENT '邮箱地址',
    `is_admin`    tinyint     NULL COMMENT '是否是管理员',
    `detail_id`   bigint      NULL COMMENT '对应着用户详情表ID',
    `create_time` datetime    NULL COMMENT '创建时间',
    `update_time` datetime    NULL COMMENT '更新时间',
    `deleted`     tinyint     NULL COMMENT '是否删除',
    PRIMARY KEY (`id`)
);
CREATE TABLE `sys_user`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username`    varchar(60) NULL COMMENT '用于登录的用户名',
    `password`    varchar(60) NULL COMMENT '用户登录的密码',
    `nickname`    varchar(50) NULL COMMENT '昵称',
    `email`       varchar(30) NULL COMMENT '邮箱地址',
    `is_admin`    tinyint     NULL COMMENT '是否是管理员',
    `detail_id`   bigint      NULL COMMENT '对应着用户详情表ID',
    `create_time` datetime    NULL COMMENT '创建时间',
    `update_time` datetime    NULL COMMENT '更新时间',
    `deleted`     tinyint     NULL COMMENT '是否删除',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_user_detail`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `real_name`   varchar(30) NULL COMMENT '用户真实姓名',
    `sex`         char(2)     NULL COMMENT '性别',
    `birth_date`  datetime    NULL COMMENT '出生年月',
    `phone`       varchar(30) NULL COMMENT '联系电话',
    `gard`        tinyint     NULL COMMENT '年级',
    `class`       varchar(30) NULL COMMENT '班级',
    `college`     varchar(60) NULL COMMENT '学院',
    `major`       varchar(60) NULL COMMENT '专业',
    `intake`      datetime    NULL COMMENT '入学时间',
    `create_time` datetime    NULL COMMENT '创建时间',
    `update_time` datetime    NULL COMMENT '修改时间',
    `deleted`     tinyint     NULL COMMENT '是否删除',
    PRIMARY KEY (`id`)
);

