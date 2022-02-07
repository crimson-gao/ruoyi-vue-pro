package cn.iocoder.yudao.module.bus.controller.admin.material;

import cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;

import io.swagger.annotations.*;

import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.bus.controller.admin.material.vo.*;
import cn.iocoder.yudao.module.bus.dal.dataobject.material.BusMaterialDO;
import cn.iocoder.yudao.module.bus.convert.material.BusMaterialConvert;
import cn.iocoder.yudao.module.bus.service.material.BusMaterialService;

@Api(tags = "物料编码")
@RestController
@RequestMapping("/bus/material")
@Validated
public class BusMaterialController {

    @Resource
    private BusMaterialService materialService;

    @PostMapping("/create")
    @ApiOperation("创建物料编码")
    @PreAuthorize("@ss.hasPermission('bus:material:create')")
    public CommonResult<Long> createMaterial(@Valid @RequestBody BusMaterialCreateReqVO createReqVO) {
        return CommonResult.success(materialService.createMaterial(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新物料编码")
    @PreAuthorize("@ss.hasPermission('bus:material:update')")
    public CommonResult<Boolean> updateMaterial(@Valid @RequestBody BusMaterialUpdateReqVO updateReqVO) {
        materialService.updateMaterial(updateReqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除物料编码")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('bus:material:delete')")
    public CommonResult<Boolean> deleteMaterial(@RequestParam("id") Long id) {
        materialService.deleteMaterial(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得物料编码")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('bus:material:query')")
    public CommonResult<BusMaterialRespVO> getMaterial(@RequestParam("id") Long id) {
        BusMaterialDO material = materialService.getMaterial(id);
        return CommonResult.success(BusMaterialConvert.INSTANCE.convert(material));
    }

    @GetMapping("/list")
    @ApiOperation("获得物料编码列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('bus:material:query')")
    public CommonResult<List<BusMaterialRespVO>> getMaterialList(@RequestParam("ids") Collection<Long> ids) {
        List<BusMaterialDO> list = materialService.getMaterialList(ids);
        return CommonResult.success(BusMaterialConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得物料编码分页")
    @PreAuthorize("@ss.hasPermission('bus:material:query')")
    public CommonResult<PageResult<BusMaterialRespVO>> getMaterialPage(@Valid BusMaterialPageReqVO pageVO) {
        PageResult<BusMaterialDO> pageResult = materialService.getMaterialPage(pageVO);
        return CommonResult.success(BusMaterialConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出物料编码 Excel")
    @PreAuthorize("@ss.hasPermission('bus:material:export')")
    @OperateLog(type = OperateTypeEnum.EXPORT)
    public void exportMaterialExcel(@Valid BusMaterialExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BusMaterialDO> list = materialService.getMaterialList(exportReqVO);
        // 导出 Excel
        List<BusMaterialExcelVO> datas = BusMaterialConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物料编码.xls", "数据", BusMaterialExcelVO.class, datas);
    }

}
