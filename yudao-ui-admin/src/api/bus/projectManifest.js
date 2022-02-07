import request from '@/utils/request'

// 创建项目物料清单
export function createProjectManifest(data) {
  return request({
    url: '/bus/project-manifest/create',
    method: 'post',
    data: data
  })
}

// 更新项目物料清单
export function updateProjectManifest(data) {
  return request({
    url: '/bus/project-manifest/update',
    method: 'put',
    data: data
  })
}

// 删除项目物料清单
export function deleteProjectManifest(id) {
  return request({
    url: '/bus/project-manifest/delete?id=' + id,
    method: 'delete'
  })
}

// 获得项目物料清单
export function getProjectManifest(id) {
  return request({
    url: '/bus/project-manifest/get?id=' + id,
    method: 'get'
  })
}

// 获得项目物料清单分页
export function getProjectManifestPage(query) {
  return request({
    url: '/bus/project-manifest/page',
    method: 'get',
    params: query
  })
}

// 导出项目物料清单 Excel
export function exportProjectManifestExcel(query) {
  return request({
    url: '/bus/project-manifest/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
