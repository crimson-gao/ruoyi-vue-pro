package cn.iocoder.yudao.module.bus.dal.mysql.projectManifest;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.bus.dal.dataobject.projectManifest.BusProjectManifestDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.bus.controller.admin.projectManifest.vo.*;

/**
 * 项目物料清单 Mapper
 *
 * @author crimson
 */
@Mapper
public interface BusProjectManifestMapper extends BaseMapperX<BusProjectManifestDO> {

    default PageResult<BusProjectManifestDO> selectPage(BusProjectManifestPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<BusProjectManifestDO>()
                .eqIfPresent("project_id", reqVO.getProjectId())
                .eqIfPresent("material_id", reqVO.getMaterialId())
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc("id")        );
    }
    default PageResult<BusProjectManifestDO> selectPageInIds(BusProjectManifestPageReqVO reqVO,List<Long> materialIds) {
        return selectPage(reqVO, new QueryWrapperX<BusProjectManifestDO>()
                .eqIfPresent("project_id", reqVO.getProjectId())
//                .eqIfPresent("material_id", reqVO.getMaterialId())
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .inIfPresent("material_id",materialIds)
                .orderByDesc("id")        );
    }

    default List<BusProjectManifestDO> selectList(BusProjectManifestExportReqVO reqVO) {
        return selectList(new QueryWrapperX<BusProjectManifestDO>()
                .eqIfPresent("project_id", reqVO.getProjectId())
                .eqIfPresent("material_id", reqVO.getMaterialId())
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc("id")        );
    }
}
