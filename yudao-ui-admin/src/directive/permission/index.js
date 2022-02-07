import hasRole from './hasRole'
import hasPermi from './hasPermi'
import hasPermiDisable from './hasPermiDisable'
const install = function(Vue) {
  Vue.directive('hasRole', hasRole)
  Vue.directive('hasPermi', hasPermi)
  Vue.directive('hasPermiDisable', hasPermiDisable)
}

if (window.Vue) {
  window['hasRole'] = hasRole
  window['hasPermi'] = hasPermi
  window['hasPermiDisable'] = hasPermiDisable
  Vue.use(install); // eslint-disable-line
}

export default install
