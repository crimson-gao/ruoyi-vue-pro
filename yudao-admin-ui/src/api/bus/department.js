import request from '@/utils/request'

// 创建项目部门
export function createDepartment(data) {
  return request({
    url: '/bus/department/create',
    method: 'post',
    data: data
  })
}

// 更新项目部门
export function updateDepartment(data) {
  return request({
    url: '/bus/department/update',
    method: 'put',
    data: data
  })
}

// 删除项目部门
export function deleteDepartment(id) {
  return request({
    url: '/bus/department/delete?id=' + id,
    method: 'delete'
  })
}

// 获得项目部门
export function getDepartment(id) {
  return request({
    url: '/bus/department/get?id=' + id,
    method: 'get'
  })
}

// 获得项目部门分页
export function getDepartmentPage(query) {
  return request({
    url: '/bus/department/page',
    method: 'get',
    params: query
  })
}

// 导出项目部门 Excel
export function exportDepartmentExcel(query) {
  return request({
    url: '/bus/department/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
