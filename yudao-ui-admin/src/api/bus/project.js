import request from '@/utils/request'

// 创建项目
export function createProject(data) {
  return request({
    url: '/bus/project/create',
    method: 'post',
    data: data
  })
}

// 更新项目
export function updateProject(data) {
  return request({
    url: '/bus/project/update',
    method: 'put',
    data: data
  })
}

// 删除项目
export function deleteProject(id) {
  return request({
    url: '/bus/project/delete?id=' + id,
    method: 'delete'
  })
}

// 获得项目
export function getProject(id) {
  return request({
    url: '/bus/project/get?id=' + id,
    method: 'get'
  })
}

// 获得项目分页
export function getProjectPage(query) {
  return request({
    url: '/bus/project/page',
    method: 'get',
    params: query
  })
}

// 导出项目 Excel
export function exportProjectExcel(query) {
  return request({
    url: '/bus/project/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
