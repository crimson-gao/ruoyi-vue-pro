package cn.iocoder.yudao.adminserver.modules.bus.controller.project.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 项目 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BusProjectBaseVO {

    @ApiModelProperty(value = "类别", required = true)
    @NotNull(message = "类别不能为空")
    private String type;

    @ApiModelProperty(value = "名称", required = true)
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "编号", required = true)
    @NotNull(message = "编号不能为空")
    private String code;

    @ApiModelProperty(value = "登记编号", required = true)
    @NotNull(message = "登记编号不能为空")
    private String registerCode;

    @ApiModelProperty(value = "部门id", required = true)
    @NotNull(message = "部门id不能为空")
    private Long deptId;

    @ApiModelProperty(value = "施工单位")
    private String builder;

    @ApiModelProperty(value = "预期付款")
    private String requiredPayment;

    @ApiModelProperty(value = "已付款金额")
    private String paidPayment;

    @ApiModelProperty(value = "备注")
    private String note;

}
