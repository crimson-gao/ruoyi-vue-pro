package cn.iocoder.yudao.module.bus.dal.mysql.material;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.bus.dal.dataobject.material.BusMaterialDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.bus.controller.admin.material.vo.*;

/**
 * 物料编码 Mapper
 *
 * @author crimson
 */
@Mapper
public interface BusMaterialMapper extends BaseMapperX<BusMaterialDO> {

    default PageResult<BusMaterialDO> selectPage(BusMaterialPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<BusMaterialDO>()
                .likeIfPresent("code", reqVO.getCode())
                .likeIfPresent("name", reqVO.getName())
                .eqIfPresent("creator", reqVO.getCreator())
                .eqIfPresent("status",reqVO.getStatus())
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc("id")        );
    }

    default List<BusMaterialDO> selectList(BusMaterialExportReqVO reqVO) {
        return selectList(new QueryWrapperX<BusMaterialDO>()
                .likeIfPresent("code", reqVO.getCode())
                .likeIfPresent("name", reqVO.getName())
                .eqIfPresent("creator", reqVO.getCreator())
                .eqIfPresent("status",reqVO.getStatus())
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc("id")        );
    }

}
