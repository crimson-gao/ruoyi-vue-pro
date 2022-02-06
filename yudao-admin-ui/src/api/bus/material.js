import request from '@/utils/request'

// 创建物料编码
export function createMaterial(data) {
  return request({
    url: '/bus/material/create',
    method: 'post',
    data: data
  })
}

// 更新物料编码
export function updateMaterial(data) {
  return request({
    url: '/bus/material/update',
    method: 'put',
    data: data
  })
}

// 根据id 批量查询物料编码列表
export function listMaterial(data) {
  return request({
    url: '/bus/material/list?ids=' + data,
    method: 'get'
  })
}

// 删除物料编码
export function deleteMaterial(id) {
  return request({
    url: '/bus/material/delete?id=' + id,
    method: 'delete'
  })
}

// 获得物料编码
export function getMaterial(id) {
  return request({
    url: '/bus/material/get?id=' + id,
    method: 'get'
  })
}

// 获得物料编码分页
export function getMaterialPage(query) {
  return request({
    url: '/bus/material/page',
    method: 'get',
    params: query
  })
}

// 导出物料编码 Excel
export function exportMaterialExcel(query) {
  return request({
    url: '/bus/material/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
