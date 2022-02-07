package cn.iocoder.yudao.module.bus.service.material;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.bus.controller.admin.material.vo.*;
import cn.iocoder.yudao.module.bus.dal.dataobject.material.BusMaterialDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 物料编码 Service 接口
 *
 * @author crimson
 */
public interface BusMaterialService {

    /**
     * 创建物料编码
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMaterial(@Valid BusMaterialCreateReqVO createReqVO);

    /**
     * 更新物料编码
     *
     * @param updateReqVO 更新信息
     */
    void updateMaterial(@Valid BusMaterialUpdateReqVO updateReqVO);

    /**
     * 删除物料编码
     *
     * @param id 编号
     */
    void deleteMaterial(Long id);

    /**
     * 获得物料编码
     *
     * @param id 编号
     * @return 物料编码
     */
    BusMaterialDO getMaterial(Long id);

    /**
     * 获得物料编码列表
     *
     * @param ids 编号
     * @return 物料编码列表
     */
    List<BusMaterialDO> getMaterialList(Collection<Long> ids);

    /**
     * 获得物料编码分页
     *
     * @param pageReqVO 分页查询
     * @return 物料编码分页
     */
    PageResult<BusMaterialDO> getMaterialPage(BusMaterialPageReqVO pageReqVO);

    /**
     * 获得物料编码列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 物料编码列表
     */
    List<BusMaterialDO> getMaterialList(BusMaterialExportReqVO exportReqVO);

}
