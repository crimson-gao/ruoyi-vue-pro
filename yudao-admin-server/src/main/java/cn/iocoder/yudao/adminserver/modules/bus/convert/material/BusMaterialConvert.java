package cn.iocoder.yudao.adminserver.modules.bus.convert.material;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.adminserver.modules.bus.controller.material.vo.*;
import cn.iocoder.yudao.adminserver.modules.bus.dal.dataobject.material.BusMaterialDO;

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
