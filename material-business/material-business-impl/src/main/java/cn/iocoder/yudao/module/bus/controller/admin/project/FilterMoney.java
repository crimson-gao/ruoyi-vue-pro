package cn.iocoder.yudao.module.bus.controller.admin.project;

import cn.iocoder.yudao.module.bus.controller.admin.project.vo.BusProjectUpdateReqVO;
import cn.iocoder.yudao.module.bus.dal.dataobject.project.BusProjectDO;
import cn.iocoder.yudao.module.bus.service.project.BusProjectService;
import cn.iocoder.yudao.module.system.service.permission.PermissionService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.Valid;
@Component("FilterMoney")
public class FilterMoney {
    final static String MONEY_VIEW_PERMISSION = "bus:project:viewMoney";
    final static String MONEY_UPDATE_PERMISSION = "bus:project:updateMoney";
    @Resource
    private PermissionService svc;
    @Resource
    private  BusProjectService projectService;
    public void doFilterResp(BusProjectDO bdo){
        if(svc.hasAnyPermissions(MONEY_VIEW_PERMISSION) || bdo == null){
            return;
        }
        bdo.setPaidPayment(null);
        bdo.setRequiredPayment(null);
    }
    public  @Valid BusProjectUpdateReqVO doFilterUpdate(BusProjectUpdateReqVO vo){
        if(svc.hasAnyPermissions(MONEY_UPDATE_PERMISSION) || vo == null){
            return vo;
        }
        BusProjectDO project = projectService.getProject(vo.getId());
        vo.setPaidPayment(project.getPaidPayment());
        vo.setRequiredPayment(project.getRequiredPayment());
        return vo;
    }
}
