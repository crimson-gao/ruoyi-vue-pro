package cn.iocoder.yudao.adminserver.modules.bus.convert.projectManifest;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.adminserver.modules.bus.controller.projectManifest.vo.*;
import cn.iocoder.yudao.adminserver.modules.bus.dal.dataobject.projectManifest.BusProjectManifestDO;

/**
 * 项目物料清单 Convert
 *
 * @author crimson
 */
@Mapper
public interface BusProjectManifestConvert {

    BusProjectManifestConvert INSTANCE = Mappers.getMapper(BusProjectManifestConvert.class);

    BusProjectManifestDO convert(BusProjectManifestCreateReqVO bean);

    BusProjectManifestDO convert(BusProjectManifestUpdateReqVO bean);

    BusProjectManifestRespVO convert(BusProjectManifestDO bean);

    List<BusProjectManifestRespVO> convertList(List<BusProjectManifestDO> list);

    PageResult<BusProjectManifestRespVO> convertPage(PageResult<BusProjectManifestDO> page);

    List<BusProjectManifestExcelVO> convertList02(List<BusProjectManifestDO> list);

}
