package cn.iocoder.yudao.module.bus.controller.admin.project.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("项目 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BusProjectRespVO extends BusProjectBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "登记时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "完工时间")
    private Date builtTime;

    @ApiModelProperty(value = "归档时间")
    private Date doneTime;

    @ApiModelProperty(value = "状态，0为已创建")
    private Integer status;

    @ApiModelProperty(value = "创建者")
    private String creator;

    @ApiModelProperty(value = "更新者")
    private String updater;

}
