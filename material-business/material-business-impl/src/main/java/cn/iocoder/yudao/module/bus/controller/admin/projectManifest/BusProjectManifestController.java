package cn.iocoder.yudao.module.bus.controller.admin.projectManifest;

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

import cn.iocoder.yudao.module.bus.controller.admin.projectManifest.vo.*;
import cn.iocoder.yudao.module.bus.dal.dataobject.projectManifest.BusProjectManifestDO;
import cn.iocoder.yudao.module.bus.convert.projectManifest.BusProjectManifestConvert;
import cn.iocoder.yudao.module.bus.service.projectManifest.BusProjectManifestService;

@Api(tags = "项目物料清单")
@RestController
@RequestMapping("/bus/project-manifest")
@Validated
public class BusProjectManifestController {

    @Resource
    private BusProjectManifestService projectManifestService;

    @PostMapping("/create")
    @ApiOperation("创建项目物料清单")
    @PreAuthorize("@ss.hasPermission('bus:project-manifest:create')")
    public CommonResult<Long> createProjectManifest(@Valid @RequestBody BusProjectManifestCreateReqVO createReqVO) {
        return CommonResult.success(projectManifestService.createProjectManifest(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新项目物料清单")
    @PreAuthorize("@ss.hasPermission('bus:project-manifest:update')")
    public CommonResult<Boolean> updateProjectManifest(@Valid @RequestBody BusProjectManifestUpdateReqVO updateReqVO) {
        projectManifestService.updateProjectManifest(updateReqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除项目物料清单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('bus:project-manifest:delete')")
    public CommonResult<Boolean> deleteProjectManifest(@RequestParam("id") Long id) {
        projectManifestService.deleteProjectManifest(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得项目物料清单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('bus:project-manifest:query')")
    public CommonResult<BusProjectManifestRespVO> getProjectManifest(@RequestParam("id") Long id) {
        BusProjectManifestDO projectManifest = projectManifestService.getProjectManifest(id);
        return CommonResult.success(BusProjectManifestConvert.INSTANCE.convert(projectManifest));
    }

    @GetMapping("/list")
    @ApiOperation("获得项目物料清单列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('bus:project-manifest:query')")
    public CommonResult<List<BusProjectManifestRespVO>> getProjectManifestList(@RequestParam("ids") Collection<Long> ids) {
        List<BusProjectManifestDO> list = projectManifestService.getProjectManifestList(ids);
        return CommonResult.success(BusProjectManifestConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得项目物料清单分页")
    @PreAuthorize("@ss.hasPermission('bus:project-manifest:query')")
    public CommonResult<PageResult<BusProjectManifestRespVO>> getProjectManifestPage(@Valid BusProjectManifestPageReqVO pageVO) {
        PageResult<BusProjectManifestDO> pageResult = projectManifestService.getProjectManifestPage(pageVO);
        return CommonResult.success(BusProjectManifestConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出项目物料清单 Excel")
    @PreAuthorize("@ss.hasPermission('bus:project-manifest:export')")
    @OperateLog(type = OperateTypeEnum.EXPORT)
    public void exportProjectManifestExcel(@Valid BusProjectManifestExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BusProjectManifestDO> list = projectManifestService.getProjectManifestList(exportReqVO);
        // 导出 Excel
        List<BusProjectManifestExcelVO> datas = BusProjectManifestConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "项目物料清单.xls", "数据", BusProjectManifestExcelVO.class, datas);
    }

}
