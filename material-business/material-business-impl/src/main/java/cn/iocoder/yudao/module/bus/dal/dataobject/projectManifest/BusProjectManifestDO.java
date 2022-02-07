package cn.iocoder.yudao.module.bus.dal.dataobject.projectManifest;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 项目物料清单 DO
 *
 * @author crimson
 */
@TableName("bus_project_manifest")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BusProjectManifestDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 所属项目
     */
    private Long projectId;
    /**
     * 物料编码
     */
    private Long materialId;
    /**
     * 申报数量
     */
    private String requiredQuantity;
    /**
     * 到货数量
     */
    private String receivedQuantity;
    /**
     * 备注
     */
    private String note;

//    @TableField(exist = false)
//    @EntityMapping(thisField = "projectId",joinField = "id")
//    private BusProjectDO project;
//
//    @TableField(exist = false)
//    @EntityMapping(thisField = "materialId",joinField = "id")
//    private BusMaterialDO material;

}
