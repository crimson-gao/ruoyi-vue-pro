package cn.iocoder.yudao.module.bus.controller.admin.projectManifest.vo;

import cn.iocoder.yudao.framework.common.util.date.DateUtils;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "项目物料清单 Excel 导出 Request VO", description = "参数和 BusProjectManifestPageReqVO 是一致的")
@Data
public class BusProjectManifestExportReqVO {

    @ApiModelProperty(value = "所属项目")
    private Long projectId;

    @ApiModelProperty(value = "物料编码")
    private Long materialId;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
