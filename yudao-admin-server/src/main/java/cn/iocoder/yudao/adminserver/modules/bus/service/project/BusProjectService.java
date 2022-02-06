package cn.iocoder.yudao.adminserver.modules.bus.service.project;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.adminserver.modules.bus.controller.project.vo.*;
import cn.iocoder.yudao.adminserver.modules.bus.dal.dataobject.project.BusProjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 项目 Service 接口
 *
 * @author crimson
 */
public interface BusProjectService {

    /**
     * 创建项目
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProject(@Valid BusProjectCreateReqVO createReqVO);

    /**
     * 更新项目
     *
     * @param updateReqVO 更新信息
     */
    void updateProject(@Valid BusProjectUpdateReqVO updateReqVO);

    /**
     * 删除项目
     *
     * @param id 编号
     */
    void deleteProject(Long id);

    /**
     * 获得项目
     *
     * @param id 编号
     * @return 项目
     */
    BusProjectDO getProject(Long id);

    /**
     * 获得项目列表
     *
     * @param ids 编号
     * @return 项目列表
     */
    List<BusProjectDO> getProjectList(Collection<Long> ids);

    /**
     * 获得项目分页
     *
     * @param pageReqVO 分页查询
     * @return 项目分页
     */
    PageResult<BusProjectDO> getProjectPage(BusProjectPageReqVO pageReqVO);

    /**
     * 获得项目列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 项目列表
     */
    List<BusProjectDO> getProjectList(BusProjectExportReqVO exportReqVO);

}
