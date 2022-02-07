package cn.iocoder.yudao.module.bus.controller.admin.projectManifest.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* 项目物料清单 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BusProjectManifestBaseVO {

    @ApiModelProperty(value = "所属项目", required = true)
    @NotNull(message = "所属项目不能为空")
    private Long projectId;

    @ApiModelProperty(value = "物料编码id", required = true)
    @NotNull(message = "物料编码不能为空")
    private Long materialId;

    @ApiModelProperty(value = "申报数量", required = true)
    @NotNull(message = "申报数量不能为空")
    private String requiredQuantity;

    @ApiModelProperty(value = "到货数量")
//    @NotNull(message = "到货数量不能为空")
    private String receivedQuantity;

    @ApiModelProperty(value = "备注")
    private String note;

//    @ApiModelProperty(value = "项目对象")
//    private BusProjectDO project;
//    @ApiModelProperty(value = "物料对象")
//    private BusMaterialDO material;
}
