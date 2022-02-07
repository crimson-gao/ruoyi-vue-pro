package cn.iocoder.yudao.module.bus.dal.dataobject.project;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 项目 DO
 *
 * @author crimson
 */
@TableName("bus_project")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusProjectDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 类别
     */
    private String type;
    /**
     * 名称
     */
    private String name;
    /**
     * 编号
     */
    private String code;
    /**
     * 登记编号
     */
    private String registerCode;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 施工单位
     */
    private String builder;
    /**
     * 完工时间
     */
    private Date builtTime;
    /**
     * 归档时间
     */
    private Date doneTime;
    /**
     * 状态，0为已创建
     */
    private Integer status;
    /**
     * 预期付款
     */
    private String requiredPayment;
    /**
     * 已付款金额
     */
    private String paidPayment;
    /**
     * 备注
     */
    private String note;

}
