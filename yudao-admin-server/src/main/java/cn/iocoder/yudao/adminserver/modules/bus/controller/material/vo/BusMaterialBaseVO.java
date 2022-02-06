package cn.iocoder.yudao.adminserver.modules.bus.controller.material.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 物料编码 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BusMaterialBaseVO {

    @ApiModelProperty(value = "物料编码", required = true)
    @NotNull(message = "物料编码不能为空")
    private String code;

    @ApiModelProperty(value = "名称", required = true)
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "规格")
    private String spec;

    @ApiModelProperty(value = "备注")
    private String note;

}
