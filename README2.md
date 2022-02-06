## 简化部署（待完成）
部署 mysql
```sql
create database `ruoyi-vue-pro` default character set utf8 collate tf8_general_ci
```

部署 redis  

启动 admin-server 
admin-ui (npm install && npm run dev) 

## 自定义
1. 首先定义数据库表，creator 之后的为通用内容  
```sql
DROP TABLE IF EXISTS `bpm_oa_leave`;
CREATE TABLE `bpm_oa_leave` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '请假表单主键',
  `user_id` bigint NOT NULL COMMENT '申请人的用户编号',
  `type` tinyint NOT NULL COMMENT '请假类型',
  `reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请假原因',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `day` tinyint NOT NULL COMMENT '请假天数',
  `result` tinyint NOT NULL COMMENT '请假结果',
  `process_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '流程实例的编号',
  
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='OA 请假申请表';
```
2. 执行 sql，然后在页面使用代码生成工具生成
可以生成 controller mapper service vue   
   分别放到 modules 下对应目录位置 system bpm infra pay tool
   
vue 放到 views 下面 

代码生成工具中可以选择生成哪些
查询代表输入框
- 列表代表列出对应列  
- 删除  
- 更新代表修改  
- 查询代表搜索框  
- 创建代表可创建  
配置模块名、上级菜单信息、业务名
  
3. 在页面配置菜单管理
配置业务 vue 的 component 为组件路径 模块名/业务名/index
配置权限标识为 模块名:业务名:  query create update delete list export 等
会自动生成对应的
   
## 注意事项
export 等可以自己再自定义
tree之类的看角色管理
tree 类型的table 也可以参考

## 错误点
错误码的生成与在线配置
树形菜单的移动化
自动代码生成工具优化区分 前端与后端  

## 权限两级
一级是菜单权限（前后端都有）
一级是部门数据权限，仅作单级部门即可

这里关于金额的权限做特殊处理
结算金额放在新的单独表格中， project ，放在单独菜单中
权限可以具体到查询修改
关于跳转，只需要弹窗即可
已完成的清单条目与项目变颜色

## 以后再考虑
优化下排序，需要配合 sql，可以只修改部分哦


sysDataPermissionConfiguration 内可以添加生效的 table 与 列的规则，可以添加 dept 的列名与 user 的列名
目前只有 user 与 dept 本身添加了规则
dept data permission 是一种 dataPermission
