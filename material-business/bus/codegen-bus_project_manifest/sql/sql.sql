-- 菜单 SQL
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '项目物料清单管理', '', 2, 0, ${table.parentMenuId},
    'project-manifest', '', 'bus/projectManifest/index', 0
);

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '项目物料清单查询', 'bus:project-manifest:query', 3, 1, @parentId,
    '', '', '', 0
);
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '项目物料清单创建', 'bus:project-manifest:create', 3, 2, @parentId,
    '', '', '', 0
);
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '项目物料清单更新', 'bus:project-manifest:update', 3, 3, @parentId,
    '', '', '', 0
);
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '项目物料清单删除', 'bus:project-manifest:delete', 3, 4, @parentId,
    '', '', '', 0
);
INSERT INTO `sys_menu`(
    `name`, `permission`, `menu_type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `status`
)
VALUES (
    '项目物料清单导出', 'bus:project-manifest:export', 3, 5, @parentId,
    '', '', '', 0
);
