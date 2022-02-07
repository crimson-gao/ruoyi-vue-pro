-- 菜单 SQL
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '项目管理', '', 2, 0, ${table.parentMenuId},
    'project', '', 'bus/project/index', 0
);

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '项目查询', 'bus:project:query', 3, 1, @parentId,
    '', '', '', 0
);
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '项目创建', 'bus:project:create', 3, 2, @parentId,
    '', '', '', 0
);
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '项目更新', 'bus:project:update', 3, 3, @parentId,
    '', '', '', 0
);
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '项目删除', 'bus:project:delete', 3, 4, @parentId,
    '', '', '', 0
);
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '项目导出', 'bus:project:export', 3, 5, @parentId,
    '', '', '', 0
);
