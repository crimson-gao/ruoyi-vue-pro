package cn.iocoder.yudao.module.bus.convert.material;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.bus.controller.admin.material.vo.*;
import cn.iocoder.yudao.module.bus.dal.dataobject.material.BusMaterialDO;

/**
 * 物料编码 Convert
 *
 * @author crimson
 */
@Mapper
public interface BusMaterialConvert {

    BusMaterialConvert INSTANCE = Mappers.getMapper(BusMaterialConvert.class);

    BusMaterialDO convert(BusMaterialCreateReqVO bean);

    BusMaterialDO convert(BusMaterialUpdateReqVO bean);

    BusMaterialRespVO convert(BusMaterialDO bean);

    List<BusMaterialRespVO> convertList(List<BusMaterialDO> list);

    PageResult<BusMaterialRespVO> convertPage(PageResult<BusMaterialDO> page);

    List<BusMaterialExcelVO> convertList02(List<BusMaterialDO> list);

}
