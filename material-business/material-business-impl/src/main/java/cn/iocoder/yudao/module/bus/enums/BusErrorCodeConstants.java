package cn.iocoder.yudao.module.bus.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 *
 * bus系统，使用 2-000-000-000 段
 */
public interface BusErrorCodeConstants {
    // ========== 物料编码 TODO 补充编号 ==========
    ErrorCode MATERIAL_NOT_EXISTS = new ErrorCode(1000010000, "物料编码不存在");
    ErrorCode PROJECT_MANIFEST_NOT_EXISTS = new ErrorCode(1000020000, "物料清单不存在");
    ErrorCode PROJECT_NOT_EXISTS = new ErrorCode(1000030000, "项目不存在");
}
