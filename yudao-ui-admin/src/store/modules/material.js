import { listSimpleDepts } from "@/api/system/dept"
// 用于存储当前的部门信息， 缓存部门 id 与 部门名称关系
const state = {
  material_map: undefined // 缓存部门信息
}
const mutations = {
  UPDATE_MATERIAL_MAP: (state,map) => {
    state.material_map = map
  }
}
const actions = {
  updateMaterialMap({ commit }) {
    listSimpleDepts().then((resp)=> {
      // 设置数据
      const dataMap = {}
      resp.data.forEach(data => {
        dataMap[data.id] = data.name

      })
      commit('UPDATE_MATERIAL_MAP', dataMap)
    })
  }
}
export default {
  namespaced: true,
  state,
  mutations,
  actions
}
