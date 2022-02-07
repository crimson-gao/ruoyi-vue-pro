package cn.iocoder.yudao.module.bus.controller.admin.project.vo;

import lombok.*;
import java.util.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 项目 Excel VO
 *
 * @author crimson
 */
@Data
public class BusProjectExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("类别")
    private String type;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("编号")
    private String code;

    @ExcelProperty("登记编号")
    private String registerCode;

    @ExcelProperty("部门id")
    private Long deptId;

    @ExcelProperty("施工单位")
    private String builder;

    @ExcelProperty("登记时间")
    private Date createTime;

    @ExcelProperty("更新时间")
    private Date updateTime;

    @ExcelProperty("完工时间")
    private Date builtTime;

    @ExcelProperty("归档时间")
    private Date doneTime;

    @ExcelProperty("状态，0为已创建")
    private Integer status;

    @ExcelProperty("预期付款")
    private String requiredPayment;

    @ExcelProperty("已付款金额")
    private String paidPayment;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("备注")
    private String note;

    @ExcelProperty("更新者")
    private String updater;

}
