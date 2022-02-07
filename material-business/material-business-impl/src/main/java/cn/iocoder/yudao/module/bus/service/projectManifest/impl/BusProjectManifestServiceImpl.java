package cn.iocoder.yudao.module.bus.service.projectManifest.impl;

import cn.iocoder.yudao.module.bus.controller.admin.material.vo.BusMaterialPageReqVO;
import cn.iocoder.yudao.module.bus.dal.dataobject.material.BusMaterialDO;
import cn.iocoder.yudao.module.bus.dal.mysql.material.BusMaterialMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.bus.controller.admin.projectManifest.vo.*;
import cn.iocoder.yudao.module.bus.dal.dataobject.projectManifest.BusProjectManifestDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.bus.convert.projectManifest.BusProjectManifestConvert;
import cn.iocoder.yudao.module.bus.dal.mysql.projectManifest.BusProjectManifestMapper;
import cn.iocoder.yudao.module.bus.service.projectManifest.BusProjectManifestService;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.bus.enums.BusErrorCodeConstants.*;

/**
 * 项目物料清单 Service 实现类
 *
 * @author crimson
 */
@Service
@Validated
public class BusProjectManifestServiceImpl implements BusProjectManifestService {

    @Resource
    private BusProjectManifestMapper projectManifestMapper;
    @Resource
    private BusMaterialMapper materialMapper;

    @Override
    public Long createProjectManifest(BusProjectManifestCreateReqVO createReqVO) {
        // 插入
        BusProjectManifestDO projectManifest = BusProjectManifestConvert.INSTANCE.convert(createReqVO);
        projectManifestMapper.insert(projectManifest);
        // 返回
        return projectManifest.getId();
    }

    @Override
    public void updateProjectManifest(BusProjectManifestUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateProjectManifestExists(updateReqVO.getId());
        // 更新
        BusProjectManifestDO updateObj = BusProjectManifestConvert.INSTANCE.convert(updateReqVO);
        projectManifestMapper.updateById(updateObj);
    }

    @Override
    public void deleteProjectManifest(Long id) {
        // 校验存在
        this.validateProjectManifestExists(id);
        // 删除
        projectManifestMapper.deleteById(id);
    }

    private void validateProjectManifestExists(Long id) {
        if (projectManifestMapper.selectById(id) == null) {
            throw exception(PROJECT_MANIFEST_NOT_EXISTS);
        }
    }

    @Override
    public BusProjectManifestDO getProjectManifest(Long id) {
        return projectManifestMapper.selectById(id);
    }

    @Override
    public List<BusProjectManifestDO> getProjectManifestList(Collection<Long> ids) {
        return projectManifestMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BusProjectManifestDO> getProjectManifestPage(BusProjectManifestPageReqVO pageReqVO) {
        // 允许通过物料编码查询
        if(pageReqVO.getMaterialId() == null && pageReqVO.getMaterialCode() != null){
            BusMaterialPageReqVO req = new BusMaterialPageReqVO();
            req.setCode(pageReqVO.getMaterialCode());
            PageResult<BusMaterialDO> res = materialMapper.selectPage(req);
            if(res!=null){
                if(res.getList().size() == 0){
                    return PageResult.empty();
                }
                // 从这些 material id 中查找
                List<Long> materialIds = res.getList().stream().map(BusMaterialDO::getId).collect(Collectors.toList());
                return projectManifestMapper.selectPageInIds(pageReqVO,materialIds);
            }
        }
        return projectManifestMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BusProjectManifestDO> getProjectManifestList(BusProjectManifestExportReqVO exportReqVO) {
        return projectManifestMapper.selectList(exportReqVO);
    }

    // 将关联的信息一起取出来
//    public List<BusProjectManifestMaterialDO> getRelatedMaterialInfo(List<BusProjectManifestDO> dos){
//        if(dos == null){
//            return null;
//        }
//        List<Long> ids = new ArrayList<>();
//        for (BusProjectManifestDO busProjectManifestDO : dos) {
//            ids.add(busProjectManifestDO.getMaterialId());
//        }
//        List<BusMaterialDO> materials =materialMapper.selectList("id",ids);
//        Map<Long,BusMaterialDO> m = materials.stream().collect(Collectors.toMap(BusMaterialDO::getId,e->{return e;}));
//        List<BusProjectManifestMaterialDO> result = new ArrayList<>();
//        for(BusProjectManifestDO manifest : dos){
//            result.add(new BusProjectManifestMaterialDO(manifest,m.getOrDefault(manifest.getMaterialId(),null)));
//        }
//        return result;
//    }
}
