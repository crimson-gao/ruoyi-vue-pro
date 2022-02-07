package cn.iocoder.yudao.module.bus.dal.mysql.project;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.bus.dal.dataobject.project.BusProjectDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.bus.controller.admin.project.vo.*;

/**
 * 项目 Mapper
 *
 * @author crimson
 */
@Mapper
public interface BusProjectMapper extends BaseMapperX<BusProjectDO> {

    default PageResult<BusProjectDO> selectPage(BusProjectPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<BusProjectDO>()
                .likeIfPresent("name", reqVO.getName())
                .eqIfPresent("code", reqVO.getCode())
                .eqIfPresent("register_code", reqVO.getRegisterCode())
                .eqIfPresent("dept_id", reqVO.getDeptId())
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .eqIfPresent("status", reqVO.getStatus())
                .orderByDesc("id")        );
    }

    default List<BusProjectDO> selectList(BusProjectExportReqVO reqVO) {
        return selectList(new QueryWrapperX<BusProjectDO>()
                .likeIfPresent("name", reqVO.getName())
                .eqIfPresent("code", reqVO.getCode())
                .eqIfPresent("register_code", reqVO.getRegisterCode())
                .eqIfPresent("dept_id", reqVO.getDeptId())
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .eqIfPresent("status", reqVO.getStatus())
                .orderByDesc("id")        );
    }

}
