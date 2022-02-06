package cn.iocoder.yudao.adminserver.modules.bus.controller.material.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("物料编码 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BusMaterialRespVO extends BusMaterialBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建者")
    private String creator;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(value = "更新者")
    private String updater;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

}
