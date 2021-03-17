package com.tss.mangoactiviti.controller;

import com.tss.mangoactiviti.manager.WorkflowManager;
import com.tss.mangoactiviti.model.ProcessDef;
import com.tss.mangocommon.id.IdWorker;
import com.tss.mangocommon.context.UserInfoContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author YC01224 yangxiangjun
 * @description
 * @date 2021/3/11 10:28
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/wkf")
public class WorkFlowController {
    @Autowired
    private WorkflowManager workflowManager;
    @Autowired
    private IdWorker idWorker;

    @RequestMapping("deploy")
    public String createDeploy(){
        String deployID = workflowManager.createDeployByBPMN("static/diagram.bpmn", "审批流测试");
        return deployID;
    }

    @RequestMapping("queryDefinition")
    public List<ProcessDef> queryDefinition(){
        System.out.println(UserInfoContext.getUserInfoContext().toString());
        System.out.println(idWorker.nextId());
        List<ProcessDef> processDefinitions = workflowManager.queryProcessDefinitions();
        return processDefinitions;
    }

    @RequestMapping("delete")
    public void deleteDefinition(@RequestBody Map<String,String> param){
        workflowManager.deleteDeployment(param.getOrDefault("id",null));
    }
}
