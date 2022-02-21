#!/bin/bash
database=material
# 创建数据库
mysql -uroot -p123456  -e "create schema if not exists ${database} default character set utf8mb4 collate utf8mb4_unicode_ci;"

# 定义函数
function execSql(){
  local -n input=$1
  for i in ${input[@]};do
    mysql -uroot -p123456 ${database} < ${i}
  done
}

# 创建表格
tables=('activiti.sql' 'quartz.sql' 'ruoyi-vue-pro.sql' 'bus_def.sql')
execSql(tables)


# 插入数据
datas=('')
# find all files in the currrent directory with prefix 'ruoyi_vue_pro_' and suffix '.sql'
datas=($(find . -maxdepth 1 -name 'ruoyi_vue_pro_*.sql'))
execSql(datas)

