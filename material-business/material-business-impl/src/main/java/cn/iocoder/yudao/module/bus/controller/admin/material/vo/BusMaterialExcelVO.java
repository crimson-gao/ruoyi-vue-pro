package cn.iocoder.yudao.module.bus.controller.admin.material.vo;

import lombok.*;
import java.util.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 物料编码 Excel VO
 *
 * @author crimson
 */
@Data
public class BusMaterialExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("物料编码")
    private String code;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("状态")
    private Integer status;

    @ExcelProperty("规格")
    private String spec;

    @ExcelProperty("备注")
    private String note;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("更新者")
    private String updater;

    @ExcelProperty("更新时间")
    private Date updateTime;

}
