 /**
 * 操作权限处理
 * Copyright (c) 2019 ruoyi
 */

import { hasPermissions } from './tools'
export default {
  inserted(el, binding, vnode) {
    const { value } = binding
    const has = hasPermissions(value)
    if (!has) {
      el.parentNode && el.parentNode.removeChild(el)
    }
  }
}
