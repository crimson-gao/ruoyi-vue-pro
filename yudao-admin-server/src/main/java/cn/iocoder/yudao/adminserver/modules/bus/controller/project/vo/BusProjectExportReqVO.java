package cn.iocoder.yudao.adminserver.modules.bus.controller.project.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "项目 Excel 导出 Request VO", description = "参数和 BusProjectPageReqVO 是一致的")
@Data
public class BusProjectExportReqVO {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编号")
    private String code;

    @ApiModelProperty(value = "登记编号")
    private String registerCode;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始登记时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束登记时间")
    private Date endCreateTime;

    @ApiModelProperty(value = "状态，0为已创建")
    private Integer status;

}
