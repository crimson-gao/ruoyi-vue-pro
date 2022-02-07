package cn.iocoder.yudao.module.bus.dal.dataobject.material;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 物料编码 DO
 *
 * @author crimson
 */
@TableName("bus_material")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusMaterialDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 物料编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    // 状态 0 为开启，1为禁用
    private Integer status;
    /**
     * 规格
     */
    private String spec;
    /**
     * 备注
     */
    private String note;

}
