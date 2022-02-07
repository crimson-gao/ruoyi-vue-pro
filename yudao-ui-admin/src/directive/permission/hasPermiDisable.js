/**
 * 操作权限处理
 * Copyright (c) 2021 crimson
 */

import { hasPermissions } from './tools'
export default {
  bind(el, binding) {
    const { value } = binding
    const has = hasPermissions(value)
    if (!has) {
      el.disabled = "disabled"
      el.classList.add('is-disabled')
    }
  }
}
