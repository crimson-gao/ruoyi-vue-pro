-- 菜单 SQL
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '物料编码管理', '', 2, 0, 1228,
    'material', '', 'bus/material/index', 0
);

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '物料编码查询', 'bus:material:query', 3, 1, @parentId,
    '', '', '', 0
);
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '物料编码创建', 'bus:material:create', 3, 2, @parentId,
    '', '', '', 0
);
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '物料编码更新', 'bus:material:update', 3, 3, @parentId,
    '', '', '', 0
);
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '物料编码删除', 'bus:material:delete', 3, 4, @parentId,
    '', '', '', 0
);
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '物料编码导出', 'bus:material:export', 3, 5, @parentId,
    '', '', '', 0
);
