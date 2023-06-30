CREATE TABLE `sys_role`
(
    `id`          int          NOT NULL COMMENT '主键ID',
    `name`        varchar(30)  NULL COMMENT '角色名称',
    `description` varchar(255) NULL COMMENT '角色描述（存储有关角色的详细说明或相关信息）',
    `status`      tinyint      NULL COMMENT '角色状态(0表示禁用，1表示启用)',
    `create_time` datetime     NULL COMMENT '创建时间',
    `update_time` datetime     NULL COMMENT '更新时间',
    `deleted`     tinyint      NULL COMMENT '是否删除',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_club`
(
    `id`                 int          NOT NULL COMMENT '主键ID，也就是社团编号',
    `name`               varchar(255) NULL COMMENT '社团名称',
    `description`        varchar(255) NULL COMMENT '描述（可以是社团简介等信息）',
    `established_date`   date         NULL COMMENT '成立时间',
    `cur_number_people`  int          NULL COMMENT '当前人数',
    `affiliated_college` varchar(255) NULL COMMENT '所属学院',
    `email`              varchar(255) NULL COMMENT '社团联系邮箱',
    `sort`               int          NULL COMMENT '前端排序字段',
    `cur_session`        varchar(255) NULL COMMENT '当前届数',
    `create_time`        datetime     NULL COMMENT '创建时间',
    `update_time`        datetime     NULL COMMENT '信息修改时间',
    `deleted`            tinyint      NULL COMMENT '是否删除',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_club_leader`
(
    `id`          int          NOT NULL COMMENT '主键ID',
    `club_id`     int          NULL COMMENT '社团编号',
    `user_id`     int          NULL COMMENT '用户编号',
    `session`     varchar(255) NULL COMMENT '届数',
    `create_time` datetime     NULL COMMENT '信息创建时间',
    `update_time` datetime     NULL COMMENT '信息修改时间',
    `deleted`     tinyint      NULL COMMENT '信息是否删除',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_department`
(
    `id`                int          NOT NULL COMMENT '主键ID，部门编号',
    `name`              varchar(255) NULL COMMENT '部门名称',
    `description`       varchar(255) NULL COMMENT '部门描述',
    `club_id`           int          NULL COMMENT '所属社团ID',
    `cur_number_people` int          NULL COMMENT '当前部门人数',
    `established_date`  date         NULL COMMENT '部门成立时间',
    `create_time`       datetime     NULL COMMENT '信息创建时间',
    `update_time`       datetime     NULL COMMENT '信息修改时间',
    `deleted`           tinyint      NULL COMMENT '信息是否删除',
    PRIMARY KEY (`id`)
);


CREATE TABLE `t_menu`
(
    `id`          int          NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`        varchar(255) NULL COMMENT '菜单名称',
    `title`       varchar(255) NULL COMMENT '菜单页面标题',
    `icon`        varchar(255) NULL COMMENT '前端菜单图标',
    `badge`       varchar(255) NULL COMMENT '菜单徽标',
    `target`      varchar(255) NULL COMMENT '页面打开窗口',
    `path`        varchar(255) NULL COMMENT '菜单对应的前端路由路径',
    `component`   varchar(255) NULL COMMENT '对应的组件',
    `renderMenu`  varchar(255) NULL COMMENT '是否渲染菜单',
    `cacheable`   varchar(255) NULL COMMENT '是否开启缓存',
    `closeable`   varchar(255) NULL COMMENT '页面是否可关闭',
    `parent_id`   int          NULL COMMENT '父菜单标签',
    `create_time` datetime     NULL COMMENT '创建时间',
    `update_time` datetime     NULL COMMENT '修改时间',
    `deleted`     tinyint      NULL COMMENT '是否删除',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_role_menu`
(
    `id`          int      NOT NULL COMMENT '主键ID',
    `role_id`     int      NULL COMMENT '角色ID',
    `menu_id`     int      NULL COMMENT '菜单ID',
    `create_time` datetime NULL,
    `update_time` datetime NULL,
    `deleted`     tinyint  NULL COMMENT '是否删 除',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_user`
(
    `id`          int         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username`    varchar(60) NOT NULL COMMENT '用于登录的用户名',
    `password`    varchar(60) NOT NULL COMMENT '用户登录的密码',
    `nickname`    varchar(50) NULL COMMENT '昵称',
    `email`       varchar(30) NULL COMMENT '邮箱地址',
    `is_admin`    tinyint     NOT NULL COMMENT '是否是管理员',
    `detail_id`   int         NULL COMMENT '对应着用户详情表ID',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `update_time` datetime    NOT NULL COMMENT '更新时间',
    `deleted`     tinyint     NULL COMMENT '是否删除',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_user_club`
(
    `id`          int      NOT NULL COMMENT '主键ID',
    `user_id`     int      NOT NULL COMMENT '用户编号',
    `club_id`     int      NULL COMMENT '社团编号',
    `role_id`     int      NULL COMMENT '角色编号',
    `join_time`   datetime NULL COMMENT '加入时间',
    `leave_time`  datetime NULL COMMENT '离开社团时间',
    `dept_id`     int      NULL COMMENT '加入社团的部门编号',
    `create_time` datetime NOT NULL COMMENT '信息创建时间',
    `update_time` datetime NOT NULL COMMENT '信息修改时间',
    `deleted`     tinyint  NULL COMMENT '信息是否删除',
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_user_detail`
(
    `id`          int         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
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

