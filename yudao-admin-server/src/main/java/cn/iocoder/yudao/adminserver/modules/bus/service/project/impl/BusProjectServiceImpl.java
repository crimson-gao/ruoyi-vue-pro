package cn.iocoder.yudao.adminserver.modules.bus.service.project.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.adminserver.modules.bus.controller.project.vo.*;
import cn.iocoder.yudao.adminserver.modules.bus.dal.dataobject.project.BusProjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.adminserver.modules.bus.convert.project.BusProjectConvert;
import cn.iocoder.yudao.adminserver.modules.bus.dal.mysql.project.BusProjectMapper;
import cn.iocoder.yudao.adminserver.modules.bus.service.project.BusProjectService;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.adminserver.modules.bus.enums.BusErrorCodeConstants.*;

/**
 * 项目 Service 实现类
 *
 * @author crimson
 */
@Service
@Validated
public class BusProjectServiceImpl implements BusProjectService {

    @Resource
    private BusProjectMapper projectMapper;

    @Override
    public Long createProject(BusProjectCreateReqVO createReqVO) {
        // 插入
        BusProjectDO project = BusProjectConvert.INSTANCE.convert(createReqVO);
        projectMapper.insert(project);
        // 返回
        return project.getId();
    }

    @Override
    public void updateProject(BusProjectUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateProjectExists(updateReqVO.getId());
        // 更新
        BusProjectDO updateObj = BusProjectConvert.INSTANCE.convert(updateReqVO);
        projectMapper.updateById(updateObj);
    }

    @Override
    public void deleteProject(Long id) {
        // 校验存在
        this.validateProjectExists(id);
        // 删除
        projectMapper.deleteById(id);
    }

    private void validateProjectExists(Long id) {
        if (projectMapper.selectById(id) == null) {
            throw exception(PROJECT_NOT_EXISTS);
        }
    }

    @Override
    public BusProjectDO getProject(Long id) {
        return projectMapper.selectById(id);
    }

    @Override
    public List<BusProjectDO> getProjectList(Collection<Long> ids) {
        return projectMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BusProjectDO> getProjectPage(BusProjectPageReqVO pageReqVO) {
        return projectMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BusProjectDO> getProjectList(BusProjectExportReqVO exportReqVO) {
        return projectMapper.selectList(exportReqVO);
    }

}
