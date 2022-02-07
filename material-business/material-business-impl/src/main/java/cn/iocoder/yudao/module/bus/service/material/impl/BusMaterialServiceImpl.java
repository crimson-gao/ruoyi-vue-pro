package cn.iocoder.yudao.module.bus.service.material.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.bus.controller.admin.material.vo.*;
import cn.iocoder.yudao.module.bus.dal.dataobject.material.BusMaterialDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.bus.convert.material.BusMaterialConvert;
import cn.iocoder.yudao.module.bus.dal.mysql.material.BusMaterialMapper;
import cn.iocoder.yudao.module.bus.service.material.BusMaterialService;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.bus.enums.BusErrorCodeConstants.*;

/**
 * 物料编码 Service 实现类
 *
 * @author crimson
 */
@Service
@Validated
public class BusMaterialServiceImpl implements BusMaterialService {

    @Resource
    private BusMaterialMapper materialMapper;

    @Override
    public Long createMaterial(BusMaterialCreateReqVO createReqVO) {
        // 插入
        BusMaterialDO material = BusMaterialConvert.INSTANCE.convert(createReqVO);
        materialMapper.insert(material);
        // 返回
        return material.getId();
    }

    @Override
    public void updateMaterial(BusMaterialUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateMaterialExists(updateReqVO.getId());
        // 更新
        BusMaterialDO updateObj = BusMaterialConvert.INSTANCE.convert(updateReqVO);
        materialMapper.updateById(updateObj);
    }

    @Override
    public void deleteMaterial(Long id) {
        // 校验存在
        this.validateMaterialExists(id);
        // 删除
        materialMapper.deleteById(id);
    }

    private void validateMaterialExists(Long id) {
        if (materialMapper.selectById(id) == null) {
            throw exception(MATERIAL_NOT_EXISTS);
        }
    }

    @Override
    public BusMaterialDO getMaterial(Long id) {
        return materialMapper.selectById(id);
    }

    @Override
    public List<BusMaterialDO> getMaterialList(Collection<Long> ids) {
        return materialMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BusMaterialDO> getMaterialPage(BusMaterialPageReqVO pageReqVO) {
        return materialMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BusMaterialDO> getMaterialList(BusMaterialExportReqVO exportReqVO) {
        return materialMapper.selectList(exportReqVO);
    }

}
