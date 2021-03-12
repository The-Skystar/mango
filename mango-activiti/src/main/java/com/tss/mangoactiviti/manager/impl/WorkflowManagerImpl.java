package com.tss.mangoactiviti.manager.impl;

import com.google.errorprone.annotations.Var;
import com.tss.mangoactiviti.manager.WorkflowManager;
import com.tss.mangoactiviti.model.ProcessDef;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YC01224 yangxiangjun
 * @description
 * @date 2021/3/11 10:05
 * @since JDK 1.8
 */
@Component
public class WorkflowManagerImpl implements WorkflowManager {
    private static final Logger log = LoggerFactory.getLogger(WorkflowManagerImpl.class);

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public String createDeployByBPMN(String filePath,String name) {
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource(filePath)
                .name(name)
                .deploy();
        log.info("流程部署完成，流程ID：{}，流程名称：{}",deploy.getId(),deploy.getName());
        return deploy.getId();
    }

    @Override
    public List<ProcessDef> queryProcessDefinitions() {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinitions = processDefinitionQuery.orderByProcessDefinitionVersion().desc().list();

        List<ProcessDef> collect = processDefinitions.stream().map(processDefinition -> {
            ProcessDef processDef = new ProcessDef();
            processDef.setId(processDefinition.getId());
            processDef.setName(processDefinition.getName());
            processDef.setKey(processDefinition.getKey());
            processDef.setCategory(processDefinition.getCategory());
            processDef.setDeploymentId(processDefinition.getDeploymentId());
            processDef.setDescription(processDefinition.getDescription());
            processDef.setResourceName(processDefinition.getResourceName());
            processDef.setVersion(processDefinition.getVersion());
            return processDef;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deleteDeployment(String id) {
        if (!StringUtils.isEmpty(id)) {
            //设置true 级联删除流程定义，即使该流程有流程实例启动也可以删除
            repositoryService.deleteDeployment(id,true);
            log.info("成功删除流程定义，流程ID：{}",id);
        }
    }
}
