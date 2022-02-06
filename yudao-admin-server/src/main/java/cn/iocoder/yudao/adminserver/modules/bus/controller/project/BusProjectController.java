package cn.iocoder.yudao.adminserver.modules.bus.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;

import io.swagger.annotations.*;

import javax.validation.constraints.*;
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

import cn.iocoder.yudao.adminserver.modules.bus.controller.project.vo.*;
import cn.iocoder.yudao.adminserver.modules.bus.dal.dataobject.project.BusProjectDO;
import cn.iocoder.yudao.adminserver.modules.bus.convert.project.BusProjectConvert;
import cn.iocoder.yudao.adminserver.modules.bus.service.project.BusProjectService;

@Api(tags = "项目")
@RestController
@RequestMapping("/bus/project")
@Validated
public class BusProjectController {
    @Autowired
    private FilterMoney filterMoney;

    @Resource
    private BusProjectService projectService;

    @PostMapping("/create")
    @ApiOperation("创建项目")
    @PreAuthorize("@ss.hasPermission('bus:project:create')")
    public CommonResult<Long> createProject(@Valid @RequestBody BusProjectCreateReqVO createReqVO) {
        return success(projectService.createProject(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新项目")
    @PreAuthorize("@ss.hasPermission('bus:project:update')")
    public CommonResult<Boolean> updateProject(@Valid @RequestBody BusProjectUpdateReqVO updateReqVO) {
        // 做一下权限的过滤，没有权限时，拒绝输入的 money 项
        projectService.updateProject(filterMoney.doFilterUpdate(updateReqVO));
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除项目")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('bus:project:delete')")
    public CommonResult<Boolean> deleteProject(@RequestParam("id") Long id) {
        projectService.deleteProject(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得项目")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('bus:project:query')")
    public CommonResult<BusProjectRespVO> getProject(@RequestParam("id") Long id) {
        BusProjectDO project = projectService.getProject(id);
        // 使用权限过滤下
        filterMoney.doFilterResp(project);
        return success(BusProjectConvert.INSTANCE.convert(project));
    }

    @GetMapping("/list")
    @ApiOperation("获得项目列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('bus:project:query')")
    public CommonResult<List<BusProjectRespVO>> getProjectList(@RequestParam("ids") Collection<Long> ids) {
        List<BusProjectDO> list = projectService.getProjectList(ids);
        // 使用权限过滤下
        if(list!=null) {
            list.forEach(e->filterMoney.doFilterResp(e));
        }
        return success(BusProjectConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得项目分页")
    @PreAuthorize("@ss.hasPermission('bus:project:query')")
    public CommonResult<PageResult<BusProjectRespVO>> getProjectPage(@Valid BusProjectPageReqVO pageVO) {
        PageResult<BusProjectDO> pageResult = projectService.getProjectPage(pageVO);
        // 使用权限过滤下
        if( pageResult != null && pageResult.getList() != null) {
            pageResult.getList().forEach(e->filterMoney.doFilterResp(e));
        }
        return success(BusProjectConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出项目 Excel")
    @PreAuthorize("@ss.hasPermission('bus:project:export')")
    @OperateLog(type = EXPORT)
    public void exportProjectExcel(@Valid BusProjectExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BusProjectDO> list = projectService.getProjectList(exportReqVO);
        // 使用权限过滤下
        if(list!=null){
            list.forEach(e->filterMoney.doFilterResp(e));
        }

        // 导出 Excel
        List<BusProjectExcelVO> datas = BusProjectConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "项目.xls", "数据", BusProjectExcelVO.class, datas);
    }

}
