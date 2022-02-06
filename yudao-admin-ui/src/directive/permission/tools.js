import store from '@/store'

function hasAnyPermission(permissions, needs) {
    const all_permission = "*:*:*";
    return permissions.some(permission => {
      return all_permission === permission || needs.includes(permission)
    })
}
function hasAllPermissions(permissions, needs) {
    const all_permission = "*:*:*";
    let hasPermissions = true
    if (!permissions.some(permission => {
      return all_permission === permission
    })) {
      for (let need of needs) {
        const has = permissions.some(permission => {
          return permission === need
        })
        if (!has) {
          return false
        }
      }
    }
    return hasPermissions
}
export function hasPermissions(needs) {
    const permissions = store.getters && store.getters.permissions
    if (needs && needs instanceof Array && needs.length > 0) {
      const has = hasAnyPermission(permissions, needs)
      // console.log(permissions, needs, has)
      return has
    } else {
      throw new Error(`请设置操作权限标签值`)
    }
}

