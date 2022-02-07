import { listSimpleDepts } from "@/api/system/dept"
// 用于存储当前的部门信息， 缓存部门 id 与 部门名称关系
const state = {
  dept_map: undefined // 缓存部门信息
}
const mutations = {
  UPDATE_DEPT_MAP: (state,map) => {
    state.dept_map = map
  }
}
const actions = {
  updateDeptMap({ commit }) {
    listSimpleDepts().then((resp)=> {
      // 设置数据
      const dataMap = {}
      if(resp !== undefined){
        resp.data.forEach(data => {
          dataMap[data.id] = data.name
        })
        commit('UPDATE_DEPT_MAP', dataMap)
      }else{
        console.log("update dept map got empty resp")
      }
    })
  }
}
export default {
  namespaced: true,
  state,
  mutations,
  actions
}
