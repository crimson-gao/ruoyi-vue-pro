package cn.iocoder.yudao.module.bus.controller.admin.projectManifest.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("项目物料清单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BusProjectManifestRespVO extends BusProjectManifestBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建者")
    private String creator;

    @ApiModelProperty(value = "更新者")
    private String updater;

}
