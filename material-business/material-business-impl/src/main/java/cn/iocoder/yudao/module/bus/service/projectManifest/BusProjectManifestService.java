package cn.iocoder.yudao.module.bus.service.projectManifest;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.bus.controller.admin.projectManifest.vo.*;
import cn.iocoder.yudao.module.bus.dal.dataobject.projectManifest.BusProjectManifestDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 项目物料清单 Service 接口
 *
 * @author crimson
 */
public interface BusProjectManifestService {

    /**
     * 创建项目物料清单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProjectManifest(@Valid BusProjectManifestCreateReqVO createReqVO);

    /**
     * 更新项目物料清单
     *
     * @param updateReqVO 更新信息
     */
    void updateProjectManifest(@Valid BusProjectManifestUpdateReqVO updateReqVO);

    /**
     * 删除项目物料清单
     *
     * @param id 编号
     */
    void deleteProjectManifest(Long id);

    /**
     * 获得项目物料清单
     *
     * @param id 编号
     * @return 项目物料清单
     */
    BusProjectManifestDO getProjectManifest(Long id);

    /**
     * 获得项目物料清单列表
     *
     * @param ids 编号
     * @return 项目物料清单列表
     */
    List<BusProjectManifestDO> getProjectManifestList(Collection<Long> ids);

    /**
     * 获得项目物料清单分页
     *
     * @param pageReqVO 分页查询
     * @return 项目物料清单分页
     */
    PageResult<BusProjectManifestDO> getProjectManifestPage(BusProjectManifestPageReqVO pageReqVO);

    /**
     * 获得项目物料清单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 项目物料清单列表
     */
    List<BusProjectManifestDO> getProjectManifestList(BusProjectManifestExportReqVO exportReqVO);

}
