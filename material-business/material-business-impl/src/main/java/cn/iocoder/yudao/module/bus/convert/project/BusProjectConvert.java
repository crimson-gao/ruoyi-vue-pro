package cn.iocoder.yudao.module.bus.convert.project;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.bus.controller.admin.project.vo.*;
import cn.iocoder.yudao.module.bus.dal.dataobject.project.BusProjectDO;

/**
 * 项目 Convert
 *
 * @author crimson
 */
@Mapper
public interface BusProjectConvert {

    BusProjectConvert INSTANCE = Mappers.getMapper(BusProjectConvert.class);

    BusProjectDO convert(BusProjectCreateReqVO bean);

    BusProjectDO convert(BusProjectUpdateReqVO bean);

    BusProjectRespVO convert(BusProjectDO bean);

    List<BusProjectRespVO> convertList(List<BusProjectDO> list);

    PageResult<BusProjectRespVO> convertPage(PageResult<BusProjectDO> page);

    List<BusProjectExcelVO> convertList02(List<BusProjectDO> list);

}
