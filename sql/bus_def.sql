# DROP TABLE IF EXISTS `bus_department`;
# CREATE TABLE `bus_department` (
#   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
#   `name` varchar(100) NOT NULL COMMENT '部门名称',
#   `region` varchar(30) DEFAULT NULL COMMENT '地区',
#   `note` varchar(300) DEFAULT NULL COMMENT '备注',
#   `creator` varchar(64) DEFAULT '' COMMENT '创建者',
#   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
#   `updater` varchar(64) DEFAULT '' COMMENT '更新者',
#   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
#   `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB COMMENT='项目部门表';

DROP TABLE IF EXISTS `bus_material`;
CREATE TABLE `bus_material` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
`code` varchar(30) NOT NULL UNIQUE COMMENT '物料编码',
`name` varchar(100) NOT NULL COMMENT '名称',
`spec` varchar(30) DEFAULT NULL COMMENT '规格',
`note` varchar(300) DEFAULT NULL COMMENT '备注',
`status` tinyint DEFAULT 0 COMMENT '0为开启，1 为关闭',
`creator` varchar(64) DEFAULT '' COMMENT '创建者',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`updater` varchar(64) DEFAULT '' COMMENT '更新者',
`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='物料编码表';

DROP TABLE IF EXISTS `bus_project`;
CREATE TABLE `bus_project` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
`type` varchar(30) NOT NULL COMMENT '项目类别',
`name` varchar(100) NOT NULL COMMENT '名称',
`code` varchar(30) NOT NULL UNIQUE COMMENT '项目编号,唯一',
`register_code` varchar(20) NOT NULL COMMENT '登记编号，年度',
`dept_id` bigint(20) NOT NULL COMMENT '部门id外键',
`builder` varchar(20) DEFAULT NULL COMMENT '施工单位',
`built_time` datetime DEFAULT NULL COMMENT '完工时间',
`done_time` datetime DEFAULT NULL COMMENT '资料核对完成时间',
`status` tinyint DEFAULT 0 COMMENT '状态，0为已创建',
# 这两个金额由额外权限保护
`required_payment` varchar(30) DEFAULT NULL COMMENT '预期付款',
`paid_payment` varchar(30) DEFAULT NULL COMMENT '已付款金额',
`note` varchar(300) DEFAULT NULL COMMENT '备注',
`creator` varchar(64) DEFAULT '' COMMENT '创建者',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`updater` varchar(64) DEFAULT '' COMMENT '更新者',
`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
PRIMARY KEY (`id`),
FOREIGN KEY (dept_id) REFERENCES 'ruoyi-vue-pro'.sys_dept(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目表';
# 需要设置项目表的权限为仅部门

DROP TABLE IF EXISTS `bus_project_manifest`;
CREATE TABLE `bus_project_manifest` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
`project_id` bigint(20) NOT NULL COMMENT '项目id外键',
`material_id` bigint(20) NOT NULL COMMENT '物料编码id外键',
`required_quantity` varchar(30) NOT NULL COMMENT '申报数量',
`received_quantity` varchar(30) DEFAULT 0 COMMENT '到货数量',
`note` varchar(300) DEFAULT NULL COMMENT '备注',
`creator` varchar(64) DEFAULT '' COMMENT '创建者',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`updater` varchar(64) DEFAULT '' COMMENT '更新者',
`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
PRIMARY KEY (`id`),
FOREIGN KEY (project_id) REFERENCES bus_project(id),
FOREIGN KEY (material_id) REFERENCES bus_material(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目物料清单表';
