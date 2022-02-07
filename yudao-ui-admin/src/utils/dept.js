import store from '@/store'

export function updateDept(){
  store.dispatch('dept/updateDeptMap')
  console.log('dept map updated')
}
export function getDeptName(id){
  return store.getters.dept_map[id] || ''
}


